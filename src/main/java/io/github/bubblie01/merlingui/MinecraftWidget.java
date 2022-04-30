package io.github.bubblie01.merlingui;


import com.spinyowl.legui.component.*;
import com.spinyowl.legui.component.event.widget.WidgetCloseEvent;
import com.spinyowl.legui.component.misc.listener.widget.WidgetDragListener;
import com.spinyowl.legui.component.optional.TextState;
import com.spinyowl.legui.component.optional.align.HorizontalAlign;
import com.spinyowl.legui.component.optional.align.VerticalAlign;
import com.spinyowl.legui.event.MouseClickEvent;
import com.spinyowl.legui.event.MouseDragEvent;
import com.spinyowl.legui.icon.CharIcon;
import com.spinyowl.legui.icon.Icon;
import com.spinyowl.legui.icon.ImageIcon;
import com.spinyowl.legui.image.loader.ImageLoader;
import com.spinyowl.legui.listener.EventListener;
import com.spinyowl.legui.listener.MouseDragEventListener;
import com.spinyowl.legui.style.Style;
import com.spinyowl.legui.style.color.ColorConstants;
import com.spinyowl.legui.style.flex.FlexStyle;
import com.spinyowl.legui.style.font.FontRegistry;
import com.spinyowl.legui.style.length.Length;
import com.spinyowl.legui.theme.Themes;
import io.github.bubblie01.merlingui.events.listeners.MinecraftWidgetCloseButtonEventListener;
import io.github.bubblie01.merlingui.events.listeners.MinecraftWidgetDragListener;
import io.github.bubblie01.merlingui.events.listeners.MinecraftWidgetMinimizeEventListener;
import io.github.bubblie01.merlingui.events.listeners.MinecraftWidgetResizeDragListener;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.List;

public class MinecraftWidget extends Component   {
    public static final String DEFAULT_WIDGET_TITLE = "Widget";
    /**
     * Initial height of title. Used to initialize title components.
     */
    private static final int INITIAL_TITLE_HEIGHT = 20;
    private static final int CLOSE_ICON_CHAR = 0xF5AD;
    private static final int MINIMIZE_ICON_CHAR = 0xF5B0;
    private static final int MAXIMIZE_ICON_CHAR = 0xF5AF;
    private MouseDragEventListener mouseDragEventLeguiEventListener;

    private Icon closeIcon;
    private Icon minimizeIcon;
    private Icon maximizeIcon;
    private ImageIcon titleIcon;

    private boolean draggable = true;
    private boolean minimized = false;
    private boolean resizable = true;
    /**
     * Used to store widget size in maximized state when minimizing widget.
     */
    private Vector2f maximizedSize = new Vector2f();

    private Component container;
    private Component titleContainer;

    private Label title;

    private Button closeButton;
    private Button minimizeButton;
    private Button resizeButton;

    private Length maximizedMinWidth;
    private Length maximizedMinHeight;
    private Length maximizedMaxWidth;
    private Length maximizedMaxHeight;
    private Length maximizedWidth;
    private Length maximizedHeight;

    private boolean ascendible = true;

    public MinecraftWidget() {
        initialize(DEFAULT_WIDGET_TITLE);
    }

    /**
     * Creates a widget with default title text. Ascendible by default (See {@link #isAscendible()}).
     *
     * @param ascendible used to make it ascendible or not.
     */
    public MinecraftWidget(boolean ascendible) {
        this.ascendible = ascendible;
        initialize(DEFAULT_WIDGET_TITLE);
    }

    /**
     * Creates a widget with default title text and specified position and size. Ascendible by default
     * (See {@link #isAscendible()}).
     *
     * @param x      x position in parent.
     * @param y      y position in parent.
     * @param width  width of component.
     * @param height height of component.
     */
    public MinecraftWidget(float x, float y, float width, float height) {
        super(x, y, width, height);
        initialize(DEFAULT_WIDGET_TITLE);
    }

    /**
     * Creates a widget with default title text and specified position and size. Ascendible by default
     * (See {@link #isAscendible()}).
     *
     * @param x          x position in parent.
     * @param y          y position in parent.
     * @param width      width of component.
     * @param height     height of component.
     * @param ascendible used to make it ascendible or not.
     */
    public MinecraftWidget(float x, float y, float width, float height, boolean ascendible) {
        super(x, y, width, height);
        this.ascendible = ascendible;
        initialize(DEFAULT_WIDGET_TITLE);
    }

    /**
     * Creates a widget with default title text and specified position and size. Ascendible by default
     * (See {@link #isAscendible()}).
     *
     * @param position position in parent.
     * @param size     size of component.
     */
    public MinecraftWidget(Vector2f position, Vector2f size) {
        super(position, size);
        initialize(DEFAULT_WIDGET_TITLE);
    }


    /**
     * Creates a widget with default title text and specified position and size. Ascendible by default
     * (See {@link #isAscendible()}).
     *
     * @param position   position in parent.
     * @param size       size of component.
     * @param ascendible used to make it ascendible or not.
     */
    public MinecraftWidget(Vector2f position, Vector2f size, boolean ascendible) {
        super(position, size);
        this.ascendible = ascendible;
        initialize(DEFAULT_WIDGET_TITLE);
    }

    /**
     * Creates a widget with specified title text. Ascendible by default (See {@link
     * #isAscendible()}).
     *
     * @param title widget text.
     */
    public MinecraftWidget(String title) {
        initialize(title);
    }

    /**
     * Creates a widget with specified title text. Ascendible by default (See {@link
     * #isAscendible()}).
     *
     * @param title      widget text.
     * @param ascendible used to make it ascendible or not.
     */
    public MinecraftWidget(String title, boolean ascendible) {
        this.ascendible = ascendible;
        initialize(title);
    }

    /**
     * Creates a widget with specified title text and specified position and size. Ascendible by
     * default (See {@link #isAscendible()}).
     *
     * @param title  widget text.
     * @param x      x position in parent.
     * @param y      y position in parent.
     * @param width  width of component.
     * @param height height of component.
     */
    public MinecraftWidget(String title, float x, float y, float width, float height) {
        super(x, y, width, height);
        initialize(title);
    }

    /**
     * Creates a widget with specified title text and specified position and size. Ascendible by
     * default (See {@link #isAscendible()}).
     *
     * @param title      widget text.
     * @param x          x position in parent.
     * @param y          y position in parent.
     * @param width      width of component.
     * @param height     height of component.
     * @param ascendible used to make it ascendible or not.
     */
    public MinecraftWidget(String title, float x, float y, float width, float height, boolean ascendible) {
        super(x, y, width, height);
        this.ascendible = ascendible;
        initialize(title);
    }

    /**
     * Creates a widget with specified title text and specified position and size. Ascendible by
     * default (See {@link #isAscendible()}).
     *
     * @param title    widget text.
     * @param position position in parent.
     * @param size     size of component.
     */
    public MinecraftWidget(String title, Vector2f position, Vector2f size) {
        super(position, size);
        initialize(title);
    }

    /**
     * Creates a widget with specified title text and specified position and size. Ascendible by
     * default (See {@link #isAscendible()}).
     *
     * @param title      widget text.
     * @param position   position in parent.
     * @param size       size of component.
     * @param ascendible used to make it ascendible or not.
     */
    public MinecraftWidget(String title, Vector2f position, Vector2f size, boolean ascendible) {
        super(position, size);
        this.ascendible = ascendible;
        initialize(title);
    }

    public void initialize(String title) {
        this.getStyle().setDisplay(Style.DisplayType.FLEX);
        this.getStyle().getFlexStyle().setFlexDirection(FlexStyle.FlexDirection.COLUMN);
        this.getStyle().setMinWidth(50f);
        this.getStyle().setMinHeight(50f);
        this.getStyle().setPadding(0f);
        //this.getStyle().getBackground().setIcon(new ImageIcon(ImageLoader.loadImage("assets/merlin/textures/widget.png")));



        titleContainer = new ImageView(ImageLoader.loadImage("assets/merlin/textures/tab.png"));
        titleContainer.setTabFocusable(false);

        titleContainer.getStyle().setDisplay(Style.DisplayType.FLEX);
        titleContainer.getStyle().setPosition(Style.PositionType.RELATIVE);
        titleContainer.getSize().y = INITIAL_TITLE_HEIGHT;
        titleContainer.getStyle().setHeight((float) INITIAL_TITLE_HEIGHT);
        titleContainer.getStyle().setMinHeight((float) INITIAL_TITLE_HEIGHT);
        titleContainer.getStyle().setMaxHeight((float) INITIAL_TITLE_HEIGHT);
        titleContainer.getStyle().getFlexStyle().setFlexGrow(1);
        titleContainer.getStyle().getFlexStyle().setFlexShrink(1);
        titleContainer.getStyle().getFlexStyle().setFlexDirection(FlexStyle.FlexDirection.ROW);
        titleContainer.getStyle().getBackground().setColor(ColorConstants.transparent());



        this.title = new Label(title);
        this.title.getStyle().setPosition(Style.PositionType.RELATIVE);
        this.title.getStyle().setMaxWidth(Float.MAX_VALUE);
        this.title.getSize().y = INITIAL_TITLE_HEIGHT;
        this.title.getStyle().setMaxHeight((float) INITIAL_TITLE_HEIGHT);
        this.title.getStyle().setHeight((float) INITIAL_TITLE_HEIGHT);
        this.title.getStyle().setMinWidth(0f);
        this.title.getStyle().setMinHeight((float) INITIAL_TITLE_HEIGHT);
        this.title.getStyle().setBorder(null);
        this.title.getStyle().getFlexStyle().setFlexGrow(1);
        this.title.getStyle().getFlexStyle().setFlexShrink(1);
        this.title.setTabFocusable(false);
        this.title.getStyle().getBackground().setColor(ColorConstants.transparent());

        mouseDragEventLeguiEventListener = new MinecraftWidgetDragListener(this);
        this.title.getListenerMap().addListener(MouseDragEvent.class, mouseDragEventLeguiEventListener);




        closeButton = new Button("");

        int iconSize = INITIAL_TITLE_HEIGHT;
        closeIcon = new ImageIcon(ImageLoader.loadImage("assets/merlin/textures/cross.png"));
        closeIcon.setSize(new Vector2f(iconSize));
        closeIcon.setHorizontalAlign(HorizontalAlign.CENTER);
        closeIcon.setVerticalAlign(VerticalAlign.MIDDLE);

        closeButton.getStyle().setPosition(Style.PositionType.RELATIVE);
        closeButton.getStyle().getBackground().setIcon(closeIcon);
        closeButton.getSize().y = INITIAL_TITLE_HEIGHT;
        closeButton.getStyle().setMaxWidth((float) INITIAL_TITLE_HEIGHT);
        closeButton.getStyle().setMaxHeight((float) INITIAL_TITLE_HEIGHT);
        closeButton.getStyle().setMinWidth((float) INITIAL_TITLE_HEIGHT);
        closeButton.getStyle().setMinHeight((float) INITIAL_TITLE_HEIGHT);
        closeButton.getStyle().setWidth((float) INITIAL_TITLE_HEIGHT);
        closeButton.getStyle().setHeight((float) INITIAL_TITLE_HEIGHT);
        closeButton.getStyle().setBorder(null);
        closeButton.getStyle().getFlexStyle().setFlexGrow(1);
        closeButton.getStyle().getFlexStyle().setFlexShrink(1);

        closeButton.getListenerMap()
                .addListener(MouseClickEvent.class, new MinecraftWidgetCloseButtonEventListener(this));
        closeButton.getStyle().setVerticalAlign(VerticalAlign.MIDDLE);
        closeButton.getStyle().setHorizontalAlign(HorizontalAlign.CENTER);
        closeButton.setTabFocusable(false);

        minimizeButton = new Button("");
        minimizeButton.setTabFocusable(false);

        minimizeIcon = new ImageIcon(ImageLoader.loadImage("assets/merlin/textures/hide_pressed.png"));
        minimizeIcon.setSize(new Vector2f(iconSize));
        minimizeIcon.setHorizontalAlign(HorizontalAlign.CENTER);
        minimizeIcon.setVerticalAlign(VerticalAlign.MIDDLE);

        maximizeIcon = new ImageIcon(ImageLoader.loadImage("assets/merlin/textures/hide.png"));
        maximizeIcon.setSize(new Vector2f(iconSize));
        maximizeIcon.setHorizontalAlign(HorizontalAlign.CENTER);
        maximizeIcon.setVerticalAlign(VerticalAlign.MIDDLE);

        minimizeButton.getStyle().getBackground().setIcon(minimizeIcon);
        minimizeButton.getStyle().setPosition(Style.PositionType.RELATIVE);
        minimizeButton.getSize().y = INITIAL_TITLE_HEIGHT;
        minimizeButton.getStyle().setMaxWidth((float) INITIAL_TITLE_HEIGHT);
        minimizeButton.getStyle().setMaxHeight((float) INITIAL_TITLE_HEIGHT);
        minimizeButton.getStyle().setMinWidth((float) INITIAL_TITLE_HEIGHT);
        minimizeButton.getStyle().setMinHeight((float) INITIAL_TITLE_HEIGHT);
        minimizeButton.getStyle().setWidth((float) INITIAL_TITLE_HEIGHT);
        minimizeButton.getStyle().setHeight((float) INITIAL_TITLE_HEIGHT);
        minimizeButton.getStyle().getFlexStyle().setFlexGrow(1);
        minimizeButton.getStyle().getFlexStyle().setFlexShrink(1);
        minimizeButton.getStyle().setBorder(null);


        minimizeButton.getListenerMap().addListener(MouseClickEvent.class, new MinecraftWidgetMinimizeEventListener(this));
        minimizeButton.getStyle().setVerticalAlign(VerticalAlign.MIDDLE);
        minimizeButton.getStyle().setHorizontalAlign(HorizontalAlign.CENTER);

        container = new Panel();
        applyStylesToContainer(container);

        titleContainer.add(this.title);
        titleContainer.add(minimizeButton);
        titleContainer.add(closeButton);

        resizeButton = new Button("");
        ImageIcon icon = new ImageIcon(ImageLoader.loadImage("assets/merlin/textures/resize.png"));
        icon.setSize(new Vector2f(iconSize));
        icon.setPosition(new Vector2f(-10, -10));
        resizeButton.getStyle().getBackground().setIcon(icon);
        resizeButton.getStyle().setWidth(10f);
        resizeButton.getStyle().setHeight(10f);
        resizeButton.getStyle().setBottom(0f);
        resizeButton.getStyle().setRight(0f);
        resizeButton.getStyle().setBorder(null);
        resizeButton.getStyle().getBackground().setColor(0.0f,0.0f,0.0f,0.0f);
        resizeButton.setTabFocusable(false);
        resizeButton.getListenerMap().addListener(MouseDragEvent.class, new MinecraftWidgetResizeDragListener(resizeButton, this));

        this.add(titleContainer);
        this.add(container);
        this.add(resizeButton);

        Themes.getDefaultTheme().getThemeManager().getComponentTheme(MinecraftWidget.class).applyAll(this);
    }

    public void applyStylesToContainer(Component container) {
        container.setTabFocusable(false);
        container.getStyle().getFlexStyle().setFlexShrink(1);
        container.getStyle().getFlexStyle().setFlexGrow(1);
        container.getStyle().setPosition(Style.PositionType.RELATIVE);
    }

    /**
     * Returns height of title.
     *
     * @return height of title.
     */
    public float getTitleHeight() {
        return titleContainer.getSize().y;
    }

    /**
     * Used to set title height.
     *
     * @param titleHeight title height to set.
     */
    public void setTitleHeight(float titleHeight) {
        this.titleContainer.getSize().y = titleHeight;
        this.titleContainer.getStyle().setMinHeight(titleHeight);
        this.titleContainer.getStyle().setHeight(titleHeight);
        this.titleContainer.getStyle().setMaxHeight(titleHeight);
        this.title.getStyle().setMinHeight(titleHeight);
        this.title.getStyle().setHeight(titleHeight);
        this.title.getStyle().setMaxHeight(titleHeight);
        this.closeButton.getStyle().setMinHeight(titleHeight);
        this.closeButton.getStyle().setMaxHeight(titleHeight);
        this.closeButton.getStyle().setHeight(titleHeight);
        this.minimizeButton.getStyle().setMinHeight(titleHeight);
        this.minimizeButton.getStyle().setMaxHeight(titleHeight);
        this.minimizeButton.getStyle().setHeight(titleHeight);
    }

    /**
     * Returns true if title enabled.
     *
     * @return true if title enabled.
     */
    public boolean isTitleEnabled() {
        return titleContainer.isVisible();
    }

    /**
     * Used to set title enabled or not.
     *
     * @param titleEnabled title state (enable or not) to set.
     */
    public void setTitleEnabled(boolean titleEnabled) {
        if (minimized) {
            return;
        }
        this.titleContainer.getStyle().setDisplay(titleEnabled ? Style.DisplayType.FLEX : Style.DisplayType.NONE);
    }

    /**
     * Returns true if widget should be closeable.
     *
     * @return true if widget should be closeable.
     */
    public boolean isCloseable() {
        return closeButton.isVisible();
    }

    /**
     * Used to set would widget closeable or not.
     *
     * @param closeable widget state (closeable or not) to set.
     */
    public void setCloseable(boolean closeable) {
        this.closeButton.getStyle().setDisplay(closeable ? Style.DisplayType.MANUAL : Style.DisplayType.NONE);
    }

    /**
     * Returns close button of widget.
     *
     * @return close button of widget.
     */
    public Button getCloseButton() {
        return closeButton;
    }

    /**
     * Returns minimize button of widget.
     *
     * @return minimize button of widget.
     */
    public Button getMinimizeButton() {
        return minimizeButton;
    }

    /**
     * Returns title container. Can be used to customize widget.
     *
     * @return title container.
     */
    public Component getTitleContainer() {
        return titleContainer;
    }

    /**
     * Returns title text state.
     *
     * @return title text state.
     */
    public TextState getTitleTextState() {
        return title.getTextState();
    }

    public Label getTitle() {
        return title;
    }

    /**
     * Returns close button text color.
     *
     * @return close button text color.
     */
    public Vector4f getCloseButtonColor() {
        return closeButton.getStyle().getTextColor();
    }

    /**
     * Used to set close button text color.
     *
     * @param closeButtonColor close button text color to set.
     */
    public void setCloseButtonColor(Vector4f closeButtonColor) {
        this.closeButton.getStyle().setTextColor(closeButtonColor);
    }

    /**
     * Returns widget container that hold all other elements. Should be used to add components.
     *
     * @return widget container.
     */
    public Component getContainer() {
        return container;
    }

    /**
     * Used to set widget container.
     *
     * @param container widget container to set.
     */
    public void setContainer(Component container) {
        this.remove(this.container);
        this.container = container;
        this.add(1, this.container);
        applyStylesToContainer(this.container);
    }

    /**
     * Returns true if widget could be dragged.
     *
     * @return true if widget could be dragged.
     */
    public boolean isDraggable() {
        return draggable;
    }

    /**
     * Used to set widget draggable or not.
     *
     * @param draggable new draggable state of widget.
     */
    public void setDraggable(boolean draggable) {
        if (this.draggable != draggable) {
            if (draggable) {
                this.title.getListenerMap()
                        .addListener(MouseDragEvent.class, mouseDragEventLeguiEventListener);
            } else {
                this.title.getListenerMap()
                        .removeListener(MouseDragEvent.class, mouseDragEventLeguiEventListener);
            }
            this.draggable = draggable;
        }
    }

    /**
     * Returns true if widget could be minimized (minimize button is visible).
     *
     * @return true if widget could be minimized (minimize button is visible).
     */
    public boolean isMinimizable() {
        return minimizeButton.isVisible();
    }

    /**
     * Used to make minimize button visible or not.
     *
     * @param minimizable new minimizable state of widget.
     */
    public void setMinimizable(boolean minimizable) {
        this.minimizeButton.getStyle().setDisplay(minimizable ? Style.DisplayType.MANUAL : Style.DisplayType.NONE);
    }

    /**
     * Returns true if widget minimized.
     *
     * @return true if widget minimized.
     */
    public boolean isMinimized() {
        return minimized;
    }

    /**
     * Used to minimize/maximize widget.
     *
     * @param minimized true to minimize, false to maximize.
     */
    public void setMinimized(boolean minimized) {
        if (this.minimized != minimized) {
            this.minimized = minimized;
            if (minimized) {
                minimize();
            } else {
                maximize();
            }
            updateIcons();
        }
    }

    public void hide() {
        getStyle().setDisplay(Style.DisplayType.NONE);
    }

    public void show() {
        getStyle().setDisplay(Style.DisplayType.FLEX);
    }

    /**
     * Used to minimize widget.
     */
    private void minimize() {
        if (isTitleEnabled()) {
            Vector2f size = getSize();
            maximizedSize.set(size);

            maximizedMinWidth = getStyle().getMinWidth();
            maximizedMinHeight = getStyle().getMinHeight();
            maximizedMaxWidth = getStyle().getMaxWidth();
            maximizedMaxHeight = getStyle().getMaxHeight();
            maximizedWidth = getStyle().getWidth();
            maximizedHeight = getStyle().getHeight();

            float titleHeight = getTitleHeight();
            size.set(size.x, titleHeight);

            this.getStyle().setMaxHeight(titleHeight);
            this.getStyle().setHeight(titleHeight);
            this.getStyle().setMinHeight(titleHeight);

            if (resizable) {
                resizeButton.getStyle().setDisplay(Style.DisplayType.NONE);
            }
        }
    }

    /**
     * Used to maximize widget.
     */
    private void maximize() {
        if (isTitleEnabled()) {

            this.getSize().set(maximizedSize);

            this.getStyle().setMaxWidth(maximizedMaxWidth);
            this.getStyle().setMaxHeight(maximizedMaxHeight);
            this.getStyle().setMinWidth(maximizedMinWidth);
            this.getStyle().setMinHeight(maximizedMinHeight);
            this.getStyle().setWidth(maximizedWidth);
            this.getStyle().setHeight(maximizedHeight);

            if (resizable) {
                resizeButton.getStyle().setDisplay(Style.DisplayType.MANUAL);
            }
        }
    }

    /**
     * Returns close icon that used by close button.
     *
     * @return close icon that used by close button.
     */
    public Icon getCloseIcon() {
        return closeIcon;
    }

    /**
     * Used to set close icon to close button.
     *
     * @param closeIcon close icon to set.
     */
    public void setCloseIcon(Icon closeIcon) {
        this.closeIcon = closeIcon;
        updateIcons();
    }

    /**
     * Returns maximize icon that used by minimize button.
     *
     * @return maximize icon that used by minimize button.
     */
    public Icon getMaximizeIcon() {
        return maximizeIcon;
    }

    /**
     * Used to set maximize icon to minimize button.
     *
     * @param maximizeIcon maximize icon to set.
     */
    public void setMaximizeIcon(Icon maximizeIcon) {
        this.maximizeIcon = maximizeIcon;
        updateIcons();
    }

    /**
     * Returns minimize icon that used by minimize button.
     *
     * @return minimize icon that used by minimize button.
     */
    public Icon getMinimizeIcon() {
        return minimizeIcon;
    }

    /**
     * Used to set minimize icon to minimize button.
     *
     * @param minimizeIcon minimize icon to set.
     */
    public void setMinimizeIcon(Icon minimizeIcon) {
        this.minimizeIcon = minimizeIcon;
        updateIcons();
    }

    /**
     * Used to update icons of close and minimize buttons.
     */
    private void updateIcons() {
        closeButton.getStyle().getBackground().setIcon(closeIcon);
        minimizeButton.getStyle().getBackground().setIcon(minimized ? maximizeIcon : minimizeIcon);
    }

    /**
     * Used to add event listener for widget close event.
     *
     * @param eventListener event listener to add.
     */
    public void addWidgetCloseEventListener(EventListener<WidgetCloseEvent> eventListener) {
        this.getListenerMap().addListener(WidgetCloseEvent.class, eventListener);
    }

    /**
     * Returns all event listeners for widget close event.
     *
     * @return all event listeners for widget close event.
     */
    public List<EventListener<WidgetCloseEvent>> getWidgetCloseEvents() {
        return this.getListenerMap().getListeners(WidgetCloseEvent.class);
    }

    /**
     * Used to remove event listener for widget close event.
     *
     * @param eventListener event listener to remove.
     */
    public void removeWidgetCloseEventListener(EventListener<WidgetCloseEvent> eventListener) {
        this.getListenerMap().removeListener(WidgetCloseEvent.class, eventListener);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MinecraftWidget widget = (MinecraftWidget) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(draggable, widget.draggable)
                .append(minimized, widget.minimized)
                .append(maximizedSize, widget.maximizedSize)
                .append(container, widget.container)
                .append(title, widget.title)
                .append(closeButton, widget.closeButton)
                .append(minimizeButton, widget.minimizeButton)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(draggable)
                .append(minimized)
                .append(maximizedSize)
                .append(container)
                .append(title)
                .append(closeButton)
                .append(minimizeButton)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("draggable", draggable)
                .append("minimized", minimized)
                .append("maximizedSize", maximizedSize)
                .append("container", container)
                .append("title", title)
                .append("closeButton", closeButton)
                .append("minimizeButton", minimizeButton)
                .toString();
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
        this.resizeButton.getStyle().setDisplay(resizable ? Style.DisplayType.MANUAL : Style.DisplayType.NONE);
    }

    public Button getResizeButton() {
        return resizeButton;
    }

    public ImageIcon getTitleImage()
    {
        return titleIcon;
    }

    /**
     * Returns true if widget could ascend to the top in parent container.
     *
     * @return true if widget could ascend to the top in parent container.
     */
    public boolean isAscendible() {
        return ascendible;
    }

    /**
     * Set true to make widget ascend to the top in parent container on click event on widget or any
     * component inside.
     *
     * @param ascendible new state.
     */
    public void setAscendible(boolean ascendible) {
        this.ascendible = ascendible;
    }
}

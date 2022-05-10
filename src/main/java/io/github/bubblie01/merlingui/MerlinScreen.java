package io.github.bubblie01.merlingui;

import com.mojang.blaze3d.platform.InputUtil;
import com.spinyowl.legui.component.*;
import com.spinyowl.legui.component.optional.TextState;
import com.spinyowl.legui.image.loader.ImageLoader;
import com.spinyowl.legui.style.Style;
import com.spinyowl.legui.style.color.ColorConstants;
import com.spinyowl.legui.style.font.FontRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector4f;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class MerlinScreen extends Screen
{
    private int controlLeftTicks;
    private double glfwTime;
    private double eventDeltaWheel;
    private int counter;
    private int mods;
    private int action;
    public float width;
    public float height;
    private final Frame frame = new Frame();
    long handle;
    boolean isInit;
    //public List<Component> componentList = new ArrayList<Component>();
    public MerlinScreen(Text text)
    {
        super(text);
    }



    @Override
    public void init()
    {
        handle = this.client.getWindow().getHandle();
        width = this.client.getWindow().getWidth();
        height = this.client.getWindow().getHeight();
        if(!isInit)
        {
            Widget widget = new Widget(150,160, 100, 100);
            TextArea textArea = new TextArea(200,160,400,400);
            ImageView imageView = new ImageView(ImageLoader.loadImage("assets/merlin/textures/cross.png"));
            imageView.getStyle().setPosition(Style.PositionType.RELATIVE);
            imageView.getStyle().setDisplay(Style.DisplayType.FLEX);
            imageView.getStyle().setBorder(null);
            imageView.setSize(200f,200f);
            textArea.getTextAreaField().getStyle().setFont("jetbrain");
            textArea.getTextState().setText("balls");
            //widget.add(textArea);
            //frame.getContainer().add(textArea);
            frame.getContainer().add(imageView);
            //frame.getContainer().add(new Widget(100,100, this.client.getWindow().getScaledWidth() - 100, this.client.getWindow().getScaledHeight() - 100));
        }
        isInit = true;
        MerlinWindow.windowResize(frame);
        super.init();
    }


    public void initializeComponents(Component component)
    {

    }

    public void saveComponentPos(Component component)
    {

    }

    @Override
    public void mouseMoved(double mouseX, double mouseY)
    {
        MerlinWindow.onCursorPos(handle, mouseX, mouseY);
        super.mouseMoved(mouseX, mouseY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button)
    {
        action = GLFW_RELEASE;
        MerlinWindow.onMouseButton(handle, button, action, mods);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        mods = 0;
        action = GLFW_PRESS;
        if(button == GLFW_MOUSE_BUTTON_LEFT || button == GLFW_MOUSE_BUTTON_RIGHT)
        {
            if(InputUtil.isKeyPressed(handle, GLFW_KEY_LEFT_SHIFT) || InputUtil.isKeyPressed(handle, GLFW_KEY_RIGHT_SHIFT))
            {
                    mods |= GLFW_MOD_SHIFT;
            }

        }
        MerlinWindow.onMouseButton(handle, button, action, mods);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void resize(MinecraftClient client, int width, int height) {
        super.resize(client, width, height);
    }

    @Override
    public void onClose()
    {
        super.onClose();
        isInit = false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        MerlinWindow.onKeyPressed(handle, GLFW_PRESS, keyCode, modifiers, scanCode);
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        MerlinWindow.onChar(handle, chr);
        return super.charTyped(chr, modifiers);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {
        MerlinWindow.windowRender(frame);
        super.render(matrices, mouseX, mouseY, delta);
    }

}

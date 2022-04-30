package io.github.bubblie01.merlingui.events.listeners;

import com.spinyowl.legui.component.Widget;
import com.spinyowl.legui.component.event.widget.WidgetCloseEvent;
import com.spinyowl.legui.event.MouseClickEvent;
import com.spinyowl.legui.listener.MouseClickEventListener;
import com.spinyowl.legui.listener.processor.EventProcessorProvider;
import io.github.bubblie01.merlingui.MinecraftWidget;
import io.github.bubblie01.merlingui.events.MinecraftWidgetCloseEvent;

import static com.spinyowl.legui.event.MouseClickEvent.MouseClickAction.CLICK;

public class MinecraftWidgetCloseButtonEventListener implements MouseClickEventListener {

    private MinecraftWidget mcWidget;

    public MinecraftWidgetCloseButtonEventListener(MinecraftWidget widget) {
        this.mcWidget = widget;
    }

    @Override
    public void process(MouseClickEvent event) {
        if (CLICK == event.getAction()) {
            mcWidget.hide();
            EventProcessorProvider.getInstance()
                    .pushEvent(new MinecraftWidgetCloseEvent(mcWidget, event.getContext(), event.getFrame()));
        }
    }

}

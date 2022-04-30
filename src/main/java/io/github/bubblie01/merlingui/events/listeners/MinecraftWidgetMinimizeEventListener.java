package io.github.bubblie01.merlingui.events.listeners;

import com.spinyowl.legui.event.MouseClickEvent;
import com.spinyowl.legui.listener.MouseClickEventListener;
import io.github.bubblie01.merlingui.MinecraftWidget;

import static com.spinyowl.legui.event.MouseClickEvent.MouseClickAction.CLICK;

public class MinecraftWidgetMinimizeEventListener implements MouseClickEventListener
{

    private MinecraftWidget mcWidget;

    public MinecraftWidgetMinimizeEventListener(MinecraftWidget mcWidget)
    {
        this.mcWidget = mcWidget;
    }

    @Override
    public void process(MouseClickEvent event)
    {
        if (CLICK == event.getAction())
        {
            boolean newValue = !mcWidget.isMinimized();
            mcWidget.getMinimizeButton().getStyle().getBackground()
                    .setIcon(newValue ? mcWidget.getMaximizeIcon() : mcWidget.getMinimizeIcon());
            mcWidget.setMinimized(newValue);
        }
    }
}


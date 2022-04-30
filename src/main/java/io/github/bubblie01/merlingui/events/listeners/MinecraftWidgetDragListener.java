package io.github.bubblie01.merlingui.events.listeners;

import com.spinyowl.legui.component.event.component.ChangePositionEvent;
import com.spinyowl.legui.event.MouseDragEvent;
import com.spinyowl.legui.icon.ImageIcon;
import com.spinyowl.legui.image.loader.ImageLoader;
import com.spinyowl.legui.listener.MouseDragEventListener;
import com.spinyowl.legui.listener.processor.EventProcessorProvider;
import io.github.bubblie01.merlingui.MinecraftWidget;
import org.joml.Vector2f;

public class MinecraftWidgetDragListener implements MouseDragEventListener {

    public static final float THRESHOLD = 0.0001f;
    private MinecraftWidget mcWidget;

    public MinecraftWidgetDragListener(MinecraftWidget mcWidget)
    {
        this.mcWidget = mcWidget;
    }

    @Override
    public void process(MouseDragEvent event) {
        Vector2f oldPos = new Vector2f(mcWidget.getPosition());
        mcWidget.getPosition().add(event.getDelta());
        Vector2f newPos = mcWidget.getPosition();
        if (!oldPos.equals(newPos, THRESHOLD)) {
            EventProcessorProvider.getInstance().pushEvent(
                    new ChangePositionEvent(mcWidget, event.getContext(), event.getFrame(), oldPos, newPos));
        }
    }
}

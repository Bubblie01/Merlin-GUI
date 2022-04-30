package io.github.bubblie01.merlingui.events;

import com.spinyowl.legui.component.Frame;
import com.spinyowl.legui.component.Widget;
import com.spinyowl.legui.event.Event;
import com.spinyowl.legui.system.context.Context;
import io.github.bubblie01.merlingui.MinecraftWidget;

public class MinecraftWidgetCloseEvent<T extends MinecraftWidget> extends Event<T> {
    public MinecraftWidgetCloseEvent(T targetComponent, Context context, Frame frame) {
        super(targetComponent, context, frame);
    }

}

package io.github.bubblie01.merlingui.mixin;

import io.github.bubblie01.merlingui.MerlinWindow;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Mouse.class)
public abstract class MouseCursorEventMixin
{
    @Inject(at = @At(value = "TAIL"), method = "onCursorPos")
    private void setCursorPosEvent(long handle, double x, double y, CallbackInfo ci)
    {
        MerlinWindow.onCursorPos(handle, x ,y);
    }

    @Inject(at = @At(value = "TAIL"), method = "onMouseButton")
    private void setMouseButtonEvent(long handle, int button, int action, int mods, CallbackInfo ci)
    {
        MerlinWindow.onMouseButton(handle, button, action, mods);
    }

    @Inject(at = @At(value = "TAIL"), method = "onMouseScroll")
    private void setMouseButtonEvent(long handle, double xOffset, double yOffset, CallbackInfo ci)
    {
        MerlinWindow.onMouseScroll(handle, xOffset, yOffset);
    }




}

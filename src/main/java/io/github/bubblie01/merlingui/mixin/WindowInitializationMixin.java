package io.github.bubblie01.merlingui.mixin;

import io.github.bubblie01.merlingui.MerlinWindow;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFWImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL32C.*;
import static org.lwjgl.opengl.GL43C.*;

@Mixin(Window.class)
public class WindowInitializationMixin
{
    @Inject(at = @At(value = "TAIL"), method = "<init>")
    private void initializeMerlinWindow(CallbackInfo ci)
    {
        MerlinWindow.windowInit(((Window)(Object)this));
        glEnable(GL_DEBUG_OUTPUT);
        glEnable(GL_DEBUG_OUTPUT_SYNCHRONOUS);

    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwWindowHint(II)V"), method = "<init>")
    private void debugWindow(CallbackInfo ci)
    {
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);

    }

}

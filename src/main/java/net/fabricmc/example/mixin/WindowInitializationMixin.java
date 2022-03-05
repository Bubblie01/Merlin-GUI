package net.fabricmc.example.mixin;


import net.fabricmc.example.MerlinScreen;
import net.fabricmc.example.MerlinWindow;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.WindowFramebuffer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.Window;
import net.minecraft.resource.ResourceManager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL32C.*;
import static org.lwjgl.opengl.GL43C.*;
import static org.lwjgl.system.MemoryUtil.NULL;

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

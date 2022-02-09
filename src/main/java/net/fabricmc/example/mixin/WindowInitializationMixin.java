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

@Mixin(Window.class)
public class WindowInitializationMixin
{





    @Inject(at = @At(value = "TAIL"), method = "<init>")
    private void initializeMerlinWindow(CallbackInfo ci)
    {
        MerlinWindow.windowInit(((Window)(Object)this));
    }

}

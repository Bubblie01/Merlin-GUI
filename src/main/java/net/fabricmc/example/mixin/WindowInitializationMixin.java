package net.fabricmc.example.mixin;


import net.fabricmc.example.MerlinScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.WindowFramebuffer;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.Window;
import net.minecraft.resource.ResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class WindowInitializationMixin
{


    @Shadow private static MinecraftClient instance;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void initializeMerlinWindow(CallbackInfo ci)
    {
        MerlinScreen.getWindow().windowInit(instance);
    }
}

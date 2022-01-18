package net.fabricmc.example.mixin;


import net.fabricmc.example.MerlinScreen;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowInitializationMixin
{

    @Inject(at = @At("TAIL"), method = "<init>")
    private void initializeMerlinWindow(CallbackInfo ci)
    {
        MerlinScreen.getWindow().windowInit();
    }
}

package net.fabricmc.example;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.example.MerlinWindow;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.lwjgl.glfw.GLFW;

public class MerlinScreen extends Screen
{
    public MerlinScreen(Text text) {
        super(text);
    }


    @Override
    protected void init()
    {
        super.init();
        MerlinWindow.windowResize();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MerlinWindow.windowRender();
        super.render(matrices, mouseX, mouseY, delta);
    }
}

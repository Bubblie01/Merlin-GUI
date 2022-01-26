package net.fabricmc.example;

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
    static MerlinWindow merlinWindow = new MerlinWindow();
    public static boolean hasEnded;
    public MerlinScreen(Text text) {
        super(text);
    }


    @Override
    protected void init()
    {
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        merlinWindow.windowRender();
        super.render(matrices, mouseX, mouseY, delta);
    }

    public static MerlinWindow getWindow()
    {
    return merlinWindow;
    }

    public static void onClientCreation()
    {
        ClientLifecycleEvents.CLIENT_STARTED.register((listener) -> {
            //merlinWindow.windowInit();
        });
    }
}

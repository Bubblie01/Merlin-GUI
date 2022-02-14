package net.fabricmc.example;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

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

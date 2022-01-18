package net.fabricmc.example;

import net.fabricmc.example.MerlinWindow;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class MerlinScreen extends Screen
{
    static MerlinWindow merlinWindow = new MerlinWindow();

    public MerlinScreen(Text text) {
        super(text);
    }

    @Override
    public void init()
    {
        //merlinWindow.windowInit();
    }

    public static MerlinWindow getWindow()
    {
    return merlinWindow;
    }
}

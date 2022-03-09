package io.github.bubblie01.merlingui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.nio.file.Path;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class MerlinScreen extends Screen
{
    private double x;
    private double y;
    private int controlLeftTicks;
    private double glfwTime;
    private double eventDeltaWheel;
    private int counter;

    public MerlinScreen(Text text) {
        super(text);
    }



    @Override
    protected void init() {
        super.init();
        MerlinWindow.windowResize();
            /*
            GLFWCursorPosCallbackI cursorPosCallbackI = (((window, cursorX, cursorY) -> this.client.execute(() -> {
                this.onCursorMoved(window, cursorX, cursorY);
            })));
            GLFWMouseButtonCallbackI mouseButtonCallbackI = (((window, button, action, mods) -> this.client.execute(() -> {
                this.onMouseClicked(window, button, action, mods);
            })));
            GLFWScrollCallbackI scrollCallbackI = (((window, xoffset, yoffset) -> this.client.execute(() -> {
                this.onMouseScroll(window, xoffset, yoffset);
            })));
            GLFWDropCallbackI dropCallbackI = (((window, count, names) -> {
                Path[] paths = new Path[count];

                for (int i = 0; i < count; ++i) {
                    paths[i] = Paths.get(GLFWDropCallback.getName(names, i));
                }

                this.client.execute(() -> this.onFilesDropped(window, Arrays.asList(paths)));
            }));

            callbackKeeper.getChainCursorPosCallback().add(cursorPosCallbackI);
            callbackKeeper.getChainScrollCallback().add(scrollCallbackI);
            callbackKeeper.getChainMouseButtonCallback().add(mouseButtonCallbackI);
            callbackKeeper.getChainDropCallback().add(dropCallbackI);
            InputUtil.setMouseCallbacks(client.getWindow().getHandle(), callbackKeeper.getChainCursorPosCallback(), callbackKeeper.getChainMouseButtonCallback(), callbackKeeper.getChainScrollCallback(), callbackKeeper.getChainDropCallback());
            SystemEventProcessor.addDefaultCallbacks(callbackKeeper, systemEventProcessor);
             */




    }



    @Override
    public void onClose() {
        super.onClose();

    }


    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MerlinWindow.windowRender();
        super.render(matrices, mouseX, mouseY, delta);
    }

}

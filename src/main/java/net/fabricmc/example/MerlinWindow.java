package net.fabricmc.example;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;


import com.spinyowl.legui.animation.AnimatorProvider;
import com.spinyowl.legui.component.Button;
import com.spinyowl.legui.component.Frame;
import com.spinyowl.legui.component.Label;
import com.spinyowl.legui.component.Widget;
import com.spinyowl.legui.style.Style;
import com.spinyowl.legui.system.context.Context;
import com.spinyowl.legui.system.layout.LayoutManager;
import com.spinyowl.legui.system.renderer.nvg.NvgRenderer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import org.joml.Vector2i;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL32C;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL30C.GL_COLOR_ATTACHMENT0;
import static org.lwjgl.opengl.GL32C.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class MerlinWindow
{
    long glfwWindow;
    int width, height;
    String name;
    private int frameBufferID;
    private int textureID;
    private Frame frame;
    private NvgRenderer renderer;
    private Context context;
    private Framebuffer framebuffer;
    private MinecraftClient client;


    public MerlinWindow()
    {
        this.width = 1;
        this.height = 1;
        this.name = "balls";
    }

    public void windowInit(MinecraftClient client)
    {
        this.client = client;
        this.width = client.getWindow().getWidth();
        this.height = client.getWindow().getHeight();
        glfwWindow = glfwCreateWindow(width, height, name, NULL, NULL);

        GL.createCapabilities();
        glfwSwapInterval(0);

        framebuffer = client.getFramebuffer();

        frameBufferID = GL32C.glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, frameBufferID);

        textureID = framebuffer.getColorAttachment();

        glBindTexture(GL_TEXTURE_2D, textureID);

        context = new Context(glfwWindow);

        renderer = new NvgRenderer();
        renderer.initialize();

        frame = new Frame(width,width);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, 0);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

        glFramebufferTexture(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, textureID, 0);

        glDrawBuffer(GL_COLOR_ATTACHMENT0);

        //glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        //glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        //glClearColor(0.0f, 0.0f, 0.0f, 1.0f);


        Widget widget = new Widget(50,15,200,200);
        widget.setDraggable(true);
        widget.setCloseable(true);
        widget.setMinimizable(true);
        widget.setResizable(true);
        widget.getContainer().getStyle().setDisplay(Style.DisplayType.FLEX);
        //widget.show();

        Button button = new Button("Hello Button", 20, 20, 160, 30);

        frame.getContainer().add(widget);
        frame.getContainer().add(button);


    }

    public void windowRender()
    {
        if(!glfwWindowShouldClose(glfwWindow)) {
            glBindFramebuffer(GL_FRAMEBUFFER, frameBufferID);
            context.updateGlfwWindow();
            Vector2i windowSize = context.getFramebufferSize();
            //glClearColor(0, 0, windowSize.x , windowSize.y);
            glViewport(0,0, width,height);
            //glClear(GL_COLOR_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
            //glEnable(GL_BLEND);
            //glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            renderer.render(frame, context);
            LayoutManager.getInstance().layout(frame);
            AnimatorProvider.getAnimator().runAnimations();
            glBindFramebuffer(GL_FRAMEBUFFER, 0);
        }
    }


}

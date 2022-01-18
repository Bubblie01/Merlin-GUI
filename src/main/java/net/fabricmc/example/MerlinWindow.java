package net.fabricmc.example;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;


import org.lwjgl.opengl.GL32C;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL30C.GL_FRAMEBUFFER;
import static org.lwjgl.opengl.GL30C.glBindFramebuffer;
import static org.lwjgl.system.MemoryUtil.NULL;

public class MerlinWindow
{
    long glfwWindow;
    int width, height;
    String name;
    private int frameBufferID;
    private int textureID;
    private MerlinWindow merlinWindow;


    public MerlinWindow()
    {
        this.width = 1;
        this.height = 1;
        this.name = "balls";
    }

    public void windowInit()
    {

        glfwWindow = glfwCreateWindow(width, height, name, NULL, NULL);

        frameBufferID = GL32C.glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, frameBufferID);

        textureID = RenderSystem.getTextureId(GlStateManager._getActiveTexture());

        glBindTexture(GL_TEXTURE_2D, textureID);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, 0);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

    }

    public void windowRender()
    {

    }

}

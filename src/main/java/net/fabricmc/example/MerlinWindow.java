package net.fabricmc.example;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;


import com.spinyowl.legui.DefaultInitializer;
import com.spinyowl.legui.animation.AnimatorProvider;
import com.spinyowl.legui.component.Button;
import com.spinyowl.legui.component.Frame;
import com.spinyowl.legui.component.Label;
import com.spinyowl.legui.component.Widget;
import com.spinyowl.legui.icon.CharIcon;
import com.spinyowl.legui.icon.Icon;
import com.spinyowl.legui.style.Background;
import com.spinyowl.legui.style.Border;
import com.spinyowl.legui.style.Style;
import com.spinyowl.legui.style.color.ColorConstants;
import com.spinyowl.legui.style.font.TextDirection;
import com.spinyowl.legui.system.context.Context;
import com.spinyowl.legui.system.layout.LayoutManager;
import com.spinyowl.legui.system.renderer.nvg.NvgRenderer;
import com.spinyowl.legui.theme.Themes;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.util.Window;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL32C;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL30C.GL_COLOR_ATTACHMENT0;
import static org.lwjgl.opengl.GL32C.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class MerlinWindow
{

    String name;
    private int frameBufferID;
    private int textureID;
    private static Frame frame;
    private static NvgRenderer renderer;
    private DefaultInitializer initializer;
    private static Context context;
    private static Framebuffer framebuffer;
    private int renderBufferID;
    private int fboWidth, fboHeight;
    static long minecraftContext;


    public static void windowInit(Window window)
    {
        int width = window.getWidth();
        int height = window.getHeight();
        minecraftContext = glfwGetCurrentContext();

        renderer = new NvgRenderer();
        renderer.initialize();
        frame = new Frame(width,height);


        MinecraftWidget widget = new MinecraftWidget("Shit",200,50,200,200);

        //widget.add(widget2);


        //widget.getTitle().getStyle().getBackground().setColor(ColorConstants.green());
        //widget.getMaximizeIcon().setSize(new Vector2f(20f, 20f));
        //widget.getTitleContainer().getFocusedStyle().getBackground().setColor(ColorConstants.green());
        //widget.getContainer().getStyle().setDisplay(Style.DisplayType.FLEX);
        //widget.getTitleContainer().getStyle().getBackground().setColor(ColorConstants.lightGreen());

        Label label = new Label("balls", 20f, 20f, 150f,20f);
        label.setTextDirection(TextDirection.HORIZONTAL);
        label.getStyle().getBackground().setColor(ColorConstants.green());

        Button button = new Button("Hello Button", 20, 20, 160, 30);
        button.getStyle().setPosition(Style.PositionType.RELATIVE);
        frame.getContainer().add(widget);
        frame.getContainer().add(button);
        //frame.getContainer().add(label);

        context = new Context(minecraftContext);

    }

    public static void windowRender()
    {
        try (final StateRestore ignored = new StateRestore()) {
            context.updateGlfwWindow();
            LayoutManager.getInstance().layout(frame);
            renderer.render(frame, context);
            AnimatorProvider.getAnimator().runAnimations();

        }

    }

    public static void windowResize() {
        context.updateGlfwWindow();
        frame.setSize(new Vector2f(context.getWindowSize()));
    }

    private static final class StateRestore implements AutoCloseable {
        private final int program;
        private final int srcRGB;
        private final int srcAlpha;
        private final int dstRGB;
        private final int dstAlpha;
        private final boolean cullFaceEnabled;
        private final int cullFace;
        private final int frontFace;
        private final boolean blendEnabled;
        private final boolean depthTestEnabled;
        private final boolean scissorTestEnabled;
        private final boolean colorMaskRed;
        private final boolean colorMaskGreen;
        private final boolean colorMaskBlue;
        private final boolean colorMaskAlpha;
        private final int stencilMask;
        private final int sfail;
        private final int dpfail;
        private final int dppass;
        private final int stencilFunc;
        private final int stencilRef;
        private final int stencilValueMask;
        private final int uniformBuffer;
        private final int vertexArray;
        private final int arrayBuffer;
        private final int texture2D;

        private StateRestore() {
            this.program = glGetInteger(GL_CURRENT_PROGRAM);
            this.srcRGB = glGetInteger(GL_BLEND_SRC_RGB);
            this.dstRGB = glGetInteger(GL_BLEND_DST_RGB);
            this.srcAlpha = glGetInteger(GL_BLEND_SRC_ALPHA);
            this.dstAlpha = glGetInteger(GL_BLEND_DST_ALPHA);
            this.cullFaceEnabled = glIsEnabled(GL_CULL_FACE);
            this.cullFace = glGetInteger(GL_CULL_FACE_MODE);
            this.frontFace = glGetInteger(GL_FRONT_FACE);
            this.blendEnabled = glIsEnabled(GL_BLEND);
            this.depthTestEnabled = glIsEnabled(GL_DEPTH_TEST);
            this.scissorTestEnabled = glIsEnabled(GL_SCISSOR_TEST);
            try (MemoryStack stack = MemoryStack.stackPush()) {
                ByteBuffer colorMaskTmp = stack.malloc(4);
                glGetBooleanv(GL_COLOR_WRITEMASK, colorMaskTmp);
                this.colorMaskRed = colorMaskTmp.get(0) != GL_FALSE;
                this.colorMaskGreen = colorMaskTmp.get(1) != GL_FALSE;
                this.colorMaskBlue = colorMaskTmp.get(2) != GL_FALSE;
                this.colorMaskAlpha = colorMaskTmp.get(3) != GL_FALSE;
            }
            this.stencilMask = glGetInteger(GL_STENCIL_WRITEMASK);
            this.sfail = glGetInteger(GL_STENCIL_FAIL);
            this.dpfail = glGetInteger(GL_STENCIL_PASS_DEPTH_FAIL);
            this.dppass = glGetInteger(GL_STENCIL_PASS_DEPTH_PASS);
            this.stencilFunc = glGetInteger(GL_STENCIL_FUNC);
            this.stencilRef = glGetInteger(GL_STENCIL_REF);
            this.stencilValueMask = glGetInteger(GL_STENCIL_VALUE_MASK);
            // make sure we read texture 0, which nanovg binds to
            RenderSystem.activeTexture(GL_TEXTURE0);
            this.uniformBuffer = glGetInteger(GL_UNIFORM_BUFFER_BINDING);
            this.vertexArray = glGetInteger(GL_VERTEX_ARRAY_BINDING);
            this.arrayBuffer = glGetInteger(GL_ARRAY_BUFFER_BINDING);
            this.texture2D = glGetInteger(GL_TEXTURE_2D);
        }

        @Override
        public void close() {
            glUseProgram(program);
            glBlendFuncSeparate(srcRGB, dstRGB, srcAlpha, dstAlpha);
            glSetEnabled(GL_CULL_FACE, cullFaceEnabled);
            glCullFace(cullFace);
            glFrontFace(frontFace);
            glSetEnabled(GL_BLEND, blendEnabled);
            glSetEnabled(GL_DEPTH_TEST, depthTestEnabled);
            glSetEnabled(GL_SCISSOR_TEST, scissorTestEnabled);
            glColorMask(colorMaskRed, colorMaskGreen, colorMaskBlue, colorMaskAlpha);
            glStencilMask(stencilMask);
            glStencilOp(sfail, dpfail, dppass);
            glStencilFunc(stencilFunc, stencilRef, stencilValueMask);
            // nanovg already sets this, so make sure GlStateManager thinks the same
            RenderSystem.activeTexture(GL_TEXTURE0);
            glBindBuffer(GL_UNIFORM_BUFFER, uniformBuffer);
            glBindVertexArray(vertexArray);
            glBindBuffer(GL_ARRAY_BUFFER, arrayBuffer);
            glBindTexture(GL_TEXTURE_2D, texture2D);
            //glUniformBlockBinding(... , GLNVG_FRAG_BINDING); TODO: not used in vanilla, but might be elsewhere
        }
    }

    public static void glSetEnabled(int cap, boolean state) {
        if (state)
            glEnable(cap);
        else
            glDisable(cap);
    }

}

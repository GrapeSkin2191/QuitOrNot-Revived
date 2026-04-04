package skin.grape.quitornot.client.toast;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;

import java.awt.*;

public final class QuitToast extends BaseToast {
    public QuitToast(Component message, long keepTime) {
        super(Component.translatable("toast.quitornot.confirm.title"), message, keepTime);
    }

    @Override
    public int width() {
        return 241;
    }

    @Override
    protected void drawToast(GuiGraphicsExtractor context, Font font) {
        context.blit(RenderPipelines.GUI_TEXTURED, texture, 0, 0, 0, 0, width(), height(), 256, 256);
        context.blit(RenderPipelines.GUI_TEXTURED, texture, 8, 0, 241, 0, 15, 30, 256, 256);
        context.text(font, title, 35, 7, Color.WHITE.getRGB());
        context.text(font, message, 35, 18, Color.WHITE.getRGB());
    }
}

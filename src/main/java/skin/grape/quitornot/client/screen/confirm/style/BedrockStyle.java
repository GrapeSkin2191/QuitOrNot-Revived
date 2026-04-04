package skin.grape.quitornot.client.screen.confirm.style;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import skin.grape.quitornot.client.config.Config;

public final class BedrockStyle extends BaseStyle {
    private static final Identifier WINDOW_TEXTURE = Identifier.fromNamespaceAndPath("quitornot", "textures/gui/bedrock/window.png");
    private static final Identifier BACKGROUND = Identifier.withDefaultNamespace("textures/block/dirt.png");

    // 窗口宽度
    private static final int windowWidth = 252;
    // 窗口长度
    private static final int windowHeight = 140;
    // 按钮旁边距
    private static final int buttonLRMargin = 20;
    // 按钮下边距
    private static final int buttonBMargin = 20;
    // 按钮宽度
    private static final int buttonWidth = 100;
    // 按钮长度
    private static final int buttonHeight = 20;
    // 信息文本下边距
    private static final int messageBMargin = 100;

    @Override
    public Button generateConfirmButtons(Screen screen, Button.OnPress onConfirm) {
        return Button.builder(CommonComponents.GUI_YES, onConfirm)
                .bounds((screen.width - windowWidth) / 2 + (Config.config.swapButtons ? (windowWidth - buttonWidth - buttonLRMargin) : buttonLRMargin),
                        (screen.height - windowHeight) / 2 + windowHeight - buttonBMargin - buttonHeight,
                        buttonWidth, buttonHeight).build();
    }

    @Override
    public Button generateCancelButtons(Screen screen, Button.OnPress onCancel) {
        return Button.builder(CommonComponents.GUI_NO, onCancel)
                .bounds((screen.width - windowWidth) / 2 + (Config.config.swapButtons ? buttonLRMargin : (windowWidth - buttonWidth - buttonLRMargin)),
                        (screen.height - windowHeight) / 2 + windowHeight - buttonBMargin - buttonHeight,
                        buttonWidth, buttonHeight).build();
    }

    @Override
    public void extractRenderState(Minecraft client, Font textRenderer, Screen screen, Component title, Component message, GuiGraphicsExtractor ctx, int mouseX, int mouseY, float delta) {
        drawWindow(textRenderer, title, ctx, (screen.width - windowWidth) / 2, (screen.height - windowHeight) / 2);
        drawMessage(textRenderer, screen, message, ctx);
    }

    private void drawWindow(Font textRenderer, Component title, GuiGraphicsExtractor ctx, int x, int y) {
        drawBackground(ctx, x, y + 16, x + windowWidth, y + windowHeight - 8);
        ctx.blit(RenderPipelines.GUI_TEXTURED, WINDOW_TEXTURE, x, y, 0, 0, 252, 140, 256, 256);
        ctx.text(textRenderer, title, x + 8, y + 6, ARGB.opaque(4210752), false);
    }

    private void drawBackground(GuiGraphicsExtractor ctx, int startX, int startY, int endX, int endY) {
        for (int x = startX; x < endX; x += 32) {
            int width = Math.min(endX - x, 32);
            for (int y = startY; y < endY; y += 32) {
                int height = Math.min(endY - y, 32);
                ctx.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, x, y, 0.0F, 0.0F, width, height, width, height,
                        32, 32, ARGB.color(255, 64, 64, 64));
            }
        }
    }

    private void drawMessage(Font textRenderer, Screen screen, Component message, GuiGraphicsExtractor ctx) {
        ctx.centeredText(textRenderer, message,
                screen.width / 2,
                (screen.height - windowHeight) / 2 + windowHeight - messageBMargin,
                ARGB.opaque(10526880));
    }
}
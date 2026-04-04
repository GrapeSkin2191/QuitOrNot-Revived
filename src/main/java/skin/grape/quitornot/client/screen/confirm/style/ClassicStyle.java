package skin.grape.quitornot.client.screen.confirm.style;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import skin.grape.quitornot.client.config.Config;

public final class ClassicStyle extends BaseStyle {
    // 按钮宽度
    private static final int buttonWidth = 150;
    // 按钮长度
    private static final int buttonHeight = 20;
    // 按钮间隔
    private static final int buttonFMargin = 10;
    // 按钮下边距
    private static final int buttonBMargin = 20;
    // 标题上边距
    private static final int titleTMargin = 40;

    @Override
    public Button generateConfirmButtons(Screen screen, Button.OnPress onConfirm) {
        return Button.builder(CommonComponents.GUI_YES, onConfirm)
                .bounds(screen.width / 2 + (Config.config.swapButtons ? buttonFMargin : (- buttonWidth - buttonFMargin)),
                        screen.height / 2 + buttonBMargin,
                        buttonWidth, buttonHeight).build();
    }

    @Override
    public Button generateCancelButtons(Screen screen, Button.OnPress onCancel) {
        return Button.builder(CommonComponents.GUI_NO, onCancel)
                .bounds(screen.width / 2 + (Config.config.swapButtons ? (- buttonWidth - buttonFMargin) : buttonFMargin),
                        screen.height / 2 + buttonBMargin,
                        buttonWidth, buttonHeight).build();
    }

    @Override
    public void render(Minecraft client, Font textRenderer, Screen screen, Component title, Component message, GuiGraphics ctx, int mouseX, int mouseY, float delta) {
        this.renderBackground(ctx, screen);
        drawTextAndMessage(textRenderer, screen, title, message, ctx);
    }

    public void renderBackground(GuiGraphics ctx, Screen screen) {
//        screen.renderTransparentBackground(ctx);
    }

    private void drawTextAndMessage(Font textRenderer, Screen screen, Component title, Component message, GuiGraphics ctx) {
        ctx.drawCenteredString(textRenderer, title,
                screen.width / 2,
                titleTMargin,
                -1);
        ctx.drawCenteredString(textRenderer, message,
                screen.width / 2,
                screen.height / 2 - 60,
                -1);
    }
}
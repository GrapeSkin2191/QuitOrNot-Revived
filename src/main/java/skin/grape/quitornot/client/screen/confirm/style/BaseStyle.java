package skin.grape.quitornot.client.screen.confirm.style;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public abstract class BaseStyle {
    public abstract Button generateConfirmButtons(Screen screen, Button.OnPress onConfirm);

    public abstract Button generateCancelButtons(Screen screen, Button.OnPress onCancel);

    public abstract void extractRenderState(Minecraft client, Font textRenderer, Screen screen, Component title, Component message,
                                            GuiGraphicsExtractor ctx, int mouseX, int mouseY, float delta);
}
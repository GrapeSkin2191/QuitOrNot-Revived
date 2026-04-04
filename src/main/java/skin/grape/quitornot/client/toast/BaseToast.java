package skin.grape.quitornot.client.toast;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public abstract class BaseToast implements Toast {
    protected static final Identifier texture = Identifier.fromNamespaceAndPath("quitornot", "textures/gui/toasts.png");
    protected final Component title;
    protected final Component message;
    protected final long keepTime;
    private Visibility wantedVisibility;

    protected BaseToast(Component title, Component message, long keepTime) {
        this.title = title;
        this.message = message;
        this.keepTime = keepTime;
    }

    public Visibility getWantedVisibility() {
        return this.wantedVisibility;
    }

    @Override
    public void update(ToastManager toastManager, long startTime) {
        if (startTime >= keepTime) {
            this.wantedVisibility = Visibility.HIDE;
        } else {
            this.wantedVisibility = Visibility.SHOW;
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphicsExtractor, Font font, long startTime) {
        drawToast(guiGraphicsExtractor, font);
    }

    protected abstract void drawToast(GuiGraphicsExtractor context, Font font);
}

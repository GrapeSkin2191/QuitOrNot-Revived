package skin.grape.quitornot.client.mixin;

import com.mojang.blaze3d.platform.Window;
import skin.grape.quitornot.client.event.ClientScheduleStopEvent;
import skin.grape.quitornot.client.event.EventResult;
import skin.grape.quitornot.client.screen.confirm.ConfirmScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Shadow
    @Final
    private Window window;

    @Shadow
    @Nullable
    public Screen screen;

    @Inject(method = "stop", at = @At("HEAD"), cancellable = true)
    private void qon$inject$scheduleStop(CallbackInfo ci) {
        if (screen instanceof ConfirmScreen) {
            if (((ConfirmScreen) screen).isConfirmed()) {
                return;
            }
            ci.cancel();
            return;
        }
        EventResult result = ClientScheduleStopEvent.CLIENT_SCHEDULE_STOP.invoker().onScheduleStop();
        if (result == EventResult.CANCEL) {
            GLFW.glfwSetWindowShouldClose(this.window.handle(), false);
            ci.cancel();
        }
    }
}

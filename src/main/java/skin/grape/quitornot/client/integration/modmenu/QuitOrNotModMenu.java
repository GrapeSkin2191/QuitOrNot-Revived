package skin.grape.quitornot.client.integration.modmenu;

import skin.grape.quitornot.client.integration.modmenu.screen.SettingScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public final class QuitOrNotModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return SettingScreen::new;
    }
}

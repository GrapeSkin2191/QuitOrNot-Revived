package skin.grape.quitornot.client.integration.modmenu.screen;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;

import java.util.Collections;
import java.util.List;

public final class SettingElementListWidget extends ContainerObjectSelectionList<SettingElementListWidget.Entry> {
    public SettingElementListWidget(Minecraft minecraftClient, int i, int j, int k, int l, int m) {
        //super(minecraftClient, i, j, k, l, m);
        super(minecraftClient, i, j, k, m);
    }

    @Override
    public int addEntry(Entry entry) {
        return super.addEntry(entry);
    }

    public class CategoryEntry extends Entry {
        private final Component text;
        private final int textWidth;
        private final int textColor;

        public CategoryEntry(Component text, int color) {
            this.text = text;
            this.textWidth = SettingElementListWidget.this.minecraft.font.width(text);
            this.textColor = color;
        }

        @Override
        public List<? extends NarratableEntry> narratables() {
            return ImmutableList.of(new NarratableEntry() {
                public NarrationPriority narrationPriority() {
                    return NarrationPriority.HOVERED;
                }

                public void updateNarration(NarrationElementOutput builder) {
                    builder.add(NarratedElementType.TITLE, text);
                }
            });
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return Collections.emptyList();
        }

        @Override
        public void extractContent(GuiGraphicsExtractor ctx, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            assert minecraft.screen != null;
            int dx = minecraft.screen.width / 2 - this.textWidth / 2;
            int dy = this.getContentY() + this.getContentHeight();
            ctx.text(minecraft.font, this.text, dx, dy - minecraft.font.lineHeight, textColor, true);
        }
    }

    public class InputListEntry extends Entry {
        private final EditBox fieldWidget;
        private final Component describeText;

        public InputListEntry(EditBox fieldWidget, Component describeText) {
            this.fieldWidget = fieldWidget;
            this.describeText = describeText;
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return Collections.singletonList(fieldWidget);
        }

        @Override
        public void extractContent(GuiGraphicsExtractor ctx, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            ctx.text(minecraft.font, describeText, this.getContentX(), (int) (this.getContentY() + minecraft.font.lineHeight / 2.0),
                    ARGB.opaque(16777215), true);
            this.fieldWidget.setX(this.getContentWidth() - fieldWidget.getWidth() + this.getContentX());
            this.fieldWidget.setY(this.getContentY());
            this.fieldWidget.extractRenderState(ctx, mouseX, mouseY, tickDelta);
        }

        @Override
        public List<? extends NarratableEntry> narratables() {
            return Collections.singletonList(fieldWidget);
        }
    }


    public class ButtonListEntry extends Entry {
        private final Button button;
        private final Component describeText;

        public ButtonListEntry(Button settleButton, Component describeText) {
            this.button = settleButton;
            this.describeText = describeText;
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return Collections.singletonList(button);
        }

        @Override
        public void extractContent(GuiGraphicsExtractor ctx, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            ctx.text(minecraft.font, describeText, this.getContentX(), this.getContentY() + 5, ARGB.opaque(16777215), true);
            this.button.setX(this.getContentWidth() - button.getWidth() + this.getContentX());
            this.button.setY(this.getContentY());
            this.button.extractRenderState(ctx, mouseX, mouseY, tickDelta);
        }

        @Override
        public boolean mouseClicked(MouseButtonEvent mouseButtonEvent, boolean bl) {
            return this.button.mouseClicked(mouseButtonEvent, bl);
        }

        @Override
        public boolean mouseReleased(MouseButtonEvent mouseButtonEvent) {
            return this.button.mouseReleased(mouseButtonEvent);
        }

        @Override
        public List<? extends NarratableEntry> narratables() {
            return Collections.singletonList(button);
        }
    }

    public abstract static class Entry extends ContainerObjectSelectionList.Entry<Entry> {
    }
}

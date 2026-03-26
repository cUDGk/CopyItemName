package com.copyitemname;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class CopyConfigScreen extends Screen {
    private final CopyConfig config;
    private final Screen parent;
    private ButtonWidget modeButton;

    public CopyConfigScreen(CopyConfig config, Screen parent) {
        super(MinecraftClient.getInstance(), MinecraftClient.getInstance().textRenderer,
                Text.literal("Copy Item Name - Settings"));
        this.config = config;
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = width / 2;
        int centerY = height / 2;

        // Mode toggle button
        modeButton = ButtonWidget.builder(
                getModeText(),
                btn -> {
                    config.copyMode = config.copyMode.next();
                    btn.setMessage(getModeText());
                }
        ).dimensions(centerX - 100, centerY - 20, 200, 20).build();
        addDrawableChild(modeButton);

        // Save & Close
        addDrawableChild(ButtonWidget.builder(
                Text.literal("\u00a7a Save & Close"),
                btn -> {
                    config.save();
                    close();
                }
        ).dimensions(centerX - 60, centerY + 20, 120, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderInGameBackground(context);

        int centerX = width / 2;
        int centerY = height / 2;

        // Title
        context.drawCenteredTextWithShadow(textRenderer,
                getTitle(), centerX, centerY - 60, 0xFFFFFF);

        // Label
        context.drawCenteredTextWithShadow(textRenderer,
                "\u00a77Middle-click copies:", centerX, centerY - 35, 0xAAAAAA);

        // Description of each mode
        int descY = centerY + 48;
        context.drawCenteredTextWithShadow(textRenderer,
                "\u00a78\u00a7oEnglish Name: Trident, Diamond Sword, Stone ...",
                centerX, descY, 0x666666);
        context.drawCenteredTextWithShadow(textRenderer,
                "\u00a78\u00a7oItem ID: minecraft:trident, minecraft:diamond_sword ...",
                centerX, descY + 12, 0x666666);
        context.drawCenteredTextWithShadow(textRenderer,
                "\u00a78\u00a7oComponent: minecraft:trident[enchantments={...}] ...",
                centerX, descY + 24, 0x666666);

        super.render(context, mouseX, mouseY, delta);
    }

    private Text getModeText() {
        String label = switch (config.copyMode) {
            case ENGLISH_NAME -> "\u00a7e\u00a7l English Name \u00a77(Trident)";
            case ITEM_ID -> "\u00a7b\u00a7l Item ID \u00a77(minecraft:trident)";
            case COMPONENT -> "\u00a7d\u00a7l Component \u00a77(minecraft:trident[...])";
        };
        return Text.literal(label);
    }

    @Override
    public void close() {
        if (client != null) {
            client.setScreen(parent);
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}

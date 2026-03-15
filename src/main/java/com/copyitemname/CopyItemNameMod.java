package com.copyitemname;

import com.copyitemname.mixin.HandledScreenMixin;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CopyItemNameMod implements ClientModInitializer {
    private CopyConfig config;

    @Override
    public void onInitializeClient() {
        config = CopyConfig.getInstance();

        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES)
            .registerReloadListener(new SimpleSynchronousResourceReloadListener() {
                @Override
                public Identifier getFabricId() {
                    return Identifier.of("copyitemname", "english_lang");
                }

                @Override
                public void reload(ResourceManager manager) {
                    EnglishNameHelper.loadTranslations(manager);
                }
            });

        ScreenEvents.BEFORE_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof HandledScreen<?>) {
                ScreenMouseEvents.beforeMouseClick(screen).register((scr, click) -> {
                    if (click.button() == 2 && scr instanceof HandledScreen<?> handledScreen) {
                        Slot slot = ((HandledScreenMixin) handledScreen).getFocusedSlot();
                        if (slot != null && slot.hasStack()) {
                            ItemStack stack = slot.getStack();
                            String copied;

                            if (config.copyMode == CopyMode.ITEM_ID) {
                                // Copy the item ID (e.g. "minecraft:trident")
                                copied = Registries.ITEM.getId(stack.getItem()).toString();
                            } else {
                                // Copy the English name
                                if (stack.isOf(Items.ENCHANTED_BOOK)) {
                                    copied = getEnchantedBookName(stack);
                                } else {
                                    String key = stack.getItem().getTranslationKey();
                                    copied = EnglishNameHelper.getEnglishName(key);
                                }
                            }

                            client.keyboard.setClipboard(copied);
                            if (client.player != null) {
                                String modeTag = config.copyMode == CopyMode.ITEM_ID
                                        ? "\u00a7b[ID] " : "\u00a7e[Name] ";
                                client.player.sendMessage(
                                    Text.literal("\u00a7aCopied: " + modeTag + "\u00a7f" + copied), true
                                );
                            }
                        }
                    }
                });
            }
        });

    }

    private static String getEnchantedBookName(ItemStack stack) {
        ItemEnchantmentsComponent enchants = stack.get(DataComponentTypes.STORED_ENCHANTMENTS);
        if (enchants == null || enchants.isEmpty()) {
            return EnglishNameHelper.getEnglishName(stack.getItem().getTranslationKey());
        }

        StringBuilder sb = new StringBuilder();
        for (RegistryEntry<Enchantment> entry : enchants.getEnchantments()) {
            if (!sb.isEmpty()) sb.append(", ");
            String enchantKey = entry.value().description().getString();
            // Try to get English name from translation key
            String transKey = entry.getIdAsString();
            // Format: minecraft:sharpness -> enchantment.minecraft.sharpness
            String langKey = "enchantment." + transKey.replace(":", ".");
            String englishName = EnglishNameHelper.getEnglishName(langKey);
            int level = enchants.getLevel(entry);
            if (englishName.equals(langKey)) {
                // Fallback to the displayed name
                englishName = enchantKey;
            }
            sb.append(englishName);
            if (level > 1 || entry.value().getMaxLevel() > 1) {
                sb.append(" ").append(toRoman(level));
            }
        }
        return sb.toString();
    }

    private static String toRoman(int num) {
        return switch (num) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            default -> String.valueOf(num);
        };
    }
}

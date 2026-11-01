package dev.customgui;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reagiert auf die Chat-Meldung "... hat 1x [Item] zur Auktion freigegeben" und schreibt den Preis
 * in den Chat. Der Preis wird aus der Hover-Beschreibung des Items in der Meldung gelesen.
 */
public final class AuctionPrice {

    private static final String TRIGGER = "zur Auktion freigegeben";
    private static final Pattern BRACKET = Pattern.compile("\\[([^\\]]+)\\]");
    private static boolean warned = false;

    private AuctionPrice() {
    }

    /** Sammelt beim Durchlaufen der Meldung Name und Preis. */
    private static final class Found {
        String name;
        String price;
    }

    public static void onMessage(Component message, boolean overlay) {
        if (overlay || !CustomGuiConfig.get().auctionPrice) return;
        String plain = message.getString();
        if (!plain.contains(TRIGGER)) return;

        Found f = new Found();
        scan(message, f);
        if (f.name == null) {
            Matcher m = BRACKET.matcher(plain);
            f.name = m.find() ? m.group(1) : "?";
        }

        Minecraft mc = Minecraft.getInstance();
        String name = f.name;
        String price = f.price;
        // Eine Tick später, damit unsere Zeile nach der Original-Meldung im Chat steht.
        mc.execute(() -> {
            if (mc.player == null) return;
            if (price == null) {
                if (!warned) {
                    warned = true;
                    mc.player.sendSystemMessage(Component.literal("[" + Lang.t("auc.prefix") + "] "
                            + Lang.t("auc.noprice")).withStyle(ChatFormatting.GRAY));
                }
                return;
            }
            Component out = Component.literal("[" + Lang.t("auc.prefix") + "] ").withStyle(ChatFormatting.GOLD)
                    .append(Component.literal(name + ": ").withStyle(ChatFormatting.WHITE))
                    .append(Component.literal(price).withStyle(ChatFormatting.YELLOW));
            mc.player.sendSystemMessage(out);
        });
    }

    private static void scan(Component c, Found f) {
        HoverEvent hover = c.getStyle().getHoverEvent();
        if (hover instanceof HoverEvent.ShowItem show) {
            ItemStack stack = show.item();
            if (f.name == null) {
                f.name = stack.getHoverName().getString();
            }
            ItemLore lore = stack.get(DataComponents.LORE);
            if (lore != null) {
                for (Component line : lore.lines()) {
                    takePrice(line.getString(), f);
                }
            }
        } else if (hover instanceof HoverEvent.ShowText text) {
            for (String line : text.value().getString().split("\n")) {
                takePrice(line, f);
            }
        }
        for (Component sibling : c.getSiblings()) {
            scan(sibling, f);
        }
    }

    private static void takePrice(String line, Found f) {
        if (f.price != null) return;
        String s = line.trim();
        String lower = s.toLowerCase(Locale.ROOT);
        if (lower.contains("preis") || lower.contains("münzen") || lower.contains("muenzen") || lower.contains("coins")) {
            f.price = s;
        }
    }
}

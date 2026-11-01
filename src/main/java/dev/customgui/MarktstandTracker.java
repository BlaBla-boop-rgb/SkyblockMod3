package dev.customgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Liest bei geöffnetem Marktstand die wartenden Kunden aus und merkt sich, wie viel von welchem
 * Produkt noch fehlt. Gelesen werden die Lore-Zeilen "Produkt: ..." und "Beliefert: x/y".
 */
public final class MarktstandTracker {

    public record Entry(String product, long missing) {
    }

    private static final Pattern PROGRESS = Pattern.compile("([\\d.]+)\\s*/\\s*([\\d.]+)");
    private static List<Entry> entries = List.of();

    private MarktstandTracker() {
    }

    public static List<Entry> entries() {
        return entries;
    }

    public static void clear() {
        entries = List.of();
    }

    /** Wird jeden Client-Tick aufgerufen, tut aber nur etwas, wenn der Marktstand offen ist. */
    public static void tick(Minecraft mc) {
        if (mc.player == null) return;
        if (!(mc.screen instanceof AbstractContainerScreen<?> screen)) return;
        if (!screen.getTitle().getString().toLowerCase(Locale.ROOT).contains("marktstand")) return;

        Map<String, Long> sum = new LinkedHashMap<>();
        boolean sawCustomer = false;
        for (Slot slot : screen.getMenu().slots) {
            ItemStack stack = slot.getItem();
            if (stack.isEmpty()) continue;
            ItemLore lore = stack.get(DataComponents.LORE);
            if (lore == null) continue;

            String product = null;
            long delivered = -1;
            long total = -1;
            for (Component line : lore.lines()) {
                String s = line.getString().trim();
                int p = s.indexOf("Produkt:");
                if (p >= 0) {
                    product = s.substring(p + "Produkt:".length()).trim();
                }
                if (s.contains("Beliefert:")) {
                    Matcher m = PROGRESS.matcher(s);
                    if (m.find()) {
                        delivered = num(m.group(1));
                        total = num(m.group(2));
                    }
                }
            }
            if (product != null && !product.isEmpty() && total >= 0) {
                sawCustomer = true;
            }
            if (product != null && !product.isEmpty() && delivered >= 0 && total > delivered) {
                sum.merge(product, total - delivered, Long::sum);
            }
        }

        // Menü lädt noch (Kunden-Slots leer): alte Liste behalten statt kurz leeren
        if (!sawCustomer) return;

        List<Entry> list = new ArrayList<>();
        sum.forEach((k, v) -> list.add(new Entry(k, v)));
        entries = list;
    }

    private static long num(String s) {
        try {
            return Long.parseLong(s.replace(".", ""));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

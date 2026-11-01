package dev.customgui;

import net.minecraft.ChatFormatting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

import java.util.List;

/** Kleines Fenster auf dem Bildschirm mit den Items, die die Marktstand-Kunden noch brauchen. */
public final class MarktstandHud {

    private static final int MAX_ROWS = 8;
    private static final int BG = 0xD00C111D;
    private static final int BORDER = 0xFF1F2B45;
    private static final int TEXT = 0xFFE6EAF2;
    private static final int MUTED = 0xFF8A94AB;

    private MarktstandHud() {
    }

    /** preview = true zeigt Beispieldaten, wenn noch nichts gelesen wurde (zum Verschieben). */
    public static List<MarktstandTracker.Entry> shown(boolean preview) {
        List<MarktstandTracker.Entry> list = MarktstandTracker.entries();
        if (list.isEmpty() && preview) {
            return List.of(new MarktstandTracker.Entry("Magmacreme", 62),
                    new MarktstandTracker.Entry("Weizen", 128));
        }
        return list;
    }

    public static int[] size(Font font, List<MarktstandTracker.Entry> list) {
        int shownRows = Math.min(list.size(), MAX_ROWS);
        int rows = shownRows + (list.size() > MAX_ROWS ? 1 : 0);
        int w = font.width(Lang.t("hud.title"));
        for (int i = 0; i < shownRows; i++) {
            MarktstandTracker.Entry e = list.get(i);
            w = Math.max(w, font.width(e.product()) + 16 + font.width("x" + e.missing()));
        }
        return new int[]{w + 16, 22 + rows * 11 + 4};
    }

    /** Position aus der Config; hudX = -1 heißt: rechts oben. Wird auf den Bildschirm begrenzt. */
    public static int[] position(Minecraft mc, int w, int h) {
        CustomGuiConfig c = CustomGuiConfig.get();
        int sw = mc.getWindow().getGuiScaledWidth();
        int sh = mc.getWindow().getGuiScaledHeight();
        int x = c.hudX < 0 ? sw - w - 6 : c.hudX;
        int y = c.hudY;
        x = Math.max(0, Math.min(Math.max(0, sw - w), x));
        y = Math.max(0, Math.min(Math.max(0, sh - h), y));
        return new int[]{x, y};
    }

    public static void draw(GuiGraphicsExtractor gfx, Font font, int x, int y, List<MarktstandTracker.Entry> list,
                            int w, int h, int accentRgb) {
        int accent = 0xFF000000 | accentRgb;
        gfx.fill(x, y, x + w, y + h, BORDER);
        gfx.fill(x + 1, y + 1, x + w - 1, y + h - 1, BG);
        gfx.fill(x + 1, y + 17, x + w - 1, y + 18, accent);
        gfx.text(font, Component.literal(Lang.t("hud.title")).withStyle(ChatFormatting.BOLD), x + 8, y + 5, TEXT);

        int shownRows = Math.min(list.size(), MAX_ROWS);
        int ry = y + 22;
        for (int i = 0; i < shownRows; i++) {
            MarktstandTracker.Entry e = list.get(i);
            gfx.text(font, Component.literal(e.product()), x + 8, ry, TEXT);
            Component count = Component.literal("x" + e.missing());
            gfx.text(font, count, x + w - 8 - font.width(count), ry, accent);
            ry += 11;
        }
        if (list.size() > MAX_ROWS) {
            gfx.text(font, Component.literal(Lang.t("hud.more", list.size() - MAX_ROWS)), x + 8, ry, MUTED);
        }
    }

    /** Wird von Fabric jeden Frame aufgerufen. */
    public static void render(GuiGraphicsExtractor gfx, DeltaTracker delta) {
        Minecraft mc = Minecraft.getInstance();
        CustomGuiConfig cfg = CustomGuiConfig.get();
        if (!cfg.hudEnabled || mc.player == null || mc.options.hideGui) return;
        if (mc.screen instanceof HudMoveScreen) return; // dort wird die Vorschau gezeichnet

        List<MarktstandTracker.Entry> list = shown(false);
        if (list.isEmpty()) return;
        int[] s = size(mc.font, list);
        int[] p = position(mc, s[0], s[1]);
        draw(gfx, mc.font, p[0], p[1], list, s[0], s[1], CustomGuiConfig.ACCENTS[cfg.accent]);
    }
}

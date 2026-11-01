package dev.customgui;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/** Einfache Übersetzung für das Menü (Deutsch / Englisch), unabhängig von der Minecraft-Sprache. */
public final class Lang {

    public static final int DE = 0;
    public static final int EN = 1;

    private static final Map<String, String[]> T = new HashMap<>();

    private static void a(String key, String de, String en) {
        T.put(key, new String[]{de, en});
    }

    static {
        a("title.menu", "Skyblock", "Skyblock");
        a("hint.shift", "Shift halten + Maus \u00FCber ein Element = Erkl\u00E4rung", "Hold Shift + hover = explanation");
        a("on", "An", "On");
        a("off", "Aus", "Off");

        // Tabs
        a("tab.home", "Start", "Home");
        a("tab.islands", "Inseln", "Islands");
        a("tab.settings", "Einstellungen", "Settings");
        a("tip.tab.home", "Schnellzugriff: Spawn und Shop.", "Quick access: spawn and shop.");
        a("tip.tab.islands", "Deine gespeicherten Inseln und Warps mit dem Befehl, der dich hinbringt.", "Your saved islands and warps with the command that takes you there.");
        a("tip.tab.settings", "Taste, Design und was im Men\u00FC sichtbar ist.", "Key, design and what is visible in the menu.");

        // Start
        a("home.spawn", "Spawn", "Spawn");
        a("tip.spawn", "F\u00FChrt /spawn aus und bringt dich zum Skyblock-Spawn.", "Runs /spawn and takes you to the Skyblock spawn.");
        a("home.shop", "Shop", "Shop");
        a("sub.browser", "Im Browser", "In browser");
        a("tip.shop", "\u00D6ffnet den Cytooxien-Shop im Standard-Browser.", "Opens the Cytooxien shop in your default browser.");
        a("home.close", "Schlie\u00DFen", "Close");
        a("tip.close", "Schlie\u00DFt dieses Men\u00FC.", "Closes this menu.");
        a("home.empty1", "Alles ausgeblendet.", "Everything is hidden.");
        a("home.empty2", "Unter Einstellungen > Anzeigen wieder einblenden.", "Show it again under Settings > Display.");

        // Inseln
        a("isl.empty1", "Noch keine Inseln gespeichert.", "No islands saved yet.");
        a("isl.empty2", "F\u00FCge deine erste Insel hinzu!", "Add your first island!");
        a("isl.allhidden1", "Alle Inseln sind ausgeblendet.", "All islands are hidden.");
        a("isl.allhidden2", "Mit \u201EBearbeiten\u201C kannst du sie wieder einblenden.", "Use \"Edit\" to show them again.");
        a("isl.add", "+ Hinzuf\u00FCgen", "+ Add");
        a("tip.isl.add", "Speichert eine neue Insel mit dem Befehl, der dich dorthin bringt.", "Saves a new island together with the command that takes you there.");
        a("isl.edit", "Bearbeiten", "Edit");
        a("isl.done", "Fertig", "Done");
        a("tip.isl.edit", "Zeigt Buttons zum Ausblenden und L\u00F6schen. Ausgeblendete Inseln sind sonst nicht sichtbar.", "Shows buttons to hide and delete. Hidden islands are otherwise not shown.");
        a("tip.isl.done", "Beendet den Bearbeiten-Modus.", "Leaves edit mode.");
        a("tip.isl.go", "Klicke, um dich zu %s zu bringen.", "Click to travel to %s.");
        a("tip.isl.editing", "Bearbeiten-Modus: Zum Reisen erst auf \u201EFertig\u201C klicken.", "Edit mode: click \"Done\" first to travel.");
        a("isl.hide", "Aus", "Hide");
        a("isl.show", "An", "Show");
        a("tip.isl.hide", "Blendet diese Insel aus. Sie bleibt gespeichert.", "Hides this island. It stays saved.");
        a("tip.isl.show", "Blendet diese Insel wieder ein.", "Shows this island again.");
        a("tip.isl.remove", "L\u00F6scht diese Insel dauerhaft aus der Liste.", "Permanently deletes this island from the list.");
        a("tip.page.prev", "Vorherige Seite.", "Previous page.");
        a("tip.page.next", "N\u00E4chste Seite.", "Next page.");
        a("form.name", "Name der Insel", "Island name");
        a("form.cmd", "Befehl (zum Hinkommen)", "Command (to get there)");
        a("form.name.hint", "z. B. Markshop", "e.g. Markshop");
        a("form.cmd.hint", "z. B. /warp Markshop", "e.g. /warp Markshop");
        a("form.save", "Speichern", "Save");
        a("tip.form.save", "Speichert die Insel.", "Saves the island.");
        a("form.cancel", "Abbrechen", "Cancel");
        a("tip.form.cancel", "Zur\u00FCck zur Liste.", "Back to the list.");
        a("form.err.name", "Bitte gib einen Namen ein.", "Please enter a name.");
        a("form.err.cmd", "Bitte gib einen Befehl ein.", "Please enter a command.");
        a("err.shop", "Shop-Link konnte nicht ge\u00F6ffnet werden: %s", "Could not open the shop link: %s");
        a("err.http", "Es sind nur http(s)-Links erlaubt", "Only http(s) links are allowed");

        // Einstellungen
        a("set.keysec", "Tastenbelegung", "Key binding");
        a("set.looksec", "Darstellung", "Appearance");
        a("set.showsec", "Anzeigen", "Display");
        a("set.key", "Men\u00FC-Taste", "Menu key");
        a("set.key.listen", "Dr\u00FCcke Taste / Maus ...", "Press key / mouse ...");
        a("tip.set.key", "Klicken, dann eine Taste oder Maustaste dr\u00FCcken (links/rechts m\u00F6glich). Esc bricht ab. Achtung: Maustasten k\u00F6nnen mit Angriff/Benutzen kollidieren.",
                "Click, then press a key or mouse button (left/right possible). Esc cancels. Note: mouse buttons can clash with attack/use.");
        a("set.key.reset", "Standard", "Default");
        a("tip.set.key.reset", "Setzt die Men\u00FC-Taste auf H zur\u00FCck.", "Resets the menu key to H.");
        a("set.lang", "Sprache", "Language");
        a("lang.name", "Deutsch", "English");
        a("tip.set.lang", "Wechselt die Sprache des Men\u00FCs zwischen Deutsch und Englisch.", "Switches the menu language between German and English.");
        a("set.accent", "Akzent", "Accent");
        a("tip.set.accent", "Akzentfarbe des Men\u00FCs.", "Accent color of the menu.");
        a("accent.cyan", "Cyan", "Cyan");
        a("accent.green", "Gr\u00FCn", "Green");
        a("accent.purple", "Lila", "Purple");
        a("accent.gold", "Gold", "Gold");
        a("accent.pink", "Rosa", "Pink");
        a("accent.white", "Wei\u00DF", "White");
        a("set.layout", "Start-Tab", "Home tab");
        a("tip.set.layout", "Zeigt den Start-Tab als Liste oder als Kacheln.", "Shows the home tab as a list or as tiles.");
        a("layout.list", "Liste", "List");
        a("layout.tiles", "Kacheln", "Tiles");
        a("set.anim", "Animationen", "Animations");
        a("tip.set.anim", "Gleit- und Hover-Effekte ein- oder ausschalten.", "Turns slide and hover effects on or off.");
        a("set.showSpawn", "Spawn-Button", "Spawn button");
        a("tip.set.showSpawn", "Blendet den Spawn-Button im Start-Tab ein oder aus.", "Shows or hides the spawn button on the home tab.");
        a("set.showShop", "Shop-Button", "Shop button");
        a("tip.set.showShop", "Blendet den Shop-Button im Start-Tab ein oder aus.", "Shows or hides the shop button on the home tab.");
        a("set.showIslands", "Inseln-Tab", "Islands tab");
        a("tip.set.showIslands", "Blendet den ganzen Inseln-Tab aus. Deine gespeicherten Inseln bleiben erhalten.", "Hides the whole islands tab. Your saved islands are kept.");
        a("set.showHint", "Hinweiszeile", "Hint line");
        a("tip.set.showHint", "Die Zeile am unteren Rand, die auf die Shift-Erkl\u00E4rungen hinweist.", "The line at the bottom that points to the Shift explanations.");
        a("set.reset", "Design & Anzeige zur\u00FCcksetzen", "Reset design & display");
        a("tip.set.reset", "Setzt Akzent, Layout und Sichtbarkeit auf Standard. Inseln, Shop-Link und Taste bleiben.", "Resets accent, layout and visibility. Islands, shop link and key are kept.");

        // Extras: Marktstand-HUD und Auktions-Preis
        a("tab.markt", "Extras", "Extras");
        a("tip.tab.markt", "Marktstand-Anzeige und Auktions-Preis im Chat.", "Market stall display and auction price in chat.");
        a("mk.sec", "Marktstand", "Market stall");
        a("mk.hud", "Anzeige (HUD)", "Display (HUD)");
        a("tip.mk.hud", "Zeigt im Spiel, welche Items die Marktstand-Kunden noch brauchen. Die Liste aktualisiert sich selbst, sobald du den Marktstand \u00F6ffnest.", "Shows in game which items the market stall customers still need. The list updates itself whenever you open the market stall.");
        a("mk.move", "Position \u00E4ndern", "Change position");
        a("tip.mk.move", "\u00D6ffnet eine Ansicht, in der du das Fenster mit der Maus an eine beliebige Stelle ziehst.", "Opens a view where you drag the window anywhere with the mouse.");
        a("mk.auc.sec", "Auktionshaus", "Auction house");
        a("mk.auc", "Preis im Chat", "Price in chat");
        a("tip.mk.auc", "Schreibt den Preis in den Chat, wenn jemand ein Item zur Auktion freigibt.", "Writes the price to chat when someone puts an item up for auction.");
        a("mk.auc.desc", "Der Preis wird aus der Hover-Beschreibung des Items in der Chat-Meldung gelesen.", "The price is read from the item's hover text in the chat message.");
        a("auc.prefix", "Auktion", "Auction");
        a("auc.noprice", "Kein Preis in der Item-Beschreibung gefunden.", "No price found in the item description.");
        a("hud.title", "Marktstand: fehlt", "Market stall: missing");
        a("hud.more", "+%s weitere", "+%s more");
        a("move.hint", "Fenster ziehen \u00B7 Rechtsklick = Standard \u00B7 Esc = fertig", "Drag the window \u00B7 Right click = default \u00B7 Esc = done");
    }

    private Lang() {
    }

    public static String t(String key) {
        String[] v = T.get(key);
        if (v == null) return key;
        int l = CustomGuiConfig.get().language;
        return v[l == EN ? EN : DE];
    }

    public static String t(String key, Object... args) {
        return String.format(Locale.ROOT, t(key), args);
    }
}

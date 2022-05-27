package online.nasgar.announcer.common.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import online.nasgar.announcer.bungee.Main;

public class Utils {

    public static String formatWithPrefix(String s) {
        return ChatColor.translateAlternateColorCodes('&', "&8[&cAnnouncer&8] &8» &7" + s);
    }

    public static String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}

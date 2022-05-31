package online.nasgar.announcer.common.utils;

import net.md_5.bungee.api.ChatColor;

public class Utils {

    public static String formatWithPrefix(String s, String prefix) {
        return ChatColor.translateAlternateColorCodes('&', prefix + s);
    }

    public static String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}

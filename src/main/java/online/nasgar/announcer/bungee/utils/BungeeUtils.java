package online.nasgar.announcer.bungee.utils;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import online.nasgar.announcer.bungee.Main;

public class BungeeUtils {
    public static boolean isInBlacklistedServers(ProxiedPlayer p) {
        for (String server : Main.getConfiguration().getDeactivatedServers()) {
            if (p.getServer().getInfo().getName().equalsIgnoreCase(server))
                return true;
        }
        return false;
    }
}

package online.nasgar.announcer.bukkit.nms;

import org.bukkit.entity.Player;

public class LocaleSpigot implements iLocale {

    @Override
    public String getLocale(Player p) throws Exception {
        return p.getLocale();
    }
}

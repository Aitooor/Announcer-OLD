package online.nasgar.announcer.bukkit.announcements;

import me.clip.placeholderapi.PlaceholderAPI;
import online.nasgar.announcer.bukkit.Main;
import online.nasgar.announcer.common.announcements.AbstractAnnouncement;
import online.nasgar.announcer.common.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BukkitAnnouncement extends AbstractAnnouncement {
    public BukkitAnnouncement(String id) {
        super(id);
    }

    @Override
    public void execute() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            String msg = p.getLocale().split("_")[0].equalsIgnoreCase("es") ? this.getMsgEs() : this.getMsgEn();
            if (Main.getConfiguration().isPlaceholderApi())
                msg = PlaceholderAPI.setPlaceholders(p, msg);
            p.sendMessage(Utils.formatWithPrefix(msg, Main.getConfiguration().getPrefix()));
        }
        Bukkit.getConsoleSender().sendMessage(Utils.formatWithPrefix(this.getMsgEn(), Main.getConfiguration().getPrefix()));
    }
}

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
            String msg = Main.getLocale(p).split("_")[0].equalsIgnoreCase("es") ? this.getMsgEs() : this.getMsgEn();
            if (Main.getConfiguration().isPlaceholderApi())
                msg = PlaceholderAPI.setPlaceholders(p, msg);
            p.sendMessage(Utils.formatWithPrefix(msg, Main.getMessageHandler().get(p, "prefix")));
        }
        if (Main.getConfiguration().isConsoleBroadcast())
            Bukkit.getConsoleSender().sendMessage(Utils.formatWithPrefix(this.getMsgEn(), Main.getMessageHandler().get(Bukkit.getConsoleSender(), "prefix")));
    }
}

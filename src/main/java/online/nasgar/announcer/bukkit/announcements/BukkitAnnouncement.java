package online.nasgar.announcer.bukkit.announcements;

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
            p.sendMessage(Utils.format(Main.getConfiguration().getPrefix() +
                    (p.getLocale().split("_")[0].equalsIgnoreCase("es") ?
                            this.getMsgEs() : this.getMsgEn())));
        }
    }
}

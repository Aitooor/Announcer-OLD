package online.nasgar.announcer.bukkit.announcements;

import de.exlll.configlib.annotation.ConfigurationElement;
import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import online.nasgar.announcer.bukkit.Main;
import online.nasgar.announcer.common.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@ConfigurationElement
public class AutoAnnouncement {
    private String name;
    private String permission;
    private double delay;
    private List<String> messagesEn;
    private List<String> messagesEs;

    AutoAnnouncement() {
        this("", 0, "", Collections.singletonList(""), Collections.singletonList(""));
    }

    public AutoAnnouncement(String name, double delay, String permission, List<String> messagesEn, List<String> messagesEs) {
        this.name = name;
        this.delay = delay;
        this.permission = permission;
        this.messagesEn = messagesEn;
        this.messagesEs = messagesEs;
    }

    public void view(CommandSender viewer) {
        if (viewer instanceof Player p) {
            List<String> messages = p.getLocale().split("_")[0].equalsIgnoreCase("es") ? messagesEs : messagesEn;
            if (Main.getConfiguration().isPlaceholderApi())
                messages = PlaceholderAPI.setPlaceholders(p, messages);
            messages.forEach(m -> viewer.sendMessage(Utils.format(m)));
        } else {
            messagesEn.forEach(m -> viewer.sendMessage(Utils.format(m)));
        }
    }

    public void broadcast() {
        Bukkit.getOnlinePlayers().forEach(this::view);
        view(Bukkit.getConsoleSender());
    }

    public void broadcast(AnnouncerThread asyncThread) {
        try {
            if (!asyncThread.isRunning()) return;
            Thread.sleep((long) (delay * 1000));
            if (!asyncThread.isRunning()) return;
            broadcast();
        } catch (InterruptedException ex) {
            Logger.getLogger(AutoAnnouncement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

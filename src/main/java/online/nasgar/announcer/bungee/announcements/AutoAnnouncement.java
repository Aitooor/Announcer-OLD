package online.nasgar.announcer.bungee.announcements;

import de.exlll.configlib.annotation.ConfigurationElement;
import lombok.Getter;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import online.nasgar.announcer.bungee.Main;
import online.nasgar.announcer.common.utils.Utils;

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

    @SuppressWarnings("deprecation")
    public void view(CommandSender viewer) {
        if (viewer instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) viewer;
            List<String> messages = p.getLocale().getLanguage().equalsIgnoreCase("es") ? messagesEs : messagesEn;
            messages.forEach(m -> viewer.sendMessage(Utils.format(m)));
        } else {
            messagesEn.forEach(m -> viewer.sendMessage(Utils.format(m)));
        }
    }

    public void broadcast() {
        Main.getInstance().getProxy().getPlayers().forEach(this::view);
        messagesEn.forEach(m -> Main.getInstance().getLogger().info(Utils.format(m)));
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

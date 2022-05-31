package online.nasgar.announcer.bungee.announcements;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import online.nasgar.announcer.bungee.Main;
import online.nasgar.announcer.bungee.utils.BungeeUtils;
import online.nasgar.announcer.common.announcements.AbstractAnnouncement;
import online.nasgar.announcer.common.utils.Utils;

public class BungeeAnnouncement extends AbstractAnnouncement {
    public BungeeAnnouncement(String id) {
        super(id);
    }

    @Override
    public void execute() {
        for (ProxiedPlayer p : Main.getInstance().getProxy().getPlayers()) {
            if (!BungeeUtils.isInBlacklistedServers(p)) {
                String msg = p.getLocale().getLanguage().equalsIgnoreCase("es") ? this.getMsgEs() : this.getMsgEn();
                p.sendMessage(Utils.formatWithPrefix(msg, Main.getConfiguration().getPrefix()));
            }
        }
        Main.getInstance().getLogger().info(Utils.format(this.getMsgEn()));
    }
}

package online.nasgar.announcer.bungee.announcements;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import online.nasgar.announcer.bungee.Main;
import online.nasgar.announcer.common.announcements.AbstractAnnouncement;
import online.nasgar.announcer.common.utils.Utils;

public class BungeeAnnouncement extends AbstractAnnouncement {
    public BungeeAnnouncement(String id) {
        super(id);
    }

    @Override
    public void execute() {
        for (ProxiedPlayer p : Main.getInstance().getProxy().getPlayers()) {
            if (!isInBlacklistedServers(p)) {
                String msg = p.getLocale().getLanguage().equalsIgnoreCase("es") ? this.getMsgEs() : this.getMsgEn();
                p.sendMessage(Utils.formatWithPrefix(msg, Main.getMessageHandler().get(p, "prefix")));
            }
        }

        if (Main.getConfiguration().isConsoleBroadcast())
            Main.getInstance().getLogger().info(Utils.formatWithPrefix(this.getMsgEn(), Main.getMessageHandler().getMessage("prefix")));
    }

    private boolean isInBlacklistedServers(ProxiedPlayer p) {
        for (String server : Main.getConfiguration().getDeactivatedServers()) {
            if (p.getServer().getInfo().getName().equalsIgnoreCase(server))
                return true;
        }
        return false;
    }
}

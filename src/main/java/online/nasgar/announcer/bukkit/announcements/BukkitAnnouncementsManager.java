package online.nasgar.announcer.bukkit.announcements;

import online.nasgar.announcer.common.announcements.AbstractAnnouncement;
import online.nasgar.announcer.common.announcements.AbstractAnnouncementsManager;

public class BukkitAnnouncementsManager extends AbstractAnnouncementsManager {
    @Override
    public AbstractAnnouncement createAnnouncement(String id) {
        AbstractAnnouncement announcement = new BukkitAnnouncement(id);
        this.getAnnouncements().add(announcement);
        return announcement;
    }
}

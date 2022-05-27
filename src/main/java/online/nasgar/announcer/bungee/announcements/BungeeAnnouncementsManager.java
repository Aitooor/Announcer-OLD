package online.nasgar.announcer.bungee.announcements;

import online.nasgar.announcer.common.announcements.AbstractAnnouncement;
import online.nasgar.announcer.common.announcements.AbstractAnnouncementsManager;

public class BungeeAnnouncementsManager extends AbstractAnnouncementsManager {
    @Override
    public AbstractAnnouncement createAnnouncement(String id) {
        AbstractAnnouncement announcement = new BungeeAnnouncement(id);
        this.getAnnouncements().add(announcement);
        return announcement;
    }
}

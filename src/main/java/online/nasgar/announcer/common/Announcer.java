package online.nasgar.announcer.common;

import online.nasgar.announcer.common.announcements.AbstractAnnouncementsManager;

public class Announcer {
    AbstractAnnouncementsManager announcementsManager;

    public Announcer(AbstractAnnouncementsManager announcementsManager) {
        this.announcementsManager = announcementsManager;
    }
}

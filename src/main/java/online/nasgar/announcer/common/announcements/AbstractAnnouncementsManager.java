package online.nasgar.announcer.common.announcements;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAnnouncementsManager {
    @Getter
    private final List<AbstractAnnouncement> announcements;

    public AbstractAnnouncementsManager() {
        this.announcements = new ArrayList<>();
    }

    public abstract AbstractAnnouncement createAnnouncement(String id);

    public AbstractAnnouncement getAnnouncement(String id) {
        return this.getAnnouncements().stream().filter(a -> a.getId().equals(id)).findFirst().orElse(createAnnouncement(id));
    }
}

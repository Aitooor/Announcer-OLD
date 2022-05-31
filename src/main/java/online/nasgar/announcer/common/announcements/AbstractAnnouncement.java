package online.nasgar.announcer.common.announcements;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractAnnouncement {

    private final String id;
    String msgEn;
    String msgEs;

    public AbstractAnnouncement(String id) {
        this.id = id;
    }

    public abstract void execute();
}

package online.nasgar.announcer.common.announcements;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class AbstractAnnouncement {

    @Getter
    private final String id;
    @Getter @Setter
    private String msgEs;
    @Getter @Setter
    private String msgEn;

    public AbstractAnnouncement(String id) {
        this.id = id;
    }

    public abstract void execute();
}

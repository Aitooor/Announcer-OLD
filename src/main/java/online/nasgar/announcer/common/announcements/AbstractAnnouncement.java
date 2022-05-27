package online.nasgar.announcer.common.announcements;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractAnnouncement {

    @Getter(value = AccessLevel.PUBLIC)
    private final String id;
    @Getter(value = AccessLevel.PUBLIC) @Setter(value = AccessLevel.PUBLIC)
    private String msgEs;
    @Getter(value = AccessLevel.PUBLIC) @Setter(value = AccessLevel.PUBLIC)
    private String msgEn;

    public AbstractAnnouncement(String id) {
        this.id = id;
    }

    public abstract void execute();
}

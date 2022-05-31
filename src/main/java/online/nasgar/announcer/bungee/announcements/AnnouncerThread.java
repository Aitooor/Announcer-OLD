package online.nasgar.announcer.bungee.announcements;

import lombok.Getter;
import lombok.Setter;
import online.nasgar.announcer.bungee.Main;
import online.nasgar.announcer.bungee.config.Announcements;


@Getter
@Setter
public class AnnouncerThread extends Thread {
    private final Announcements config = Main.getAnnouncements();
    public boolean running = false;

    @Override
    public void run() {
        if (getConfig().getAnnouncements().isEmpty())
            return;

        while (isRunning()) {
            try {
                if (getConfig().isRandomBroadcast()) {
                    if (isRunning())
                        getConfig().getAnnouncementByRandom().broadcast(this);
                } else
                    getConfig().getAnnouncements().stream()
                            .filter(running -> isRunning()).forEach(a -> a.broadcast(this));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}

package online.nasgar.announcer.bukkit.announcements;

import lombok.Getter;
import lombok.Setter;
import online.nasgar.announcer.bukkit.Main;
import online.nasgar.announcer.bukkit.config.Announcements;


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
                    getConfig().getAnnouncementByRandom().broadcast(this);
                } else {
                    getConfig().getAnnouncements().stream()
                            .filter(running -> isRunning()).forEach(a -> a.broadcast(this));
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}

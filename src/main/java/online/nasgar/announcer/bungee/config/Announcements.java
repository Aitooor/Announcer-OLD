package online.nasgar.announcer.bungee.config;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.annotation.ElementType;
import de.exlll.configlib.configs.yaml.BukkitYamlConfiguration;
import de.exlll.configlib.format.FieldNameFormatters;
import lombok.Getter;
import online.nasgar.announcer.bungee.Main;
import online.nasgar.announcer.bungee.announcements.AutoAnnouncement;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Getter
public class Announcements extends BukkitYamlConfiguration {
    @Comment({"", "Whether to broadcast the announcement randomly",
            "When enabled, the announcements in the list will be broadcast randomly"})
    private boolean randomBroadcast = false;

    @Comment({"", "Announcements priority. (From top to bottom)"})
    @ElementType(AutoAnnouncement.class)
    private List<AutoAnnouncement> announcements = Collections.singletonList(
            new AutoAnnouncement(
                    "ExampleAnnouncement",
                    20,
                    "announcer.announcement.example",
                    List.of("&8========================================",
                            " &c# &aThis is an example of announcement",
                            "&8========================================"),
                    List.of("&8========================================",
                            " &c# &aEste es un ejemplo de un anuncio",
                            "&8========================================")));

    public Announcements() {
        super(
                new File(Main.getInstance().getDataFolder(), "announcements.yml").toPath(),
                BukkitYamlProperties.builder().setFormatter(FieldNameFormatters.LOWER_UNDERSCORE).build()
        );
    }

    public AutoAnnouncement getAnnouncementByRandom() {
        ;
        return getAnnouncements().get(new Random().nextInt(getAnnouncements().size()));
    }
}

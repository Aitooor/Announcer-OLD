package online.nasgar.announcer.bungee.config;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.configs.yaml.YamlConfiguration;
import de.exlll.configlib.format.FieldNameFormatters;
import lombok.Getter;
import online.nasgar.announcer.bungee.Main;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Getter
@Comment({"",
        "                       Plugin made by:",
        "                          Vicen621"})
public class Configuration extends YamlConfiguration {

    @Comment({"", "Prefix of the announcement"})
    private String prefix = "&8[&cAnnouncer&8] > &f";

    @Comment({"", "Whether to display announcements in the console"})
    private boolean consoleBroadcast = true;

    @Comment({"", "List of worlds where the sound will not be played"})
    private List<String> deactivatedServers = Collections.singletonList("test");

    public Configuration() {
        super(
                new File(Main.getInstance().getDataFolder(), "config.yml").toPath(),
                YamlProperties.builder().setFormatter(FieldNameFormatters.LOWER_UNDERSCORE).build()
        );
    }
}
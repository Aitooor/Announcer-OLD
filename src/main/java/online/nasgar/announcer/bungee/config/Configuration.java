package online.nasgar.announcer.bungee.config;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.configs.yaml.YamlConfiguration;
import de.exlll.configlib.format.FieldNameFormatters;
import lombok.Getter;
import online.nasgar.announcer.bungee.Main;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Getter
@Comment({"",
        "                       Plugin made by:",
        "                          Vicen621"})
public class Configuration extends YamlConfiguration {

    @Comment({"", "Whether to display announcements in the console"})
    private boolean consoleBroadcast = false;

    @Comment({"", "List of worlds where the sound will not be played"})
    private List<String> deactivatedServers = Arrays.asList("Auth-1", "Auth-2");

    public Configuration() {
        super(
                new File(Main.getInstance().getDataFolder(), "config.yml").toPath(),
                YamlProperties.builder().setFormatter(FieldNameFormatters.LOWER_UNDERSCORE).build()
        );
    }
}
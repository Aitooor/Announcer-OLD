package online.nasgar.announcer.bukkit.config;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.configs.yaml.BukkitYamlConfiguration;
import de.exlll.configlib.format.FieldNameFormatters;
import lombok.Getter;
import online.nasgar.announcer.bukkit.Main;

import java.io.File;

@Getter
@Comment({"",
        "                       Plugin made by:",
        "                          Vicen621"})
public class Configuration extends BukkitYamlConfiguration {

    @Comment({"", "Whether to display announcements in the console"})
    private boolean consoleBroadcast = false;

    @Comment({"", "Whether to use the PlaceholderAPI"})
    private boolean placeholderApi = true;

    public Configuration() {
        super(
                new File(Main.getInstance().getDataFolder(), "config.yml").toPath(),
                BukkitYamlProperties.builder().setFormatter(FieldNameFormatters.LOWER_UNDERSCORE).build()
        );
    }
}
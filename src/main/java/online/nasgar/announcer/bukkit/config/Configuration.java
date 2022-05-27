package online.nasgar.announcer.bukkit.config;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.configs.yaml.BukkitYamlConfiguration;
import de.exlll.configlib.format.FieldNameFormatters;
import lombok.Getter;
import online.nasgar.announcer.bukkit.Main;

import java.io.File;

@Comment({"",
        "                       Plugin made by:",
        "                          Vicen621"})
public class Configuration extends BukkitYamlConfiguration {

    @Getter
    @Comment({"", "Prefix of the announcement"})
    private String prefix = "&8[&cAnnouncer&8] » &f";

    public Configuration() {
        super(
                new File(Main.getInstance().getDataFolder(), "config.yml").toPath(),
                BukkitYamlProperties.builder().setFormatter(FieldNameFormatters.LOWER_UNDERSCORE).build()
        );
    }
}
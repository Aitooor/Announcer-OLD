package online.nasgar.announcer.bukkit;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.MessageType;
import lombok.Getter;
import me.yushust.message.MessageHandler;
import me.yushust.message.MessageProvider;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSourceDecorator;
import online.nasgar.announcer.bukkit.announcements.BukkitAnnouncementsManager;
import online.nasgar.announcer.bukkit.commands.BukkitAnnouncerCommand;
import online.nasgar.announcer.bukkit.config.Configuration;
import online.nasgar.announcer.common.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

//TODO: Hacer auto-announcements
public final class Main extends JavaPlugin {

    @Getter
    private static Main instance;
    @Getter
    private static Configuration configuration;
    @Getter
    private static BukkitCommandManager cmdManager;
    @Getter
    private static BukkitAnnouncementsManager announcementsManager;
    @Getter
    private static MessageHandler messageHandler;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        configuration = new Configuration();
        configuration.loadAndSave();

        announcementsManager = new BukkitAnnouncementsManager();

        MessageProvider messageProvider = MessageProvider
                .create(
                        MessageSourceDecorator
                                .decorate(BukkitMessageAdapt.newYamlSource(
                                        this, "lang_%lang%.yml"))
                                .addFallbackLanguage("en")
                                .addFallbackLanguage("es")
                                .get(),
                        config -> {
                            config.specify(Player.class)
                                    .setLinguist(player -> player.getLocale().split("_")[0])
                                    .setMessageSender((sender, mode, message) -> sender.sendMessage(message));
                            config.specify(CommandSender.class)
                                    .setLinguist(commandSender -> "en")
                                    .setMessageSender((sender, mode, message) -> sender.sendMessage(message));
                            config.addInterceptor(Utils::formatWithPrefix);
                        }
                );

        messageHandler = MessageHandler.of(messageProvider);

        commands();
    }

    private void commands() {
        cmdManager = new BukkitCommandManager(getInstance());
        cmdManager.enableUnstableAPI("help");

        cmdManager.setFormat(MessageType.HELP, ChatColor.DARK_AQUA, ChatColor.AQUA, ChatColor.GRAY, ChatColor.DARK_GRAY);
        cmdManager.registerCommand(new BukkitAnnouncerCommand());
    }
}

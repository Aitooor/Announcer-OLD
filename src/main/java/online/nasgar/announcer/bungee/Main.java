package online.nasgar.announcer.bungee;

import co.aikar.commands.BungeeCommandManager;
import co.aikar.commands.MessageType;
import lombok.Getter;
import me.yushust.message.MessageHandler;
import me.yushust.message.MessageProvider;
import me.yushust.message.bungee.BungeeMessageAdapt;
import me.yushust.message.source.MessageSourceDecorator;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import online.nasgar.announcer.bungee.announcements.BungeeAnnouncementsManager;
import online.nasgar.announcer.bungee.commands.BungeeAnnouncerCommand;
import online.nasgar.announcer.bungee.config.Configuration;
import online.nasgar.announcer.common.utils.Utils;

public final class Main extends Plugin {

    @Getter
    private static Main instance;
    @Getter
    private static Configuration configuration;
    @Getter
    private static BungeeCommandManager cmdManager;
    @Getter
    private static BungeeAnnouncementsManager announcementsManager;
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

        announcementsManager = new BungeeAnnouncementsManager();

        MessageProvider messageProvider = MessageProvider
                .create(
                        MessageSourceDecorator
                                .decorate(BungeeMessageAdapt.newYamlSource(
                                        this, "lang_%lang%.yml"))
                                .addFallbackLanguage("en")
                                .addFallbackLanguage("es")
                                .get(),
                        config -> {
                            config.specify(ProxiedPlayer.class)
                                    .setLinguist(player -> player.getLocale().getLanguage())
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
        cmdManager = new BungeeCommandManager(getInstance());
        cmdManager.enableUnstableAPI("help");

        cmdManager.setFormat(MessageType.HELP, ChatColor.DARK_AQUA, ChatColor.AQUA, ChatColor.GRAY, ChatColor.DARK_GRAY);
        cmdManager.registerCommand(new BungeeAnnouncerCommand());
    }
}

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
import online.nasgar.announcer.bungee.announcements.AnnouncerThread;
import online.nasgar.announcer.bungee.announcements.BungeeAnnouncementsManager;
import online.nasgar.announcer.bungee.commands.BungeeAnnouncerCommand;
import online.nasgar.announcer.bungee.config.Announcements;
import online.nasgar.announcer.bungee.config.Configuration;
import online.nasgar.announcer.common.utils.Utils;

public final class Main extends Plugin {

    @Getter
    private static Main instance;
    @Getter
    private static Configuration configuration;
    @Getter
    private static Announcements announcements;
    @Getter
    private static BungeeCommandManager cmdManager;
    @Getter
    private static BungeeAnnouncementsManager announcementsManager;
    @Getter
    private static MessageHandler messageHandler;
    private static AnnouncerThread thread = null;

    public static void restartAnnouncer() {
        Main.getInstance().getProxy().getScheduler().runAsync(Main.getInstance(), () -> {
            if (thread != null && thread.isAlive()) {
                thread.setRunning(false);
                thread = new AnnouncerThread();
                thread.start();
                thread.setRunning(true);
            } else {
                thread = new AnnouncerThread();
                thread.setRunning(true);
                thread.start();
            }
        });
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        configuration = new Configuration();
        announcements = new Announcements();
        configuration.loadAndSave();
        announcements.loadAndSave();

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
                            config.addInterceptor(m -> Utils.formatWithPrefix(m, configuration.getPrefix()));
                        }
                );

        messageHandler = MessageHandler.of(messageProvider);

        commands();
        restartAnnouncer();
    }

    @Override
    public void onDisable() {
        if (thread != null && thread.isAlive())
            thread.setRunning(false);
    }

    private void commands() {
        cmdManager = new BungeeCommandManager(getInstance());
        cmdManager.enableUnstableAPI("help");

        cmdManager.setFormat(MessageType.HELP, ChatColor.DARK_AQUA, ChatColor.AQUA, ChatColor.GRAY, ChatColor.DARK_GRAY);
        cmdManager.registerCommand(new BungeeAnnouncerCommand());
    }
}

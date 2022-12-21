package online.nasgar.announcer.bukkit.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import online.nasgar.announcer.bukkit.Main;
import online.nasgar.announcer.common.announcements.AbstractAnnouncement;
import online.nasgar.announcer.common.utils.Utils;
import org.bukkit.command.CommandSender;

@CommandAlias("announcer|announce")
@CommandPermission("announcer.admin")
public class BukkitAnnouncerCommand extends BaseCommand {

    @Default
    @HelpCommand
    @CatchUnknown
    public void onHelp(CommandSender sender, @Name("Command Help") CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("es|español|spanish")
    @Description("Announces the message in Spanish")
    public void onSpanish(CommandSender sender, String message) {
        AbstractAnnouncement announcement = Main.getAnnouncementsManager().getAnnouncement(sender.getName());
        announcement.setMsgEs(message);
        sender.sendMessage(Utils.formatWithPrefix(Main.getMessageHandler().replacing(sender, "added.es", "{msg}", message),
                Main.getMessageHandler().get(sender, "prefix")));
    }

    @Subcommand("en|ingles|english")
    @Description("Announces the message in English")
    public void onEnglish(CommandSender sender, String message) {
        AbstractAnnouncement announcement = Main.getAnnouncementsManager().getAnnouncement(sender.getName());
        announcement.setMsgEn(message);

        sender.sendMessage(Utils.formatWithPrefix(Main.getMessageHandler().replacing(sender, "added.en", "{msg}", message),
                Main.getMessageHandler().get(sender, "prefix")));
    }

    @Subcommand("execute")
    @Description("Executes the announcement")
    public void onExecute(CommandSender sender) {
        AbstractAnnouncement announcement = Main.getAnnouncementsManager().getAnnouncement(sender.getName());
        announcement.execute();
    }

    @Subcommand("reload|rl")
    @Description("Reloads the Announcer plugin config")
    public void onReload(CommandSender sender) {
        Main.getConfiguration().load();
        Main.getAnnouncements().load();
        Main.restartAnnouncer();

        sender.sendMessage(Utils.formatWithPrefix(Main.getMessageHandler().get(sender, "config-reloaded"),
                Main.getMessageHandler().get(sender, "prefix")));
    }
}
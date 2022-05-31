package online.nasgar.announcer.bungee.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.md_5.bungee.api.CommandSender;
import online.nasgar.announcer.bungee.Main;
import online.nasgar.announcer.common.announcements.AbstractAnnouncement;

@CommandAlias("announcer|announce")
@CommandPermission("announcer.admin")
public class BungeeAnnouncerCommand extends BaseCommand {

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
        Main.getMessageHandler().sendReplacing(sender, "added.es", "{msg}", message);
    }

    @Subcommand("en|ingles|english")
    @Description("Announces the message in English")
    public void onEnglish(CommandSender sender, String message) {
        AbstractAnnouncement announcement = Main.getAnnouncementsManager().getAnnouncement(sender.getName());
        announcement.setMsgEn(message);

        Main.getMessageHandler().sendReplacing(sender, "added.en", "{msg}", message);
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

        Main.getMessageHandler().send(sender, "config-reloaded");
    }
}

package com.supermistmc.antibow.commands.anitbow.subcommands;

import com.supermistmc.antibow.AntiBow;
import com.supermistmc.antibow.Locale;
import com.supermistmc.antibow.Permissions;
import com.supermistmc.antibow.commands.AbstractSubCommand;
import com.supermistmc.antibow.services.locale.ILocaleService;
import com.supermistmc.antibow.services.locale.LocaleService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class AntiBowSubReloadPlugin extends AbstractSubCommand {

    public boolean canExecute(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 && args[0].equalsIgnoreCase("reload");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String label, String[] args) {
        AntiBow antiBowPlugin = AntiBow.getInstance();
        ILocaleService localeService = LocaleService.getILocaleService();
        if (sender.isOp() || sender.hasPermission(Permissions.RELOAD_ANTIBOW)) {
            antiBowPlugin.reload();
            sender.sendMessage(localeService.getLocale(Locale.RELOAD_MESSAGE));
        } else {
            sender.sendMessage(localeService.getLocale(Locale.NO_PERMISSION));
        }
        return true;
    }

}

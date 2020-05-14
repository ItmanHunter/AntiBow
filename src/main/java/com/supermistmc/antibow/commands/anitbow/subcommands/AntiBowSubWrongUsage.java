package com.supermistmc.antibow.commands.anitbow.subcommands;

import com.supermistmc.antibow.Locale;
import com.supermistmc.antibow.Permissions;
import com.supermistmc.antibow.commands.AbstractSubCommand;
import com.supermistmc.antibow.services.locale.ILocaleService;
import com.supermistmc.antibow.services.locale.LocaleService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class AntiBowSubWrongUsage extends AbstractSubCommand {

    @Override
    public boolean canExecute(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String label, String[] args) {
        ILocaleService localeService = LocaleService.getILocaleService();
        if (sender.isOp() || sender.hasPermission(Permissions.USAGE)) {
            sender.sendMessage(localeService.getLocale(Locale.WRONG_USAGE));
        } else {
            sender.sendMessage(localeService.getLocale(Locale.NO_PERMISSION));
        }
        return true;
    }
}

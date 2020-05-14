package com.supermistmc.antibow.commands.anitbow.subcommands;

import com.supermistmc.antibow.AntiBow;
import com.supermistmc.antibow.Locale;
import com.supermistmc.antibow.Permissions;
import com.supermistmc.antibow.commands.AbstractSubCommand;
import com.supermistmc.antibow.services.locale.ILocaleService;
import com.supermistmc.antibow.services.locale.LocaleService;
import com.supermistmc.antibow.services.region.RegionService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class AntiBowSubHelp extends AbstractSubCommand {

    @Override
    public boolean canExecute(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 && args[0].equalsIgnoreCase("help");
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String label, String[] args) {
        ILocaleService localeService = LocaleService.getILocaleService();
        if (sender.isOp() || sender.hasPermission(Permissions.HELP)) {
            for(String message:localeService.getHelpString()) {
                sender.sendMessage(message);
            }
        } else {
            sender.sendMessage(localeService.getLocale(Locale.NO_PERMISSION));
        }
        return true;
    }
}

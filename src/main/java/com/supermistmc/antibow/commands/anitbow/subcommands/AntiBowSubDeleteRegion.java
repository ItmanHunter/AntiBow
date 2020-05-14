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

public class AntiBowSubDeleteRegion extends AbstractSubCommand {

    public static final String[] SUB_COMMAND_ALIAS = new String[]{
            "delete",
            "remove",
    };

    @Override
    public boolean canExecute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            for (String commandAlias:SUB_COMMAND_ALIAS) {
                if(args[0].equalsIgnoreCase(commandAlias)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean executeCommand(CommandSender sender, Command command, String label, String[] args) {
        ILocaleService localeService = LocaleService.getILocaleService();
        if (sender.isOp() || sender.hasPermission(Permissions.REMOVE_REGION)) {
            RegionService.getRegionService().removeRegion(args[1]);
            sender.sendMessage(localeService.getLocale(Locale.REGION_REMOVED));
        } else {
            sender.sendMessage(localeService.getLocale(Locale.NO_PERMISSION));
        }
        return true;
    }
}

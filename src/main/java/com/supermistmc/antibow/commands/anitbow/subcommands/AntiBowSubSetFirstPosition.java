package com.supermistmc.antibow.commands.anitbow.subcommands;

import com.supermistmc.antibow.AntiBow;
import com.supermistmc.antibow.Locale;
import com.supermistmc.antibow.Permissions;
import com.supermistmc.antibow.Point;
import com.supermistmc.antibow.commands.AbstractSubCommand;
import com.supermistmc.antibow.services.locale.ILocaleService;
import com.supermistmc.antibow.services.locale.LocaleService;
import com.supermistmc.antibow.services.region.RegionService;
import com.supermistmc.antibow.utils.RegionUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AntiBowSubSetFirstPosition extends AbstractSubCommand {

    public static final String[] SUB_COMMAND_ALIAS = new String[]{
            "first",
            "pos1",
            "position1",
            "firstposition",
            "start"
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
        if (sender.isOp() || sender.hasPermission(Permissions.CREATE_REGION)) {
            if(RegionUtils.playerCheck(sender)) {
                return true;
            }
            Point firstPoint = RegionUtils.fromPlayer((Player)sender);
            RegionService.getRegionService().setFirstPoint(firstPoint);
            sender.sendMessage(localeService.getLocale(Locale.POSITION1_SET,firstPoint));
        } else {
            sender.sendMessage(localeService.getLocale(Locale.NO_PERMISSION));
        }
        return true;
    }

}

package com.supermistmc.antibow.commands;


import com.supermistmc.antibow.AntiBow;
import com.supermistmc.antibow.Locale;
import com.supermistmc.antibow.Permissions;
import com.supermistmc.antibow.Point;
import com.supermistmc.antibow.services.RegionServiceFactory;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AntiBowCommand extends AbstractCommand {

    public final static String COMMAND = "AnitBow";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        AntiBow antiBowPlugin = AntiBow.getInstance();
        if (args.length == 0) {
            showPluginInfo(sender);
            return false;
        }
        if (args.length == 1) {
            if(args[0].equalsIgnoreCase("help")) {
                showPluginInfo(sender);
                return true;
            }
            if(args[0].equalsIgnoreCase("reload")) {
                if (sender.isOp() || sender.hasPermission(Permissions.RELOAD_ANTIBOW)) {
                    antiBowPlugin.reload();
                    sender.sendMessage(antiBowPlugin.getLocale(Locale.RELOAD_MESSAGE));
                    return true;
                } else {
                    sender.sendMessage(antiBowPlugin.getLocale(Locale.NO_PERMISSION));
                    return true;
                }
            }
            if(args[0].equalsIgnoreCase("pos1")) {
                if (sender.isOp() || sender.hasPermission(Permissions.CREATE_REGION)) {
                    if(playerCheck(sender)) {
                        return true;
                    }
                    RegionServiceFactory.getRegionService().setFirstPoint(fromPlayer((Player)sender));
                    sender.sendMessage(antiBowPlugin.getLocale(Locale.POSITION1_SET));
                    return true;
                } else {
                    sender.sendMessage(antiBowPlugin.getLocale(Locale.NO_PERMISSION));
                    return true;
                }
            }
            if(args[0].equalsIgnoreCase("pos2")) {
                if (sender.isOp() || sender.hasPermission(Permissions.CREATE_REGION)) {
                    if(playerCheck(sender)) {
                        return true;
                    }
                    RegionServiceFactory.getRegionService().setSecondPoint(fromPlayer((Player)sender));
                    sender.sendMessage(antiBowPlugin.getLocale(Locale.POSITION2_SET));
                } else {
                    sender.sendMessage(antiBowPlugin.getLocale(Locale.NO_PERMISSION));
                }
                return true;
            }
            showPluginInfo(sender);
            return false;
        }
        if (args.length == 2) {
            if(args[0].equalsIgnoreCase("create")
                    || args[0].equalsIgnoreCase("new")
                    || args[0].equalsIgnoreCase("add")) {
                if (sender.isOp() || sender.hasPermission(Permissions.CREATE_REGION)) {
                    RegionServiceFactory.getRegionService().addRegion(args[1]);
                    sender.sendMessage(antiBowPlugin.getLocale(Locale.REGION_ADDED));
                } else {
                    sender.sendMessage(antiBowPlugin.getLocale(Locale.NO_PERMISSION));
                }
                return true;
            }
            if(args[0].equalsIgnoreCase("remove")
                    || args[0].equalsIgnoreCase("delete") ) {
                if (sender.isOp() || sender.hasPermission(Permissions.REMOVE_REGION)) {
                    RegionServiceFactory.getRegionService().removeRegion(args[1]);
                    sender.sendMessage(antiBowPlugin.getLocale(Locale.REGION_REMOVED));
                } else {
                    sender.sendMessage(antiBowPlugin.getLocale(Locale.NO_PERMISSION));
                }
                return true;
            }
            showPluginInfo(sender);
            return false;
        }
        showPluginInfo(sender);
        return false;
    }

    private boolean playerCheck(CommandSender sender) {
        if (sender instanceof Player) {
            return false;
        }
        AntiBow antiBowPlugin = AntiBow.getInstance();
        sender.sendMessage(antiBowPlugin.getLocale(Locale.PLAYER_ONLY));
        return true;
    }

    private void showPluginInfo(CommandSender sender) {
        AntiBow AnitBowPlugin = AntiBow.getInstance();
        for(String message:AnitBowPlugin.getHelpString()) {
            sender.sendMessage(message);
        }
    }

    private Point fromPlayer(Player player) {
        Location location = player.getLocation();
        Point point = Point.builder()
                .world(location.getWorld().getName())
                .x(location.getX())
                .y(location.getY())
                .z(location.getZ()).build();
        return point;
    }
}

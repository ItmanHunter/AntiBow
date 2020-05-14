package com.supermistmc.antibow.utils;

import com.supermistmc.antibow.AntiBow;
import com.supermistmc.antibow.Locale;
import com.supermistmc.antibow.Point;
import com.supermistmc.antibow.Region;
import com.supermistmc.antibow.services.locale.LocaleService;
import com.supermistmc.antibow.services.region.RegionService;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegionUtils {

    public static Point fromPlayer(Player player) {
        Location location = player.getLocation();
        Point point = Point.builder()
                .world(location.getWorld().getName())
                .x(location.getX())
                .y(location.getY())
                .z(location.getZ()).build();
        return point;
    }

    public static boolean playerCheck(CommandSender sender) {
        if (sender instanceof Player) {
            return false;
        }
        sender.sendMessage(LocaleService.getILocaleService().getLocale(Locale.PLAYER_ONLY));
        return true;
    }

    public static boolean checkPlayerInRegion(Player player) {
        Point point = fromPlayer(player);
        for(Region region: RegionService.getRegionService().getRegions()) {
            if (region.insideRegion(point)) {
                return true;
            }
        }
        return false;
    }
}

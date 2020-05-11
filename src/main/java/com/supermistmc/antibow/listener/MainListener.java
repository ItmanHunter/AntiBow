package com.supermistmc.antibow.listener;

import com.supermistmc.antibow.Point;
import com.supermistmc.antibow.Region;
import com.supermistmc.antibow.services.RegionServiceFactory;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class MainListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleBow(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (checkPlayerInRegion(player)) {
                event.setCancelled(true);
                return;
            }
        }
    }

    private boolean checkPlayerInRegion(Player player) {
        Point point = fromPlayer(player);
        for(Region region: RegionServiceFactory.getRegionService().getRegions()) {
            if (region.insideRegion(point)) {
                return true;
            }
        }
        return false;
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

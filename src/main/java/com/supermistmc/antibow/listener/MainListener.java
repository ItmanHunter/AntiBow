package com.supermistmc.antibow.listener;

import com.supermistmc.antibow.Locale;
import com.supermistmc.antibow.services.locale.LocaleService;
import com.supermistmc.antibow.utils.RegionUtils;
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
            if (RegionUtils.checkPlayerInRegion(player)) {
                event.setCancelled(true);
                player.sendMessage(
                        LocaleService.getILocaleService().getLocale(Locale.NO_BOWS)
                );
                return;
            }
        }
    }

}

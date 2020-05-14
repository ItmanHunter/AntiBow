package com.supermistmc.antibow;

import com.supermistmc.antibow.commands.Command;
import com.supermistmc.antibow.listener.MainListener;
import com.supermistmc.antibow.services.locale.FileLocaleService;
import com.supermistmc.antibow.services.locale.LocaleService;
import com.supermistmc.antibow.services.region.FileRegionService;
import com.supermistmc.antibow.services.region.RegionService;
import com.supermistmc.antibow.utils.FileManager;
import com.supermistmc.antibow.utils.MessageUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class AntiBow extends JavaPlugin {

    private static AntiBow antiBow;

    private FileManager locale, mainConfig;

    public static AntiBow getInstance() {
        return antiBow;
    }

    private void prepareRegionService() {
        FileRegionService fileRegionService = new FileRegionService(this);
        RegionService.setRegionService(fileRegionService);
    }

    private void prepareLocaleService() {
        FileLocaleService fileLocaleService = new FileLocaleService(this);
        LocaleService.setILocaleService(fileLocaleService);
    }

    public void onEnable() {
        antiBow = this;
        saveDefaultConfig();
        prepareRegionService();
        prepareLocaleService();
        mainConfig = new FileManager("config",this.getDataFolder().getAbsolutePath());
        Command.registerCommands(this);
        getServer().getPluginManager().registerEvents(new MainListener(), this);
    }

    public void onDisable() {
        RegionService.getRegionService().save();
        LocaleService.getILocaleService().save();
        mainConfig.save();
    }

    public void reload() {
        RegionService.getRegionService().reload();
        LocaleService.getILocaleService().reload();
        mainConfig.reload();
    }
}

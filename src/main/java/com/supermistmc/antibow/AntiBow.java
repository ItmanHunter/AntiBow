package com.supermistmc.antibow;

import com.supermistmc.antibow.commands.Command;
import com.supermistmc.antibow.listener.MainListener;
import com.supermistmc.antibow.services.FileRegionService;
import com.supermistmc.antibow.services.RegionServiceFactory;
import com.supermistmc.antibow.utils.FileManager;
import com.supermistmc.antibow.utils.MessageUtil;
import org.bukkit.Bukkit;
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
        RegionServiceFactory.setRegionService(fileRegionService);
    }

    public void onEnable() {
        antiBow = this;
        saveDefaultConfig();
        prepareRegionService();
        locale = new FileManager("locale",this.getDataFolder().getAbsolutePath());
        mainConfig = new FileManager("config",this.getDataFolder().getAbsolutePath());
        Command.registerCommands(this);
        getServer().getPluginManager().registerEvents(new MainListener(), this);
    }

    public void onDisable() {
        locale.save();
        mainConfig.save();
    }

    public void reload() {
        locale.reload();
        mainConfig.reload();
    }

    public String getLocale(String name) {
        return MessageUtil.replaceColors(locale.getConfig().getString(name,""));
    }

    public List<String> getHelpString() {
        List<String> list = new ArrayList<>();
        ConfigurationSection usageHelp = locale.getConfig().getConfigurationSection(Locale.USAGE_HELP);
        if (usageHelp == null) {
            return list;
        }
        for (String key: usageHelp.getKeys(false) ) {
            list.add(MessageUtil.replaceColors(
                    usageHelp.getString(key)
            ));
        }
        return list;
    }

    public String getLocale(String name, Point point) {
        String message = MessageUtil.replaceColors(locale.getConfig().getString(name,""));
        message = message.replace("%x%",String.valueOf(point.getX()));
        message = message.replace("%y%",String.valueOf(point.getY()));
        message = message.replace("%z%",String.valueOf(point.getZ()));
        return message;
    }
}

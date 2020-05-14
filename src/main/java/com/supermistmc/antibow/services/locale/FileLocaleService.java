package com.supermistmc.antibow.services.locale;

import com.supermistmc.antibow.Locale;
import com.supermistmc.antibow.Point;
import com.supermistmc.antibow.Region;
import com.supermistmc.antibow.utils.FileManager;
import com.supermistmc.antibow.utils.MessageUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class FileLocaleService implements ILocaleService {

    FileManager locale;

    JavaPlugin plugin;

    public FileLocaleService(JavaPlugin plugin) {
        this.plugin = plugin;
        reload();
    }

    @Override
    public void reload() {
        locale = new FileManager("locale",
                plugin.getDataFolder().getAbsolutePath());
    }

    @Override
    public void save() {
        locale.save();
    }

    @Override
    public String getLocale(String name) {
        return MessageUtil.replaceColors(locale.getConfig().getString(name,""));
    }

    @Override
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

    @Override
    public String getLocale(String name, Point point) {
        String message = MessageUtil.replaceColors(locale.getConfig().getString(name,""));
        message = message.replace("%x%",String.valueOf(point.getX()));
        message = message.replace("%y%",String.valueOf(point.getY()));
        message = message.replace("%z%",String.valueOf(point.getZ()));
        return message;
    }

    @Override
    public String getLocale(String name,Region region) {
        String message = MessageUtil.replaceColors(locale.getConfig().getString(name,""));
        message = message.replace("%region_name%", region.getName());
        message = message.replace("%region_world%", region.getWorld());
        message = message.replace("%x1%",String.valueOf(region.getMinX()));
        message = message.replace("%y1%",String.valueOf(region.getMinY()));
        message = message.replace("%z1%",String.valueOf(region.getMinZ()));
        message = message.replace("%x2%",String.valueOf(region.getMaxX()));
        message = message.replace("%y2%",String.valueOf(region.getMaxY()));
        message = message.replace("%z2%",String.valueOf(region.getMaxZ()));
        return message;
    }
}

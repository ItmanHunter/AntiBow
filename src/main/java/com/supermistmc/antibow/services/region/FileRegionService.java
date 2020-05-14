package com.supermistmc.antibow.services.region;

import com.supermistmc.antibow.Point;
import com.supermistmc.antibow.Region;
import com.supermistmc.antibow.utils.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class FileRegionService implements IRegionService {

    private static String firstPoint = "firstPoint";
    private static String secondPoint = "secondPoint";

    FileManager dataConfig;
    JavaPlugin plugin;

    public FileRegionService(JavaPlugin plugin) {
        this.plugin = plugin;
        reload();
    }

    @Override
    public void save() {
        dataConfig.save();
    }

    @Override
    public boolean isFirstPointSet() {
        return dataConfig.getConfig().isSet(firstPoint);
    }

    @Override
    public boolean isSecondPointSet() {
        return dataConfig.getConfig().isSet(secondPoint);
    }

    @Override
    public void reload() {
        dataConfig = new FileManager("data",
                plugin.getDataFolder().getAbsolutePath());
    }

    @Override
    public void setFirstPoint(Point point) {
        dataConfig.getConfig().set(firstPoint,savePoint(point));
        dataConfig.save();
    }

    private String savePoint(Point point) {
        return point.getWorld() + ":" + point.getX() + ":" + point.getY() + ":" + point.getZ();
    }

    @Override
    public void setSecondPoint(Point point) {
        dataConfig.getConfig().set(secondPoint,savePoint(point));
        dataConfig.save();
    }

    @Override
    public void addRegion(String name) {
        if (dataConfig.getConfig().getConfigurationSection("regions") == null) {
            dataConfig.getConfig().createSection("regions");
            dataConfig.save();
        }
        saveRegion(name);
    }

    private void saveRegion(String name) {
        dataConfig.getConfig().set("regions."+name+".name", name);
        dataConfig.getConfig().set("regions."+name+".point1", dataConfig.getConfig().get(firstPoint));
        dataConfig.getConfig().set("regions."+name+".point2", dataConfig.getConfig().get(secondPoint));
        dataConfig.save();
    }

    @Override
    public void removeRegion(String name) {
        if (dataConfig.getConfig().getConfigurationSection("regions") == null) {
            dataConfig.getConfig().createSection("regions");
            dataConfig.save();
        }
        dataConfig.getConfig().set("regions."+name,null);
        dataConfig.save();
    }

    @Override
    public Region getRegion(String name) {
        Point first = Point.fromString(dataConfig.getConfig().getString("regions."+name+".point1"));
        Point second = Point.fromString(dataConfig.getConfig().getString("regions."+name+".point2"));;
        Region region = new Region(first,second);
        region.setName(dataConfig.getConfig().getString("regions."+name+".name"));
        return region;
    }

    @Override
    public List<Region> getRegions() {
        if (dataConfig.getConfig().getConfigurationSection("regions") == null) {
            dataConfig.getConfig().createSection("regions");
            dataConfig.save();
        }
        List<Region> regions = new ArrayList<>();
        for(String key:dataConfig.getConfig().getConfigurationSection("regions").getKeys(false)) {
            regions.add(getRegion(key));
        }
        return regions;
    }
}

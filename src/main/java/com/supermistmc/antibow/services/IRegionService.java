package com.supermistmc.antibow.services;

import com.supermistmc.antibow.Point;
import com.supermistmc.antibow.Region;

import java.util.List;

public interface IRegionService {
    void setFirstPoint(Point point);
    void setSecondPoint(Point point);
    void addRegion(String name);
    void removeRegion(String name);
    Region getRegion(String name);
    List<Region> getRegions();
}

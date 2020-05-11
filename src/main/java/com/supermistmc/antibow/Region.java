package com.supermistmc.antibow;

import lombok.Data;

@Data
public class Region {

    String world;

    double maxX, minX, maxY, minY, maxZ, minZ;

    public Region(Point first,Point second) {
        world = first.getWorld();
        maxX = Math.max(first.getX(),second.getX());
        minX = Math.min(first.getX(),second.getX());
        maxY = Math.max(first.getY(),second.getY());
        minY = Math.min(first.getY(),second.getY());
        maxZ = Math.max(first.getZ(),second.getZ());
        minZ = Math.min(first.getZ(),second.getZ());
    }

    public boolean insideRegion(Point point) {
        return world.equalsIgnoreCase(point.getWorld()) &&
                (minX <= point.getX()) && (point.getX() <= maxX)
                && (minY <= point.getY()) && (point.getY() <= maxY)
                && (minZ <= point.getZ()) && (point.getZ() <= maxZ);
    }

}

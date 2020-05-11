package com.supermistmc.antibow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Point {
    String world;
    double x, y, z;

    public static Point fromString(String string) {
        String[] parts = string.split(":");
        if (parts.length != 4) {
            return new Point("world",0,0,0);
        }
        return new Point(
                parts[0],
                Double.valueOf(parts[1]),
                Double.valueOf(parts[2]),
                Double.valueOf(parts[3])
                );
    }
}

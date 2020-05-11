package com.supermistmc.antibow.utils;

import org.bukkit.ChatColor;

import java.util.Map;

public class MessageUtil {

    public static String replaceColors(String command) {
        return ChatColor.translateAlternateColorCodes('&',command);
    }

}

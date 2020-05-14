package com.supermistmc.antibow.services.locale;

import com.supermistmc.antibow.Point;
import com.supermistmc.antibow.Region;

import java.util.List;

public interface ILocaleService {
    String getLocale(String name);
    List<String> getHelpString();
    String getLocale(String name,Point point);
    String getLocale(String name,Region region);
    void reload();
    void save();
}

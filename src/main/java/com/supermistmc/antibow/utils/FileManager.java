package com.supermistmc.antibow.utils;

import com.google.common.base.Charsets;
import com.supermistmc.antibow.AntiBow;
import lombok.Data;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Data
public class FileManager {

    /* File */
    private File file;
    /* Strings */
    private String name, directory;
    /* Configuration */
    private YamlConfiguration config;

    /**
     * Bukkit Configuration Class
     *
     * @param name      - Is the identifier for the configuration file and object.
     * @param directory - Directory.
     */
    public FileManager(String name, String directory) {
        this.name = name;
        this.directory = directory;
        file = new File(directory, name + ".yml");
        if (!file.exists()) {
            AntiBow.getInstance().saveResource(name + ".yml", false);
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Saves the configuration file from memory to storage
     */
    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(this.getFile());
        final InputStream defConfigStream = AntiBow.getInstance().getResource(name + ".yml");
        if (defConfigStream == null) {
            return;
        }
        config.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
    }
}

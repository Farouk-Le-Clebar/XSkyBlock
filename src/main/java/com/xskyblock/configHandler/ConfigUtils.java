package com.xskyblock.configHandler;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtils {
    private File configFile = new File("plugins/XSkyBlock", "config.yml");
    private FileConfiguration config;

    public ConfigUtils() {
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void removePlayerIsland(String playerIsland, String playerName) {
        try {
            config.set("islands." + playerIsland + "." + playerName, null);
            config.save(configFile);
            reloadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNewPlayerIsland(String playerIsland, String playerName, String permission) {
        try {
            config.set("islands." + playerIsland + "." + playerName, permission);
            config.save(configFile);
            reloadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}

package com.xskyblock.config;

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
                configFile.getParentFile().mkdirs();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNewPlayerIsland(String playerIsland, String playerName, String permission) {
        try {
            config.set("islands." + playerIsland + "." + playerName, permission);
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public boolean playerHavingIsland(String playerName) {
        for (String island : config.getConfigurationSection("islands.").getKeys(false)) {
            if (config.getConfigurationSection("islands." + island).getKeys(false).contains(playerName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPlayerExists(String playerName) {
        for (String alreadyConnected : config.getConfigurationSection("alreadyConnected.").getKeys(false)) {
            if (alreadyConnected.equals(playerName)) {
                return true;
            }
        }
        return false;
    }

    public void setPlayerAlreadyConnected(String playerName) {
        try {
            config.set("alreadyConnected." + playerName, true);
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPlayerIsland(String playerName) {
        for (String island : config.getConfigurationSection("islands.").getKeys(false)) {
            if (config.getConfigurationSection("islands." + island).getKeys(false).contains(playerName)) {
                return island;
            }
        }
        return null;
    }

    public String getPlayerIslandPermission(String playerName) {
        for (String island : config.getConfigurationSection("islands.").getKeys(false)) {
            if (config.getConfigurationSection("islands." + island).getKeys(false).contains(playerName)) {
                return config.getString("islands." + island + "." + playerName);
            }
        }
        return null;
    }

    public void removeIsland(String playerName) {
        String island = getPlayerIsland(playerName);
        String permission = getPlayerIslandPermission(playerName);

        if (permission.equals("owner")) {
            try {
                config.set("islands." + island, null);
                config.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

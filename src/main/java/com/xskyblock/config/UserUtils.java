package com.xskyblock.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class UserUtils {
    private File configFile;
    private FileConfiguration config;

    public UserUtils(JavaPlugin plugin) {
        configFile = new File(plugin.getDataFolder(), "user.yml");

        if (!configFile.exists()) {
            plugin.saveResource("user.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void addMoney(String playerName, int amount) {
        reloadConfig();
        int currentMoney = config.getInt("user." + playerName + ".money", 0);
        int newMoney = currentMoney + amount;
        config.set("user." + playerName + ".money", newMoney);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerMoney(String playerName, int amount) {
        reloadConfig();
        config.set("user." + playerName + ".money", amount);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMoney(String playerName) {
        reloadConfig();
        return config.getInt("user." + playerName + ".money", -1);
    }

    public String getRank(String playerName) {
        reloadConfig();
        return config.getString("user." + playerName + ".rank", "ERROR");
    }

    public void setPlayerRank(String playerName, String rank) {
        reloadConfig();
        config.set("user." + playerName + ".rank", rank);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initPlayer(String playerName) {
        if (config.get("user." + playerName) == null) {
            config.set("user." + playerName + ".money", 0);
            config.set("user." + playerName + ".rank", "Joueur");
            try {
                config.save(configFile);
                reloadConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isPlayerExists(String playerName) {
        reloadConfig();
        if (config.get("user." + playerName) == null)
            return false;
        else
            return true;
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}

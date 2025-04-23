package com.xskyblock.moneyHandler;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MoneyUtils {
    private File configFile;
    private FileConfiguration config;

    public MoneyUtils() {
        configFile = new File("plugins/XSkyBlock", "config.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void addMoney(String playerName, int amount) {
        reloadConfig();
        int currentMoney = config.getInt("money." + playerName, 0);
        int newMoney = currentMoney + amount;
        config.set("money." + playerName, newMoney);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerMoney(String playerName, int amount) {
        reloadConfig();
        config.set("money." + playerName, amount);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMoney(String playerName) {
        reloadConfig();
        return config.getInt("money." + playerName, -1);
    }

    public void initPlayerMoney(String playerName) {
        if (config.get("money." + playerName) == null) {
            config.set("money." + playerName, 0);
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
        if (config.get("money." + playerName) == null)
            return false;
        else
            return true;
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}

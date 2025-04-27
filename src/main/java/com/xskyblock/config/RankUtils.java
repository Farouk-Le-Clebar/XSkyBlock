package com.xskyblock.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class RankUtils {
    private File configFile;
    private FileConfiguration config;

    public RankUtils(JavaPlugin plugin) {
        configFile = new File(plugin.getDataFolder(), "rank.yml");

        if (!configFile.exists()) {
            plugin.saveResource("rank.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void setRank(String playerName, String rank) {
        reloadConfig();
        config.set("rank." + playerName, rank);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRank(String playerName) {
        return config.getString("rank." + playerName, "Joueur");
    }

    public String getRankPrefix(String rank) {
        String prefix = config.getString("ranks." + rank + ".prefix");
        if (prefix == null) {
            return ("ranks." + rank + ".prefix");
        }
        return prefix;
    }

    public void initPlayerRank(String playerName) {
        if (config.get("rank." + playerName) == null) {
            config.set("rank." + playerName, "Joueur");
            try {
                config.save(configFile);
                reloadConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRankExists(String rank) {
        return config.getConfigurationSection("ranks").getKeys(false).contains(rank);
    }

    public boolean hasPermission(String rank, String permission) {
        return config.getStringList("ranks." + rank + ".permissions").contains(permission);
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}

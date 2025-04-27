package com.xskyblock.island;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.xskyblock.config.ConfigUtils;
import com.xskyblock.config.RankUtils;
import com.xskyblock.config.UserUtils;

import java.util.HashMap;
import java.util.Map;

public class IslandLevel {
    private ConfigUtils configUtils;
    private UserUtils userUtils;
    private RankUtils rankUtils;
    private Map<Material, Integer> blockValues;

    public IslandLevel(ConfigUtils configUtils, UserUtils userUtils, RankUtils rankUtils) {
        this.configUtils = configUtils;
        this.userUtils = userUtils;
        this.rankUtils = rankUtils;
        this.blockValues = loadBlockValues();
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return;
        }

        String playerRank = userUtils.getRank(player.getName());
        if (!rankUtils.hasPermission(playerRank, "XSkyBlock.island.level")) {
            sender.sendMessage("§4§lSorry §r§7You don't have permission to use this command.");

            return;
        }

        World playerWorld = Bukkit.getWorld("plugins/XSkyBlock/" + configUtils.getPlayerIsland(player.getName()));

        if (playerWorld == null) {
            player.sendMessage("§4§lSorry §r§7You don't have an island.");
            return;
        }

        WorldBorder worldBorder = playerWorld.getWorldBorder();
        double centerX = worldBorder.getCenter().getX();
        double centerZ = worldBorder.getCenter().getZ();
        double size = worldBorder.getSize() / 2.0;

        int minX = (int) (centerX - size);
        int maxX = (int) (centerX + size);
        int minZ = (int) (centerZ - size);
        int maxZ = (int) (centerZ + size);

        int result = 0;

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = -64; y <= 320; y++) {
                    Block block = playerWorld.getBlockAt(x, y, z);
                    if (!block.getType().isSolid()) continue;

                    Integer value = blockValues.get(block.getType());
                    if (value != null) {
                        result += value;
                    }
                }
            }
        }
        configUtils.saveIslandLevel(configUtils.getPlayerIsland(player.getName()), result);
        player.sendMessage("§a§lIsland Level §r§7is " + result + " §7points.");
    }

    private Map<Material, Integer> loadBlockValues() {
        Map<Material, Integer> map = new HashMap<>();
        FileConfiguration config = configUtils.getConfig();

        if (config.isConfigurationSection("block-values")) {
            for (String key : config.getConfigurationSection("block-values").getKeys(false)) {
                try {
                    Material material = Material.valueOf(key);
                    int value = config.getInt("block-values." + key);
                    map.put(material, value);
                } catch (IllegalArgumentException e) {
                    Bukkit.getLogger().warning("[XSkyBlock] Material invalide dans block-values: " + key);
                }
            }
        }
        return map;
    }
}

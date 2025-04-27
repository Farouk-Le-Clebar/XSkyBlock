package com.xskyblock.island;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xskyblock.config.RankUtils;
import com.xskyblock.config.UserUtils;

public class IslandSpawnPoint {
    private UserUtils userUtils;
    private RankUtils rankUtils;

    public IslandSpawnPoint(UserUtils userUtils, RankUtils rankUtils) {
        this.userUtils = userUtils;
        this.rankUtils = rankUtils;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return false;
        }

        String playerRank = userUtils.getRank(sender.getName());
        if (!rankUtils.hasPermission(playerRank, "XSkyBlock.island.setworldspawn")) {
            sender.sendMessage("§4§lSorry §r§7You don't have permission to use this command.");
            return true;
        }

        Player player = (Player) sender;
        World playerWorld = player.getWorld();

        if (playerWorld.getName().contains(player.getName())) {
            Location location = player.getLocation();
            Block block = playerWorld.getBlockAt(location.getBlockX(), location.getBlockY() - 1, location.getBlockZ());

            if (block.getType() == Material.AIR) {
                player.sendMessage("§4§lSorry §r§7You cannot set the spawn point in mid-air. Please stand on a solid block.");
                return false;
            } 
            playerWorld.setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
            player.sendMessage("§2§lSuccessful §r§7Spawn point set to (" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + ")");
        } else {
            sender.sendMessage("§4§lSorry §r§7You are not allowed to use this command here. ");
        }
        return true;
    }
}

package com.xskyblock.island;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xskyblock.config.ConfigUtils;

public class IslandTeleport {
    private ConfigUtils configUtils;

    public IslandTeleport(ConfigUtils configUtils) {
        this.configUtils = configUtils;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;

        String islandName = configUtils.getPlayerIsland(player.getName());

        if (islandName == null)
            islandName = player.getName();

        player.setInvulnerable(true);
        teleportPlayerToMap(player, islandName);
        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("XSkyBlock"), () -> {
            player.setInvulnerable(false);
        }, 20L);
        return true;
    }

    private void teleportPlayerToMap(Player player, String islandName) {
        String worldName = "plugins/XSkyBlock/" + islandName;
        File worldFolder = new File(Bukkit.getWorldContainer(), worldName);

        if (!worldFolder.exists()) {
            player.sendMessage("§6§lWarning §r§7Please create your island first (/is create)");
            return;
        }

        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            loadWorld(worldName);
            world = Bukkit.getWorld(worldName);
        }

        if (world != null) {
            Location spawnLocation = world.getSpawnLocation();

            spawnLocation.add(0.5, 0, 0.5);
            player.teleport(spawnLocation);
            player.sendMessage("§2§lSuccessful §r§7Your are now on your island");
        } else {
            player.sendMessage("§4§lSorry §r§7An error occurred while teleporting you to your island");
        }
    }

    private void loadWorld(String worldName) {
        WorldCreator creator = new WorldCreator(worldName);
        World world = Bukkit.createWorld(creator);

        if (world == null) {
            throw new IllegalArgumentException("World could not be loaded");
        }
    }
}

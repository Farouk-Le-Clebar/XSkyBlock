package com.xskyblock.mapHandler;

import java.io.File;

import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class TeleportToMap {
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }
        teleportPlayerToMap((Player) sender);
        return true;
    }

    private void teleportPlayerToMap(Player player) {
        String worldName = "plugins/XSkyBlock/" + player.getName();
        File worldFolder = new File(Bukkit.getWorldContainer(), worldName);

        if (!worldFolder.exists()) {
            player.sendMessage("Please create your island first (/is create)");
            return;
        }

        World world = Bukkit.getWorld(worldName);
    
        if (world == null) {
            loadWorld(worldName);
            world = Bukkit.getWorld(worldName);
        }
    
        if (world != null) {
            Location spawnLocation = world.getSpawnLocation();

            player.teleport(spawnLocation);
            player.sendMessage("Teleportation to your island...");
        } else {
            player.sendMessage("Unable to teleport. Please contact an administrator.");
        }
    }

    private void loadWorld(String worldName) {
        WorldCreator creator = new WorldCreator(worldName);
        World world = Bukkit.createWorld(creator);
    
        if (world == null) {
            throw new IllegalArgumentException("World could not be loaded. Please contact an administrator.");
        }
    }
}

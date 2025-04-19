package com.xskyblock.mapHandler;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class MapCreator {

    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;

        try {
            copyOriginalMap("islands/" + sender.getName());
            loadWorld("plugins/islands/" + sender.getName());
            World world = Bukkit.getWorld("plugins/islands/" + sender.getName());
            world.setSpawnLocation(9, 65, 2);
            player.teleport(world.getSpawnLocation());
        } catch (IOException e) {
            player.sendMessage("Failed to create the map: " + e.getMessage());
        }

        return true;
    }

    private void copyOriginalMap(String destinationPath) throws IOException {
        File pluginsFolder = Bukkit.getPluginManager().getPlugin("XSkyBlock").getDataFolder().getParentFile();
    
        Path source = pluginsFolder.toPath().resolve("original_map");
    
        Path destination = pluginsFolder.toPath().resolve(destinationPath);
    
        if (!Files.exists(source)) {
            throw new IOException("Source map folder does not exist: " + source.toString());
        }
    
        if (!Files.exists(destination)) {
            Files.createDirectories(destination);
        }
    
        Files.walk(source).forEach(sourcePath -> {
            try {
                Path targetPath = destination.resolve(source.relativize(sourcePath));
                if (Files.isDirectory(sourcePath)) {
                    if (!Files.exists(targetPath)) {
                        Files.createDirectories(targetPath);
                    }
                } else {
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to copy map files: " + e.getMessage(), e);
            }
        });
    }
    

    private void loadWorld(String mapName) {
        WorldCreator creator = new WorldCreator(mapName);
        World world = Bukkit.createWorld(creator);

        if (world == null) {
            throw new IllegalArgumentException("World could not be loaded.");
        }
    }
}
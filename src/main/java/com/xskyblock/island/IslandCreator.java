package com.xskyblock.island;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xskyblock.config.ConfigUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class IslandCreator {
    private ConfigUtils configUtils;

    public IslandCreator(ConfigUtils configUtils) {
        this.configUtils = configUtils;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;
        if (configUtils.playerHavingIsland(player.getName())) {
            player.sendMessage("§4§lSorry §r§7You already have an island");
            return false;
        }
        player.sendMessage("§6§lWarning §r§7Your island is being created, please wait...");
        try {
            copyOriginalMap("XSkyBlock/" + sender.getName());
            loadWorld("plugins/XSkyBlock/" + sender.getName());
            World world = Bukkit.getWorld("plugins/XSkyBlock/" + sender.getName());
            configUtils.setNewPlayerIsland(sender.getName(), sender.getName(), "owner");
            world.setSpawnLocation(8, 67, 7);
            player.teleport(world.getSpawnLocation());
            player.sendMessage("§2§lSuccessful §r§7Your island has been created, teleporting you to your island...");
        } catch (IOException e) {
            player.sendMessage("§4§lSorry §r§7An error occurred while creating your island, please try again later");
        }
        return true;
    }

    private void copyOriginalMap(String destinationPath) throws IOException {
        File pluginFolder = Bukkit.getPluginManager().getPlugin("XSkyBlock").getDataFolder();
        File destinationDir = new File(pluginFolder.getParentFile(), destinationPath);
    
        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }

        ClassLoader classLoader = getClass().getClassLoader();

        String[] filesToCopy = {
            "original_map/level.dat",
            "original_map/region/r.0.0.mca",
            "original_map/session.lock"
        };

        for (String resourcePath : filesToCopy) {
            try {
                File outFile = new File(destinationDir, resourcePath.replace("original_map/", ""));
                File parentDir = outFile.getParentFile();
                if (!parentDir.exists()) parentDir.mkdirs();

                try (var in = classLoader.getResourceAsStream(resourcePath)) {
                    if (in == null) throw new IOException("Impossible de trouver la ressource : " + resourcePath);
                    Files.copy(in, outFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                throw new IOException("Error while copying file : " + resourcePath, e);
            }
        }
    }

    private void loadWorld(String mapName) {
        WorldCreator creator = new WorldCreator(mapName);
        World world = Bukkit.createWorld(creator);

        if (world == null) {
            throw new IllegalArgumentException("World could not be loaded.");
        }
    }
}
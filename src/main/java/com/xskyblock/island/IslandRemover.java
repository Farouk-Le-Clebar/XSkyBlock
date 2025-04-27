package com.xskyblock.island;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xskyblock.config.ConfigUtils;
import com.xskyblock.config.RankUtils;
import com.xskyblock.config.UserUtils;

public class IslandRemover {
    private ConfigUtils configUtils;
    private UserUtils userUtils;
    private RankUtils rankUtils;

    public IslandRemover(ConfigUtils configUtils, UserUtils userUtils, RankUtils rankUtils) {
        this.configUtils = configUtils;
        this.userUtils = userUtils;
        this.rankUtils = rankUtils;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return false;
        }

        String playerRank = rankUtils.getRank(sender.getName());
        if (!rankUtils.hasPermission(playerRank, "XSkyBlock.island.remove")) {
            sender.sendMessage("§4§lSorry §r§7You don't have permission to use this command.");
            return false;
        }

        Player player = (Player) sender;

        String worldName = "plugins/XSkyBlock/" + player.getName();
        File worldFolder = new File(Bukkit.getWorldContainer(), worldName);

        if (!worldFolder.exists()) {
            player.sendMessage("§4§lSorry §r§7You don't have any island");
            return false;
        }
        if (Bukkit.getWorld(worldName) != null) {
            if (!Bukkit.getServer().unloadWorld(worldName, false)) {
                player.sendMessage("§4§lSorry §r§7An error occurred while unloading your island world, you're not supposed to be on the island");
                return false;
            }
        }
        if (deleteFolder(worldFolder)) {
            configUtils.removeIsland(player.getName());
            player.sendMessage("§2§lSuccessful §r§7Your island has been removed");
        } else {
            player.sendMessage("§4§lSorry §r§7An error occurred while removing your island");
        }
        return true;
    }

    private boolean deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        return folder.delete();
    }
}

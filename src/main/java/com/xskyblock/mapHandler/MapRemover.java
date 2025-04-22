package com.xskyblock.mapHandler;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MapRemover {
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }
        Player player = (Player) sender;

        String worldName = "plugins/XSkyBlock/" + player.getName();
        File worldFolder = new File(Bukkit.getWorldContainer(), worldName);

        if (!worldFolder.exists()) {
            player.sendMessage("§4§lSorry §r§8You don't have any island");
            return false;
        }
        if (!Bukkit.getServer().unloadWorld(worldName, false)) {
            player.sendMessage("§4§lSorry §r§8An error occurred while unloading your island world, you're not supposed to be on the island");
            return false;
        }
        if (deleteFolder(worldFolder)) {
            player.sendMessage("§2§lSuccessful §r§7Your island has been removed");
        } else {
            player.sendMessage("§4§lSorry §r§8An error occurred while removing your island");
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

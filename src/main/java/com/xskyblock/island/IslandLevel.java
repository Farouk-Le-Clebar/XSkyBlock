package com.xskyblock.island;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xskyblock.config.ConfigUtils;

public class IslandLevel implements CommandExecutor {
    private ConfigUtils configUtils;

    public IslandLevel(ConfigUtils configUtils) {
        this.configUtils = configUtils;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;

        return true;
    }

    public int getLevel(String playerName) {
        String worldPath = "plugins/XSkyBlock/" + configUtils.getPlayerIsland(playerName);

        World world = Bukkit.getWorld(worldPath);
        return 0;
    }
}

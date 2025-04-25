package com.xskyblock.globalCommand;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof org.bukkit.entity.Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        World world = player.getServer().getWorld("world");
        player.teleport(world.getSpawnLocation());
        player.sendMessage("§2§lSuccessful §r§7You have been teleported to spawn.");
        return true;
    }
}

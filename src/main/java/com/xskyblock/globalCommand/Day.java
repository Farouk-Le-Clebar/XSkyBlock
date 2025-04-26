package com.xskyblock.globalCommand;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Day implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {    
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;

        World world = player.getWorld();
        world.setTime(0);
        player.sendMessage("§2§lSuccessful §r§7You have set the time to day.");
        return true;
    }
}

package com.xskyblock.market;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§8Only players can use this command.");
            return false;
        }
        Player player = (Player) sender;

        player.setAllowFlight(!player.getAllowFlight());
        if (player.getAllowFlight()) {
            player.sendMessage("§2§lSuccessful §r§7Fly enabled");
        } else {
            player.sendMessage("§2§lSuccessful §r§7Fly disabled");
        }
        return true;
    }    
}

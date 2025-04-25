package com.xskyblock.globalCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class God implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;

        player.setInvulnerable(!player.isInvulnerable());
        if (player.isInvulnerable()) {
            player.sendMessage("§2§lSuccessful §r§7God mode enabled");
        } else {
            player.sendMessage("§2§lSuccessful §r§7God mode disabled");
        }
        return true;    
    }
}

package com.xskyblock.globalCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 1) {
            Player target = player.getServer().getPlayer(args[0]);

            if (target == null) {
                player.sendMessage("§4§lSorry §r§7Player not found (" + args[0] + ").");
                return true;
            }
            target.setHealth(20);
            target.setFoodLevel(20);
            target.sendMessage("§2§lSuccessful §r§7" + player.getName() + " just healed you.");
            player.sendMessage("§2§lSuccessful §r§7You have healed " + target.getName() + " (" + args[0] + ").");
            return true;
        }

        player.setHealth(20);
        player.setFoodLevel(20);
        player.sendMessage("§2§lSuccessful §r§7You have been healed.");
        return true;
    }
}

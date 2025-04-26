package com.xskyblock.money;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xskyblock.config.UserUtils;

public class MoneyGet {
     private final UserUtils userUtils;

    public MoneyGet(UserUtils userUtil) {
        this.userUtils = userUtil;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            int money = userUtils.getMoney(player.getName());
            player.sendMessage("§2§l" + player.getName() + " §r§7has " + money + "§7$");
            return true;
        }
        if (args.length == 2) {
            int money = userUtils.getMoney(args[1]);
            if (money == -1) {
                player.sendMessage("§4§lSorry §r§7Player not found.");
                return true;
            }
            player.sendMessage("§2§l" + args[1] + " §r§7has " + money + "§7$");
        }
        return true;
    }
}

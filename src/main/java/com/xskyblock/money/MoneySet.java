package com.xskyblock.money;

import org.bukkit.command.CommandSender;

import com.xskyblock.config.UserUtils;

public class MoneySet {
     private final UserUtils userUtils;

    public MoneySet(UserUtils userUtil) {
        this.userUtils = userUtil;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length != 3) {
            sender.sendMessage("§6§lUsage §r§7/money set <player> <amount>");
            return true;
        }

        if (!isInteger(args[2])) {
            sender.sendMessage("§4§lSorry §r§7The amount must be an integer.");
            return true;
        }
        
        String playerName = args[1];

        if (!userUtils.isPlayerExists(playerName)) {
            sender.sendMessage("§4§lSorry §r§7This player does not exist.");
            return true;
        }

        String amountStr = args[2];

        userUtils.setPlayerMoney(playerName, Integer.parseInt(amountStr));

        sender.sendMessage("§2§lSuccessful §r§7You have set " + playerName + "'s money to " + amountStr + "$");

        return true;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean playerExists(String playerName) {
        return org.bukkit.Bukkit.getPlayer(playerName) != null;
    }
}

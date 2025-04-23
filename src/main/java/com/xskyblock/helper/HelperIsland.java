package com.xskyblock.helper;

import org.bukkit.command.CommandSender;

public class HelperIsland {
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage("§8------§r§6§lXSkyBlock Helper§r§8------");
        sender.sendMessage("      §8§oIsland Commands :");
        sender.sendMessage("§6/is §r§7Teleport to your island");
        sender.sendMessage("§6/is tp §r§7Teleport to your island");
        sender.sendMessage("§6/is help §r§7Display this help message");
        sender.sendMessage("§6/is create §r§7Create a new island");
        sender.sendMessage("§6/is remove §r§7Remove your island");
        sender.sendMessage("§6/is invite <player> §r§7Invite a player to your island");
        sender.sendMessage("§6/is teleport §r§7Teleport to your island");
        sender.sendMessage("§6/is setworldspawn §r§7Set your island spawn point");
        sender.sendMessage("      §8§oMoney Commands :");
        sender.sendMessage("§6/money §r§7Get your money");
        sender.sendMessage("§6/money <player> §r§7Get other player's money");
        return true;
    }
}

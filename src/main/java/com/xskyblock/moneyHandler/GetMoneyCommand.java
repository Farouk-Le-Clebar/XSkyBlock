package com.xskyblock.moneyHandler;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetMoneyCommand {
    private final MoneyUtils moneyUtils;

    public GetMoneyCommand(MoneyUtils moneyUtils) {
        this.moneyUtils = moneyUtils;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            int money = moneyUtils.getMoney(player.getName());
            player.sendMessage("§2§l" + player.getName() + " §r§7has " + money + "§7$");
            return true;
        }
        if (args.length == 2) {
            int money = moneyUtils.getMoney(args[1]);
            if (money == -1) {
                player.sendMessage("§4§lSorry §r§7Player not found.");
                return true;
            }
            player.sendMessage("§2§l" + args[1] + " §r§7has " + money + "§7$");
        }
        return true;
    }
}

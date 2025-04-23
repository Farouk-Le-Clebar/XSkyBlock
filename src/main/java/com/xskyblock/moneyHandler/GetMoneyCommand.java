package com.xskyblock.moneyHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetMoneyCommand implements CommandExecutor {
    private final MoneyUtils moneyUtils;

    public GetMoneyCommand(MoneyUtils moneyUtils) {
        this.moneyUtils = moneyUtils;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            int money = moneyUtils.getMoney(player.getName());
            player.sendMessage("§2§l" + player.getName() + " §r§7has " + money + "§7$");
            return true;
        }
        if (args.length == 1) {
            int money = moneyUtils.getMoney(args[0]);
            if (money == -1) {
                player.sendMessage("§4§lSorry §r§7Player not found.");
                return true;
            }
            player.sendMessage("§2§l" + args[0] + " §r§7has " + money + "§7$");
        }
        return true;
    }
}

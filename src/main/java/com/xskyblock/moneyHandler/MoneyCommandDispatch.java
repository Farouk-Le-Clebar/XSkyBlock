package com.xskyblock.moneyHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MoneyCommandDispatch implements CommandExecutor {
    private final MoneyUtils moneyUtils = new MoneyUtils();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            new GetMoneyCommand(moneyUtils).execute(sender, new String[]{"get"});
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "get":
                new GetMoneyCommand(moneyUtils).execute(sender, args);
                break;
            case "set":
                new SetMoneyCommand(moneyUtils).execute(sender, args);
                break;
            default:
                sender.sendMessage("§6§lUsage §r§7/money <get|set>");
                break;
        }
        return true;
    }
}

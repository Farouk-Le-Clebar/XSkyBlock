package com.xskyblock.money;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.xskyblock.config.UserUtils;

public class MoneyDispatcher implements CommandExecutor, TabCompleter {
    private final UserUtils userUtils;

    public MoneyDispatcher(UserUtils userUtil) {
        this.userUtils = userUtil;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            new MoneyGet(userUtils).execute(sender, new String[]{"get"});
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "get":
                new MoneyGet(userUtils).execute(sender, args);
                break;
            case "set":
                new MoneySet(userUtils).execute(sender, args);
                break;
            default:
                sender.sendMessage("§6§lUsage §r§7/money <get|set>");
                break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("money")) {
            if (args.length == 1) {
                List<String> subcommands = new ArrayList<>();
                subcommands.add("get");
                subcommands.add("set");
                return subcommands;
            } else if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
                List<String> playerNames = new ArrayList<>();
                Bukkit.getOnlinePlayers().forEach(player -> playerNames.add(player.getName()));
                return playerNames;
            } else if (args.length == 3 && args[0].equalsIgnoreCase("set")) {
                List<String> amounts = new ArrayList<>();
                amounts.add("<amount>");
                return amounts;
            }
        }
        return null;
    }
}

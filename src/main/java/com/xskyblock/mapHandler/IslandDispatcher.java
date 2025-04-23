package com.xskyblock.mapHandler;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.xskyblock.helper.IslandCommands;
import com.xskyblock.spawnPointHandler.SpawnPointHandler;

public class IslandDispatcher implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            new TeleportToMap().execute(sender, args);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "create":
                new MapCreator().execute(sender, args);
                break;
            case "teleport":
                new TeleportToMap().execute(sender, args);
                break;
            case "tp":
                new TeleportToMap().execute(sender, args);
                break;
            case "setworldspawn":
                new SpawnPointHandler().execute(sender, args);
                break;
            case "help":
                new IslandCommands().execute(sender, args);
                break;
            case "remove":
                new MapRemover().execute(sender, args);
                break;
            default:
                sender.sendMessage("Unknown subcommand. Use /is <create|teleport>");
                break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("is")) {
            if (args.length == 1) {
                List<String> subcommands = new ArrayList<>();
                subcommands.add("tp");
                subcommands.add("help");
                subcommands.add("create");
                subcommands.add("remove");
                subcommands.add("teleport");
                subcommands.add("setworldspawn");
                return subcommands;
            }
        }
        return null;
    }
}

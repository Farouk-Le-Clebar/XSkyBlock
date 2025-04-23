package com.xskyblock;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import com.xskyblock.custommotd.CustomMotd;
import com.xskyblock.gamemodeHandler.GamemodeHandler;
import com.xskyblock.helper.IslandCommands;
import com.xskyblock.mapHandler.MapCreator;
import com.xskyblock.mapHandler.MapRemover;
import com.xskyblock.mapHandler.TeleportToMap;
import com.xskyblock.market.FlyCommand;
import com.xskyblock.market.Market;
import com.xskyblock.moneyHandler.GetMoneyCommand;
import com.xskyblock.moneyHandler.MoneyUtils;
import com.xskyblock.playerJoiningHandler.PlayerJoiningHandler;
import com.xskyblock.spawnPointHandler.SpawnPointHandler;

public class XSkyBlock extends JavaPlugin implements TabCompleter {
    private final Market market = new Market();
    private final MoneyUtils moneyUtils = new MoneyUtils();

    @Override
    public void onEnable() {
        getLogger().info("XSkyBlock has been enabled!");
        getCommand("is").setExecutor(this);
        getCommand("is").setTabCompleter(this);
        getCommand("market").setExecutor(market);
        getCommand("gm").setExecutor(new GamemodeHandler());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("money").setExecutor(new GetMoneyCommand(moneyUtils));
        getServer().getPluginManager().registerEvents(market, this);
        getServer().getPluginManager().registerEvents(new CustomMotd(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoiningHandler(moneyUtils), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("XSkyBlock has been disabled!");
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
}
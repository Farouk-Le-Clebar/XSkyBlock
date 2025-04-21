package com.xskyblock;

import com.xskyblock.gamemodeHandler.GamemodeHandler;
import com.xskyblock.mapHandler.*;
import com.xskyblock.market.Market;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class XSkyBlock extends JavaPlugin {
    private final Market market = new Market();

    @Override
    public void onEnable() {
        
        getLogger().info("XSkyBlock has been enabled!");
        getCommand("is").setExecutor(this);
        getCommand("market").setExecutor(market);
        getCommand("gm").setExecutor(new GamemodeHandler());
        getServer().getPluginManager().registerEvents(market, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("XSkyBlock has been disabled!");
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
            default:
                sender.sendMessage("Unknown subcommand. Use /is <create|teleport>");
                break;
        }
        return true;
    }
}
package com.xskyblock;

import com.xskyblock.mapHandler.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class XSkyBlock extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("XSkyBlock has been enabled!");
        getCommand("is").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /is <create|teleport>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "create":
                new MapCreator().execute(sender, args);
                break;
            case "teleport":
                new TeleportToMap().execute(sender, args);
                break;
            default:
                sender.sendMessage("Unknown subcommand. Use /is <create|teleport>");
                break;
        }
        return true;
    }
}
package com.xskyblock.island;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.xskyblock.config.ConfigUtils;
import com.xskyblock.config.RankUtils;
import com.xskyblock.config.UserUtils;
import com.xskyblock.helper.HelperIsland;
import com.xskyblock.island.worldBorder.WorldBorderHandler;

public class IslandDispatcher implements CommandExecutor, TabCompleter {
    private ConfigUtils configUtils;
    private IslandTeleport islandTeleport;
    private RankUtils rankUtils;
    private UserUtils userUtils;
    private WorldBorderHandler worldBorder;

    public IslandDispatcher(ConfigUtils configUtils, RankUtils rankUtils, UserUtils userUtils) {
        this.configUtils = configUtils;
        this.rankUtils = rankUtils;
        this.userUtils = userUtils;
        this.islandTeleport = new IslandTeleport(configUtils, userUtils, rankUtils);
        this.worldBorder = new WorldBorderHandler(userUtils, rankUtils);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            islandTeleport.execute(sender, args);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "create":
                new IslandCreator(configUtils, rankUtils, userUtils).execute(sender, args);
                break;
            case "teleport":
                islandTeleport.execute(sender, args);
                break;
            case "tp":
                islandTeleport.execute(sender, args);
                break;
            case "setworldspawn":
                new IslandSpawnPoint(userUtils, rankUtils).execute(sender, args);
                break;
            case "help":
                new HelperIsland().execute(sender, args);
                break;
            case "remove":
                new IslandRemover(configUtils, userUtils, rankUtils).execute(sender, args);
                break;
            case "invite":
                new IslandInvitePlayer(configUtils, userUtils, rankUtils).execute(sender, args);
                break;
            case "worldborder":
                worldBorderDispatcher(sender, args);
                break;
            case "level":
                new IslandLevel(configUtils, userUtils, rankUtils).execute(sender, args);
                break;
            default:
                sender.sendMessage("Unknown subcommand.");
                break;
        }
        return true;
    }

    public void worldBorderDispatcher(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("§4§lSorry §r§7Please specify a subcommand.");
            return;
        }

        switch (args[1].toLowerCase()) {
            case "show":
                worldBorder.show(sender, args);
                break;
            case "hide":
                worldBorder.hide(sender, args);
                break;
            default:
                sender.sendMessage("Usage: /is worldborder <show|hide>");
                break;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("is")) {
            if (args.length == 1) {
                List<String> subcommands = new ArrayList<>();
                subcommands.add("tp");
                subcommands.add("help");
                subcommands.add("level");
                subcommands.add("create");
                subcommands.add("remove");
                subcommands.add("invite");
                subcommands.add("teleport");
                subcommands.add("worldborder");
                subcommands.add("setworldspawn");
                return subcommands;
            }
            if (args.length == 2 && args[0].equalsIgnoreCase("worldborder")) {
                List<String> subcommands = new ArrayList<>();
                subcommands.add("show");
                subcommands.add("hide");
                return subcommands;
            }
        }
        return null;
    }
}

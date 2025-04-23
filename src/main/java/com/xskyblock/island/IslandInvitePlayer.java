package com.xskyblock.island;

import com.xskyblock.config.ConfigUtils;

import org.bukkit.command.CommandSender;

public class IslandInvitePlayer {

    private ConfigUtils configUtils;

    public IslandInvitePlayer(ConfigUtils configUtils) {
        this.configUtils = configUtils;
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("§6§lWarning §r§7Please specify a player name.");
            return;
        }

        String playerName = args[1];

        if (configUtils.playerHavingIsland(playerName)) {
            sender.sendMessage("§4§lSorry §r§7This player already has an island.");
            return;
        }

        if (!configUtils.isPlayerExists(playerName)) {
            sender.sendMessage("§4§lSorry §r§7This player does not exist.");
            return;
        }
        configUtils.setNewPlayerIsland(sender.getName(), playerName, "member");
    }
}

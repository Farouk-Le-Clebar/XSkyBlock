package com.xskyblock.island;

import com.xskyblock.config.ConfigUtils;
import com.xskyblock.config.RankUtils;
import com.xskyblock.config.UserUtils;

import org.bukkit.command.CommandSender;

public class IslandInvitePlayer {
    private ConfigUtils configUtils;
    private UserUtils userUtils;
    private RankUtils rankUtils;

    public IslandInvitePlayer(ConfigUtils configUtils, UserUtils userUtils, RankUtils rankUtils) {
        this.configUtils = configUtils;
        this.userUtils = userUtils;
        this.rankUtils = rankUtils;
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("§6§lWarning §r§7Please specify a player name.");
            return;
        }

        String playerRank = userUtils.getRank(sender.getName());
        if (!rankUtils.hasPermission(playerRank, "XSkyBlock.island.invite")) {
            sender.sendMessage("§4§lSorry §r§7You don't have permission to use this command.");
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

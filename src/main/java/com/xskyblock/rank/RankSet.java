package com.xskyblock.rank;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xskyblock.config.RankUtils;
import com.xskyblock.config.UserUtils;

public class RankSet {
     private final UserUtils userUtils;
    private final RankUtils rankUtils;

    public RankSet(UserUtils userUtil, RankUtils rankUtil) {
        this.userUtils = userUtil;
        this.rankUtils = rankUtil;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length != 3) {
            sender.sendMessage("§6§lUsage §r§7/money set <player> <amount>");
            return true;
        }
        
        String playerName = args[1];

        if (!userUtils.isPlayerExists(playerName)) {
            sender.sendMessage("§4§lSorry §r§7This player does not exist.");
            return true;
        }

        String rankStr = args[2];

        if (!rankUtils.isRankExists(rankStr)) {
            sender.sendMessage("§4§lSorry §r§7This rank does not exist.");
            return true;
        }

        if (!(sender instanceof Player)) {
            setRank(playerName, rankStr, sender);
            return true;
        }

        Player player = (Player) sender;

        String playerRank = userUtils.getRank(player.getName());

        if (rankUtils.hasPermission(playerRank, "XSkyblock.rank.set")) {

            setRank(playerName, rankStr, sender);
            return true;
        }
        
        player.sendMessage("§4§lSorry §r§7You do not have permission to use this command.");
        return false;
    }

    public void setRank(String playerName, String rankStr, CommandSender sender) {
        userUtils.setPlayerRank(playerName, rankStr);

        sender.sendMessage("§2§lSuccessful §r§7You have set " + playerName + "'s rank to " + rankStr);
    }
}

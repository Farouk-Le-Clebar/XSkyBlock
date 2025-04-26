package com.xskyblock.rank;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xskyblock.config.RankUtils;
import com.xskyblock.config.UserUtils;

public class RankGet {
     private final UserUtils userUtils;
    private final RankUtils rankUtils;

    public RankGet(UserUtils userUtil, RankUtils rankUtil) {
        this.userUtils = userUtil;
        this.rankUtils = rankUtil;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return false;
        }

        if (args.length > 2) {
            sender.sendMessage("§6§lUsage §r§7/rank get <player>");
            return false;
        }

        Player player = (Player) sender;

        String rank = userUtils.getRank(args[1]);
        if (rank == "ERROR") {
            player.sendMessage("§4§lSorry §r§7Player not found.");
            return false;
        }
        String prefix = rankUtils.getRankPrefix(rank);

        player.sendMessage("§2§l" + args[1] + " §r§7has rank " + prefix);
    
        return true;
    }
}

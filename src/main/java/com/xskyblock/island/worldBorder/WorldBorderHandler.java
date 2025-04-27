package com.xskyblock.island.worldBorder;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xskyblock.config.RankUtils;
import com.xskyblock.config.UserUtils;

public class WorldBorderHandler {
    private UserUtils userUtils;
    private RankUtils rankUtils;

    public WorldBorderHandler(UserUtils userUtils, RankUtils rankUtils) {
        this.userUtils = userUtils;
        this.rankUtils = rankUtils;
    }

    public boolean hide(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return true;
        }

        String playerRank = userUtils.getRank(sender.getName());
        if (!rankUtils.hasPermission(playerRank, "XSkyBlock.island.worldborder.hide")) {
            sender.sendMessage("§4§lSorry §r§7You don't have permission to use this command.");
            return true;
        }

        Player player = (Player) sender;

        player.setResourcePack("https://www.dropbox.com/scl/fi/gvu64lenvgdk1xjnzmw1t/XSkyBlock.zip?rlkey=rdjw96b122jatqpatqaf7djxs&st=zwyyeua3&dl=1");
        return true;
    }
    
    public boolean show(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return true;
        }

        String playerRank = userUtils.getRank(sender.getName());
        if (!rankUtils.hasPermission(playerRank, "XSkyBlock.island.worldborder.show")) {
            sender.sendMessage("§4§lSorry §r§7You don't have permission to use this command.");
            return true;
        }

        Player player = (Player) sender;

        player.setResourcePack("");
        return true;
    }
}

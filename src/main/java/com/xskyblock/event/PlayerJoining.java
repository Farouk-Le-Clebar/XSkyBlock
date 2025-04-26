package com.xskyblock.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.xskyblock.config.UserUtils;
import com.xskyblock.scoreboard.ScoreboardHandler;

public class PlayerJoining implements Listener {
    private ScoreboardHandler scoreboardHandler;

        private UserUtils userUtils;

    public PlayerJoining(UserUtils userUtil) {
        this.userUtils = userUtil;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        userUtils.initPlayer(event.getPlayer().getName());
        event.getPlayer().setResourcePack("https://www.dropbox.com/scl/fi/gvu64lenvgdk1xjnzmw1t/XSkyBlock.zip?rlkey=rdjw96b122jatqpatqaf7djxs&st=zwyyeua3&dl=1");
        scoreboardHandler.addScoreBoardToPlayer(event.getPlayer());
    }
}

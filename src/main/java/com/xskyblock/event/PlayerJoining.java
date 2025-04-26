package com.xskyblock.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.xskyblock.config.ConfigUtils;
import com.xskyblock.config.UserUtils;
import com.xskyblock.scoreboard.ScoreboardHandler;

public class PlayerJoining implements Listener {
    private UserUtils userUtils;
    private ConfigUtils configUtils;

    public PlayerJoining(UserUtils userUtil, ConfigUtils configUtil) {
        this.userUtils = userUtil;
        this.configUtils = configUtil;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ScoreboardHandler scoreboardHandler = new ScoreboardHandler(userUtils);

        userUtils.initPlayer(event.getPlayer().getName());
        if (!configUtils.isPlayerExists(event.getPlayer().getName()))
            configUtils.setPlayerAlreadyConnected(event.getPlayer().getName());
        event.getPlayer().setResourcePack("https://www.dropbox.com/scl/fi/gvu64lenvgdk1xjnzmw1t/XSkyBlock.zip?rlkey=rdjw96b122jatqpatqaf7djxs&st=zwyyeua3&dl=1");
        scoreboardHandler.addScoreBoardToPlayer(event.getPlayer());
    }
}

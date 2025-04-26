package com.xskyblock.playerJoining;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.xskyblock.config.UserUtils;

public class PlayerJoiningHandler implements Listener {
    private UserUtils userUtils;

    public PlayerJoiningHandler(UserUtils userUtil) {
        this.userUtils = userUtil;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        userUtils.initPlayer(event.getPlayer().getName());
    }
}

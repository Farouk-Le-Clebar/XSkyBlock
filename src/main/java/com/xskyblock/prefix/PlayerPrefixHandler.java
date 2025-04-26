package com.xskyblock.prefix;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.xskyblock.config.RankUtils;
import com.xskyblock.config.UserUtils;

public class PlayerPrefixHandler implements Listener {
    private final UserUtils userUtils;
    private final RankUtils rankUtils;
    
    public PlayerPrefixHandler(UserUtils userUtil, RankUtils rankUtil) {
        this.userUtils = userUtil;
        this.rankUtils = rankUtil;
    }
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String rank = userUtils.getRank(player.getName());
        String prefix = rankUtils.getRankPrefix(rank);
        
        event.setFormat(prefix + player.getName() + " : " + message);
    }
}

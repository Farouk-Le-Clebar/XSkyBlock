package com.xskyblock.playerJoining;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.xskyblock.money.MoneyUtils;

public class PlayerJoiningHandler implements Listener {
    private final MoneyUtils moneyUtils = new MoneyUtils();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        moneyUtils.initPlayerMoney(event.getPlayer().getName());
    }
}

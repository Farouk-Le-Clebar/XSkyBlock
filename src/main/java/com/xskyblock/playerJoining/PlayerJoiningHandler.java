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
        event.getPlayer().setResourcePack("https://www.dropbox.com/scl/fi/3hlramzd5ji1nayysq8a6/Invisible-World-Border.zip?rlkey=814qsc1l9ta9c0jlxo1mymqwn&e=1&st=dbbueybp&dl=1");
    }
}

package com.xskyblock.playerJoining;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.xskyblock.money.MoneyUtils;
import com.xskyblock.scoreboard.ScoreboardHandler;

public class PlayerJoiningHandler implements Listener {
    private final MoneyUtils moneyUtils = new MoneyUtils();
    private final ScoreboardHandler scoreboardHandler = new ScoreboardHandler();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().setResourcePack("https://www.dropbox.com/scl/fi/gvu64lenvgdk1xjnzmw1t/XSkyBlock.zip?rlkey=rdjw96b122jatqpatqaf7djxs&st=zwyyeua3&dl=1");
        moneyUtils.initPlayerMoney(event.getPlayer().getName());
        scoreboardHandler.addScoreBoardToPlayer(event.getPlayer());
    }
}

package com.xskyblock.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.xskyblock.money.MoneyUtils;
import com.xskyblock.scoreboard.ScoreboardHandler;

public class PlayerJoining implements Listener {
    private MoneyUtils moneyUtils = new MoneyUtils();
    private ScoreboardHandler scoreboardHandler;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        scoreboardHandler = new ScoreboardHandler(moneyUtils);

        event.getPlayer().setResourcePack("https://www.dropbox.com/scl/fi/gvu64lenvgdk1xjnzmw1t/XSkyBlock.zip?rlkey=rdjw96b122jatqpatqaf7djxs&st=zwyyeua3&dl=1");
        moneyUtils.initPlayerMoney(event.getPlayer().getName());
        scoreboardHandler.addScoreBoardToPlayer(event.getPlayer());
    }
}

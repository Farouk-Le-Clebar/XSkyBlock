package com.xskyblock.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.xskyblock.money.MoneyUtils;

public class ScoreboardHandler {
    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private MoneyUtils moneyUtils = new MoneyUtils();

    public ScoreboardHandler(MoneyUtils moneyUtils) {
        this.moneyUtils = moneyUtils;
    }

    public void addScoreBoardToPlayer(Player player) {
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("XSkyBlock", "dummy", "§6§lXSkyBlock");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);

        String playersEntry = "§1";
        String moneyEntry = "§2";
        String spacerEntry = "§3";

        scoreboard.registerNewTeam("players").addEntry(playersEntry);
        scoreboard.registerNewTeam("money").addEntry(moneyEntry);
        scoreboard.registerNewTeam("spacer").addEntry(spacerEntry);

        objective.getScore(spacerEntry).setScore(3);
        objective.getScore(playersEntry).setScore(2);
        objective.getScore(moneyEntry).setScore(1);

        Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("XSkyBlock"), () -> {
            scoreboard.getTeam("players").setPrefix("§aPlayers: §f" + Bukkit.getOnlinePlayers().size());
            scoreboard.getTeam("money").setPrefix("§aMoney: §f" + moneyUtils.getMoney(player.getName()) + "$");
            scoreboard.getTeam("spacer").setPrefix("");
        }, 0L, 20L);
    }
}

package com.xskyblock.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardHandler {
    private ScoreboardManager manager = Bukkit.getScoreboardManager();

    public void addScoreBoardToPlayer(Player player) {
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("XSkyBlock", "dummy", "§6§lXSkyBlock");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        Score line1 = objective.getScore("§7Bienvenue !");
        line1.setScore(3);

        Score line2 = objective.getScore("§eÎle: §fSkyBlock");
        line2.setScore(2);

        Score line3 = objective.getScore("§eJoueurs: §f" + Bukkit.getOnlinePlayers().size());
        line3.setScore(1);
        player.setScoreboard(scoreboard);
    }
}

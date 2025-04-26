package com.xskyblock.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.xskyblock.config.ConfigUtils;
import com.xskyblock.config.UserUtils;

public class ScoreboardHandler {
    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private UserUtils userUtils;
    private ConfigUtils configUtils;

    public ScoreboardHandler(UserUtils userUtil, ConfigUtils configUtils) {
        this.userUtils = userUtil;
        this.configUtils = configUtils;
    }

    public void addScoreBoardToPlayer(Player player) {
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("XSkyBlock", "dummy", "§6§lXSkyBlock");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);

        scoreboard.registerNewTeam("spacer").addEntry("§1");
        scoreboard.registerNewTeam("money").addEntry("§2");
        scoreboard.registerNewTeam("island-level").addEntry("§3");
        scoreboard.registerNewTeam("players").addEntry("§4");

        objective.getScore("§1").setScore(4);
        objective.getScore("§2").setScore(3);
        objective.getScore("§3").setScore(2);
        objective.getScore("§4").setScore(1);

        Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("XSkyBlock"), () -> {
            scoreboard.getTeam("spacer").setPrefix("");
            scoreboard.getTeam("players").setPrefix("§aPlayers: §f" + Bukkit.getOnlinePlayers().size());
            scoreboard.getTeam("money").setPrefix("§aMoney: §f" + userUtils.getMoney(player.getName()) + "$");
            scoreboard.getTeam("island-level").setPrefix("§aIsland Level: §f" + configUtils.getIslandLevel(player.getName()));
        }, 0L, 20L);
    }
}

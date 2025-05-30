package com.xskyblock;

import org.bukkit.plugin.java.JavaPlugin;

import com.xskyblock.config.ConfigUtils;
import com.xskyblock.config.RankUtils;
import com.xskyblock.config.UserUtils;
import com.xskyblock.event.Chat;
import com.xskyblock.event.PlayerJoining;
import com.xskyblock.globalCommand.Day;
import com.xskyblock.globalCommand.Fly;
import com.xskyblock.globalCommand.GamemodeHandler;
import com.xskyblock.globalCommand.God;
import com.xskyblock.globalCommand.Heal;
import com.xskyblock.globalCommand.Night;
import com.xskyblock.globalCommand.Spawn;
import com.xskyblock.island.IslandDispatcher;
import com.xskyblock.island.cobbleGenerator.IslandCustomCobbleGenerator;
import com.xskyblock.market.Market;
import com.xskyblock.money.MoneyDispatcher;
import com.xskyblock.motd.CustomMotd;
import com.xskyblock.prefix.PlayerPrefixHandler;
import com.xskyblock.rank.RankDispatcher;

public class XSkyBlock extends JavaPlugin {
    private IslandDispatcher islandDispatcher;
    private UserUtils userUtils;
    private RankUtils rankUtils;
    private MoneyDispatcher moneyDispatcher;
    private RankDispatcher rankDispatcher;
    private ConfigUtils configUtils;
    
    @Override
    public void onEnable() {
        getLogger().info("XSkyBlock has been enabled!");

        loadDefaultConfig();

        userUtils = new UserUtils(this);
        rankUtils = new RankUtils(this);
        configUtils = new ConfigUtils();

        moneyDispatcher = new MoneyDispatcher(userUtils);
        rankDispatcher = new RankDispatcher(userUtils, rankUtils);
        islandDispatcher = new IslandDispatcher(configUtils, rankUtils, userUtils);

        getCommand("is").setExecutor(islandDispatcher);
        getCommand("is").setTabCompleter(islandDispatcher);

        getCommand("money").setExecutor(moneyDispatcher);
        getCommand("money").setTabCompleter(moneyDispatcher);

        getCommand("rank").setExecutor(rankDispatcher);
        getCommand("rank").setTabCompleter(rankDispatcher);

        getCommand("market").setExecutor(new Market());
        
        setupGlobalCommands();
        
        getServer().getPluginManager().registerEvents(new Market(), this);
        getServer().getPluginManager().registerEvents(new CustomMotd(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoining(userUtils, configUtils), this);
        getServer().getPluginManager().registerEvents(new IslandCustomCobbleGenerator(), this);
        getServer().getPluginManager().registerEvents(new PlayerPrefixHandler(userUtils, rankUtils), this);
        getServer().getPluginManager().registerEvents(new Chat(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("XSkyBlock has been disabled!");
    }

    public void setupGlobalCommands() {
        getCommand("gm").setExecutor(new GamemodeHandler());
        getCommand("fly").setExecutor(new Fly());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("god").setExecutor(new God());
        getCommand("heal").setExecutor(new Heal());
        getCommand("day").setExecutor(new Day());
        getCommand("night").setExecutor(new Night());
    }

    public void loadDefaultConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
}
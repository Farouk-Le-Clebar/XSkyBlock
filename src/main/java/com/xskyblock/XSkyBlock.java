package com.xskyblock;

import org.bukkit.plugin.java.JavaPlugin;

import com.xskyblock.config.MenuUtils;
import com.xskyblock.config.RankUtils;
import com.xskyblock.config.UserUtils;
import com.xskyblock.gamemode.GamemodeHandler;
import com.xskyblock.island.IslandDispatcher;
import com.xskyblock.island.cobbleGenerator.IslandCustomCobbleGenerator;
import com.xskyblock.market.FlyCommand;
import com.xskyblock.market.Market;
import com.xskyblock.money.MoneyDispatcher;
import com.xskyblock.motd.CustomMotd;
import com.xskyblock.playerJoining.PlayerJoiningHandler;
import com.xskyblock.prefix.PlayerPrefixHandler;
import com.xskyblock.rank.RankDispatcher;
import com.xskyblock.spawn.SpawnCommand;


public class XSkyBlock extends JavaPlugin {
    private final IslandDispatcher islandDispatcher = new IslandDispatcher();
    private UserUtils userUtils;
    private RankUtils rankUtils;
    private MenuUtils menuUtils;
    private MoneyDispatcher moneyDispatcher;
    private RankDispatcher rankDispatcher;
    
    @Override
    public void onEnable() {
        getLogger().info("XSkyBlock has been enabled!");

        loadDefaultConfig();
        
        userUtils = new UserUtils(this);
        rankUtils = new RankUtils(this);
        menuUtils = new MenuUtils();
    
        moneyDispatcher = new MoneyDispatcher(userUtils);
        rankDispatcher = new RankDispatcher(userUtils, rankUtils, menuUtils);

        getCommand("is").setExecutor(islandDispatcher);
        getCommand("is").setTabCompleter(islandDispatcher);

        getCommand("money").setExecutor(moneyDispatcher);
        getCommand("money").setTabCompleter(moneyDispatcher);

        getCommand("rank").setExecutor(rankDispatcher);
        getCommand("rank").setTabCompleter(rankDispatcher);

        getCommand("market").setExecutor(new Market(menuUtils));
        
        getCommand("gm").setExecutor(new GamemodeHandler());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        
        getServer().getPluginManager().registerEvents(new Market(menuUtils), this);
        getServer().getPluginManager().registerEvents(new CustomMotd(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoiningHandler(userUtils), this);
        getServer().getPluginManager().registerEvents(new IslandCustomCobbleGenerator(), this);
        getServer().getPluginManager().registerEvents(new PlayerPrefixHandler(userUtils, rankUtils), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("XSkyBlock has been disabled!");
    }

    public void loadDefaultConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
}
package com.xskyblock;

import org.bukkit.plugin.java.JavaPlugin;

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
import com.xskyblock.playerJoining.PlayerJoiningHandler;

public class XSkyBlock extends JavaPlugin {
    private final Market market = new Market();
    private final IslandDispatcher islandDispatcher = new IslandDispatcher();
    private final MoneyDispatcher moneyDispatcher = new MoneyDispatcher();

    @Override
    public void onEnable() {
        getLogger().info("XSkyBlock has been enabled!");

        loadDefaultConfig();

        getCommand("is").setExecutor(islandDispatcher);
        getCommand("is").setTabCompleter(islandDispatcher);

        getCommand("money").setExecutor(moneyDispatcher);
        getCommand("money").setTabCompleter(moneyDispatcher);

        getCommand("market").setExecutor(market);
        getServer().getPluginManager().registerEvents(market, this);

        setupGlobalCommands();

        getServer().getPluginManager().registerEvents(new CustomMotd(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoiningHandler(), this);
        getServer().getPluginManager().registerEvents(new IslandCustomCobbleGenerator(), this);
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
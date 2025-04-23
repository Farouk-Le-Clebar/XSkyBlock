package com.xskyblock;

import org.bukkit.plugin.java.JavaPlugin;

import com.xskyblock.gamemode.GamemodeHandler;
import com.xskyblock.island.IslandDispatcher;
import com.xskyblock.market.FlyCommand;
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

        getCommand("is").setExecutor(islandDispatcher);
        getCommand("is").setTabCompleter(islandDispatcher);

        getCommand("money").setExecutor(moneyDispatcher);
        getCommand("money").setTabCompleter(moneyDispatcher);

        getCommand("market").setExecutor(market);
        getServer().getPluginManager().registerEvents(market, this);

        getCommand("gm").setExecutor(new GamemodeHandler());
        getCommand("fly").setExecutor(new FlyCommand());

        getServer().getPluginManager().registerEvents(new CustomMotd(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoiningHandler(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("XSkyBlock has been disabled!");
    }
}
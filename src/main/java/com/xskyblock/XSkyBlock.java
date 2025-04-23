package com.xskyblock;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import com.xskyblock.custommotd.CustomMotd;
import com.xskyblock.gamemodeHandler.GamemodeHandler;
import com.xskyblock.mapHandler.IslandDispatcher;
import com.xskyblock.market.FlyCommand;
import com.xskyblock.market.Market;
import com.xskyblock.moneyHandler.MoneyDispatcher;
import com.xskyblock.playerJoiningHandler.PlayerJoiningHandler;

public class XSkyBlock extends JavaPlugin implements TabCompleter {
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
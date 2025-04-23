package com.xskyblock.motd;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class CustomMotd implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        String line1 = "                      §b★ §fXSkyBlock §b★";
        String line2 = "         §dSkyblock Above the Clouds §7[§f1.21.5§7]";
        event.setMotd(line1 + "\n" + line2);
    }
}

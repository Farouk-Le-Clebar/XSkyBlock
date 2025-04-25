package com.xskyblock.event;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        if (message.equals("ez") || message.equals("EZ") || message.equals("Ez") || message.equals("eZ")) {
            event.setMessage("Je suis gay !");
        }
    }
}

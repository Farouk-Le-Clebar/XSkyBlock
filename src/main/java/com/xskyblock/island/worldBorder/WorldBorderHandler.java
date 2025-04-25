package com.xskyblock.island.worldBorder;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldBorderHandler {
    public boolean hide(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;

        player.setResourcePack("https://www.dropbox.com/scl/fi/3hlramzd5ji1nayysq8a6/Invisible-World-Border.zip?rlkey=814qsc1l9ta9c0jlxo1mymqwn&e=1&st=dbbueybp&dl=1");
        return true;
    }
    
    public boolean show(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        
        player.setResourcePack("");
        return true;
    }
}

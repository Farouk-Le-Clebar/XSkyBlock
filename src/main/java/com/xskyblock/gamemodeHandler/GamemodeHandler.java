package com.xskyblock.gamemodeHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage("§6§lWarning §r§7Please specify a gamemode.");
            return false;
        }

        String gamemode = args[0].toLowerCase();
        Player player = (Player) sender;

        if (gamemode.equals("0")) {
            player.setGameMode(org.bukkit.GameMode.SURVIVAL);
            return true;
        }
        if (gamemode.equals("1")) {
            player.setGameMode(org.bukkit.GameMode.CREATIVE);
            return true;
        }
        if (gamemode.equals("2")) {
            player.setGameMode(org.bukkit.GameMode.ADVENTURE);
            return true;
        }
        if (gamemode.equals("3")) {
            player.setGameMode(org.bukkit.GameMode.SPECTATOR);
            return true;
        }

        if (gamemode.equals("s")) {
            player.setGameMode(org.bukkit.GameMode.SURVIVAL);
            return true;
        }
        if (gamemode.equals("c")) {
            player.setGameMode(org.bukkit.GameMode.CREATIVE);
            return true;
        }
        if (gamemode.equals("a")) {
            player.setGameMode(org.bukkit.GameMode.ADVENTURE);
            return true;
        }
        if (gamemode.equals("sp")) {
            player.setGameMode(org.bukkit.GameMode.SPECTATOR);
            return true;
        }

        if (gamemode.equals("survival")) {
            player.setGameMode(org.bukkit.GameMode.SURVIVAL);
            return true;
        }
        if (gamemode.equals("creative")) {
            player.setGameMode(org.bukkit.GameMode.CREATIVE);
            return true;
        }
        if (gamemode.equals("adventure")) {
            player.setGameMode(org.bukkit.GameMode.ADVENTURE);
            return true;
        }
        if (gamemode.equals("spectator")) {
            player.setGameMode(org.bukkit.GameMode.SPECTATOR);
            return true;
        }
        return false;
    }
}

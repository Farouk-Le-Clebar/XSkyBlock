package com.xskyblock.rank;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.xskyblock.config.MenuUtils;
import com.xskyblock.config.UserUtils;

public class RankMenu {
    private final UserUtils userUtils;

    public RankMenu(UserUtils userUtil) {
        this.userUtils = userUtil;
    }

    public boolean execute(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Commande réservée aux joueurs !");
            return false;
        }
        
        Player player = (Player) sender;
        
        Inventory rankMenu = Bukkit.createInventory(null, 45, "§8✦ §aMenu des Rangs §8✦");

        // ---- 1. Bouton "Quêtes de rang" ----
        ItemStack quests = MenuUtils.createItem(Material.BOOK, "§b✦ §aQuêtes de Rang §b✦",
        false,
            "§7Accomplis des quêtes pour débloquer",
            "§7des récompenses exclusives selon ton rang.",
            "",
            "§a§l>> §aClique pour découvrir"
        );

        // ---- 2. Bouton "Voir tous les Rangs" ----
        ItemStack allRanks = MenuUtils.createItem(Material.PAPER, "§d✦ §bVoir Tous les Rangs §d✦",
            false,
            "§7Liste complète des rangs disponibles",
            "§7avec leurs avantages exclusifs.",
            "",
            "§b§l>> §bClique pour découvrir"
        );

        // ---- 3. Boutique de rangs ----
        ItemStack rankShop = MenuUtils.createItem(Material.EMERALD, "§a✦ §2Boutique de Rang §a✦",
            false,
            "§7Achète des objets exclusifs selon ton rang actuel.",
            "",
            "§2§l>> §2Clique pour ouvrir la boutique"
        );

        // ---- 4. Voir son propre rang ----
        String playerRank = userUtils.getRank(player.getName());
        ItemStack yourRank = MenuUtils.createItem(Material.NAME_TAG, "§b✦ §fTon Rang Actuel : §a" + playerRank + " §b✦",
            false,
            "§7Voici ton rang actuel sur le serveur.",
            "§7Gravis les échelons pour débloquer encore plus de récompenses !"
        );

        rankMenu.setItem(13, yourRank);
        rankMenu.setItem(19, quests);
        rankMenu.setItem(21, allRanks);
        rankMenu.setItem(23, rankShop);

        // ---- Ouverture du menu ----
        player.openInventory(rankMenu);
        return true;
    }
}

package com.xskyblock.market;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.xskyblock.config.MenuUtils;

public class Market implements CommandExecutor, Listener {

    private MenuUtils menuUtils;

    public Market(MenuUtils menuUtil) {
        this.menuUtils = menuUtil;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        createMenu(player);

        return true;
    }

    public void createMenu(Player player) {
        Inventory inventory = menuUtils.createInventory("Market", 54);

        ItemStack pane = menuUtils.createItem(Material.BLACK_STAINED_GLASS_PANE, "", false, "");

        ItemStack fly = menuUtils.createItem(Material.FEATHER, "§b✦ §aFly (1Hour) §b✦",
        true,
            "§7Use this item to fly for 1 hour.",
            "§7Right-click to activate."
        );

        ItemStack timeSetDay = menuUtils.createItem(Material.CLOCK, "§b✦ §aTime set day §b✦",
        true,
            "§7Buy to set the time to day.",
            "§7Right-click to activate."
        );

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, pane);
        }

        inventory.setItem(0, fly);
        inventory.setItem(1, timeSetDay);
        
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Market")) {
            int slot = event.getSlot();

            if (slot == 0) {
                Player player = (Player) event.getWhoClicked();
                if (player.getAllowFlight()) {
                    event.getWhoClicked().sendMessage("You are already flying!");
                    event.setCancelled(true);
                    return;
                }
                event.getWhoClicked().sendMessage("You have purchased a fly item!");
                player.setAllowFlight(true);
                player.sendMessage("You can now fly for 1 hour!");
                
                Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("XSkyBlock"), () -> {
                    player.setAllowFlight(false);
                    player.sendMessage("Your flight ability has expired!");
                }, 20L * 60 * 60);
            }

            if (slot == 1) {
                event.getWhoClicked().getWorld().setTime(0);
            }
            event.setCancelled(true);
        }
    }
}

package com.xskyblock.market;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemFlag;

public class Market implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        Inventory inventory = sender.getServer().createInventory(null, 54, "Market");

        ItemStack enchantedFeather = new ItemStack(Material.FEATHER, 1);
        ItemMeta meta = enchantedFeather.getItemMeta();
        if (meta != null) {
            meta.addEnchant(Enchantment.FEATHER_FALLING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);            
            meta.setDisplayName("§bFly (1Hour)");
            meta.setLore(List.of("§bUse this item to fly for 1 hour.", "§bRight-click to activate."));
            enchantedFeather.setItemMeta(meta);
        }

        ItemStack enchantedClock = new ItemStack(Material.CLOCK, 1);
        ItemMeta clockMeta = enchantedClock.getItemMeta();
        if (clockMeta != null) {
            clockMeta.addEnchant(Enchantment.AQUA_AFFINITY, 1, true);
            clockMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);            
            clockMeta.setDisplayName("§bTime set day");
            clockMeta.setLore(List.of("§bBuy to set the time to day.", "§bRight-click to activate."));
            enchantedClock.setItemMeta(clockMeta);
        }

        inventory.setItem(0, enchantedFeather);
        inventory.setItem(1, enchantedClock);

        player.openInventory(inventory);
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Market")) {
            if (event.getCurrentItem().getType() == Material.CLOCK) {
                event.getWhoClicked().sendMessage("You have purchased a time set day item!");
                World world = event.getWhoClicked().getWorld();
                world.setTime(0);
            }
            if (event.getCurrentItem().getType() == Material.FEATHER) {
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
            event.setCancelled(true);
        }
    }
}

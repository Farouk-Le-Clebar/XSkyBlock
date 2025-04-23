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

    private final MenuUtility menuUtility = new MenuUtility();

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
        Inventory inventory = player.getServer().createInventory(null, 54, "Market");

        List<ItemStack> items = List.of(
            new ItemStack(Material.FEATHER, 1),
            new ItemStack(Material.CLOCK, 1)
        );

        menuUtility.changeItemMeta(items.get(0), "§bFly (1Hour)", List.of("§bUse this item to fly for 1 hour.", "§bRight-click to activate."), true);
        menuUtility.changeItemMeta(items.get(1), "§bTime set day", List.of("§bBuy to set the time to day.", "§bRight-click to activate."), true);

        menuUtility.setItemInventory(items, inventory);

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

            if (slot == 2) {
                event.getWhoClicked().getWorld().setTime(0);
            }
            event.setCancelled(true);
        }
    }
}

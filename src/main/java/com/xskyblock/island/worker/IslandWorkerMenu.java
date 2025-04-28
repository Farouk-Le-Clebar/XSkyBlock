package com.xskyblock.island.worker;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.xskyblock.config.MenuUtils;

public class IslandWorkerMenu {
    public void openMenu(Player player, int workerLevel) {
        Inventory menu = MenuUtils.createInventory("§6§lIsland Worker", 9 * 3);

        ItemStack pane = MenuUtils.createItem(Material.BLACK_STAINED_GLASS_PANE, " ", false, "");

        for (int i = 0; i < menu.getSize(); i++)
            menu.setItem(i, pane);

        if (workerLevel == 1) {
            menu.setItem(6, null);
            menu.setItem(7, null);
            menu.setItem(8, null);
            menu.setItem(15, null);
            menu.setItem(16, null);
            menu.setItem(17, null);
            menu.setItem(24, null);
            menu.setItem(25, null);
            menu.setItem(26, null);
        }

        player.openInventory(menu);
    }
}

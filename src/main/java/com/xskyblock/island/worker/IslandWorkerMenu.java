package com.xskyblock.island.worker;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;

import com.xskyblock.config.MenuUtils;
import com.xskyblock.island.worker.WorkerInventoryHolder;

import java.util.ArrayList;
import java.util.List;

public class IslandWorkerMenu {
    private String workerId;
    private String menuId;
    private List <ItemStack> items = new ArrayList<>();
    private Inventory menu;

    public IslandWorkerMenu(String workerId) {
        this.workerId = workerId;
        this.menu = Bukkit.createInventory(new WorkerInventoryHolder(workerId), 9 * 3, "§6§lIsland Worker | " + workerId);
    }

    public void openMenu(Player player, int workerLevel) {

        ItemStack pane = MenuUtils.createItem(Material.BLACK_STAINED_GLASS_PANE, " ", false, "");

        for (int i = 0; i < this.menu.getSize(); i++)
            this.menu.setItem(i, pane);

        if (workerLevel == 1) {
            this.menu.setItem(0, null);
            this.menu.setItem(1, null);
            this.menu.setItem(2, null);
            this.menu.setItem(9, null);
            this.menu.setItem(10, null);
            this.menu.setItem(11, null);
            this.menu.setItem(18, null);
            this.menu.setItem(19, null);
            this.menu.setItem(20, null);
        }
        for (ItemStack item : items) {
            this.menu.setItem(this.menu.firstEmpty(), item);
        }

        player.openInventory(this.menu);
    }

    public String getWorkerId() {
        return this.workerId;
    }

    public void addItem(ItemStack item) {
        for (ItemStack existingItem : this.items) {
            if (existingItem.isSimilar(item)) {
                existingItem.setAmount(existingItem.getAmount() + item.getAmount());
                return;
            }
        }
        this.items.add(item);
    }
}

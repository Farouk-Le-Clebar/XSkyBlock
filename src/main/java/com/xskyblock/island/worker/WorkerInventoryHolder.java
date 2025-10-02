package com.xskyblock.island.worker;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class WorkerInventoryHolder implements InventoryHolder {
    private final String workerId;

    public WorkerInventoryHolder(String workerId) {
        this.workerId = workerId;
    }

    public String getWorkerId() {
        return workerId;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}

package com.xskyblock.island.cobbleGenerator;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

public class IslandCustomCobbleGenerator implements Listener {
    @EventHandler
    public void onMerge (BlockFormEvent e) {
        if (e.getNewState().getType() == Material.COBBLESTONE) {
            int randomNum = (int)(Math.random() * 101);
            if (randomNum >= 0 && randomNum <= 80) {
                e.getNewState().setType(Material.COBBLESTONE);
            }
            else if (randomNum >= 81 && randomNum <= 90) {
                e.getNewState().setType(Material.COAL_ORE);
            }
            else if (randomNum >= 91 && randomNum <= 95) {
                e.getNewState().setType(Material.IRON_ORE);
            }
            else if (randomNum >= 96 && randomNum <= 98) {
                e.getNewState().setType(Material.GOLD_ORE);
            }
            else if (randomNum == 99) {
                e.getNewState().setType(Material.DIAMOND_ORE);
            }
            else if (randomNum == 100) {
                e.getNewState().setType(Material.EMERALD_ORE);
            }
        }
    }
}

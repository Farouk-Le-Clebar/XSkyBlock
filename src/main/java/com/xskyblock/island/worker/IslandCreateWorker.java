package com.xskyblock.island.worker;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import com.xskyblock.config.ArmorStandUtils;
import com.xskyblock.config.ConfigUtils;
import com.xskyblock.config.RankUtils;
import com.xskyblock.config.UserUtils;

public class IslandCreateWorker implements Listener {
    private ConfigUtils configUtils;
    private UserUtils userUtils;
    private RankUtils rankUtils;
    private IslandWorkerMenu islandWorkerMenu = new IslandWorkerMenu();

    public IslandCreateWorker(ConfigUtils configUtils, UserUtils userUtils, RankUtils rankUtils) {
        this.configUtils = configUtils;
        this.userUtils = userUtils;
        this.rankUtils = rankUtils;
    }

    public void createWorker(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4§lSorry §r§7Only players can use this command.");
            return;
        }

        String playerRank = userUtils.getRank(sender.getName());
        if (!rankUtils.hasPermission(playerRank, "XSkyBlock.island.create.worker")) {
            sender.sendMessage("§4§lSorry §r§7You don't have permission to use this command.");
            return;
        }

        Player player = (Player) sender;
        String islandName = configUtils.getPlayerIsland(player.getName());

        World world = Bukkit.getWorld("plugins/XSkyBlock/" + islandName);

        if (world == null) {
            player.sendMessage("§6§lWarning §r§7You need to be on your island to use this command.");
            return;
        }

        ArmorStand armorStand = (ArmorStand) world.spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setSmall(true);
        armorStand.setInvisible(false);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName("§7Mining §5Worker §5I");
        armorStand.setCustomNameVisible(true);
        EntityEquipment entityEquipment = armorStand.getEquipment();
        entityEquipment.setHelmet(ArmorStandUtils.createCustomHead(
            "http://textures.minecraft.net/texture/c2854af3d42814a2e060949e5a5cbfad73c4ba3d3e77289b12d890b1249472ee"
        ));
        entityEquipment.setItemInMainHand(ArmorStandUtils.createItem(Material.WOODEN_PICKAXE, true));
        entityEquipment.setChestplate(ArmorStandUtils.createItem(Material.LEATHER_CHESTPLATE, false));
        entityEquipment.setLeggings(ArmorStandUtils.createItem(Material.LEATHER_LEGGINGS, false));
        entityEquipment.setBoots(ArmorStandUtils.createItem(Material.LEATHER_BOOTS, false));
        armorStand.setCanPickupItems(false);

        new BukkitRunnable() {
            double t = 0;
            int animationTick = 0;
            int breakProgress = 0;
            Block targetBlock = null;
        
            @Override
            public void run() {
                if (armorStand.isDead() || !armorStand.isValid()) {
                    cancel();
                    return;
                }

                double xRotation = Math.sin(t) * Math.toRadians(45);
                armorStand.setRightArmPose(new EulerAngle(xRotation, 0, 0));

                t += Math.PI / 10;
                animationTick++;

                if (animationTick >= 5) {
                    animationTick = 0;

                    if (targetBlock == null || targetBlock.getType() == Material.AIR) {
                        Vector direction = armorStand.getLocation().getDirection().normalize();
                        Location blockLocation = armorStand.getLocation().clone()
                            .add(0, 2, 0)
                            .add(direction.multiply(1.5));
                        targetBlock = blockLocation.getBlock();
                        breakProgress = 0;
                    }

                    if (targetBlock != null && targetBlock.getType() != Material.AIR) {
                        breakProgress++;

                        for (Player nearby : armorStand.getWorld().getPlayers())
                            nearby.sendBlockDamage(targetBlock.getLocation(), breakProgress / 9.0f);

                        if (breakProgress >= 9) {
                            targetBlock.breakNaturally();
                            targetBlock = null;
                        }
                    }
                }
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("XSkyBlock"), 0L, 2L);
    }

    @EventHandler
    public void onPlayerShiftRightClick(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) event.getRightClicked();
            if (armorStand.getCustomName() != null && armorStand.getCustomName().contains("§7Mining §5Worker")) {
                if (event.getPlayer().isSneaking()) {
                    event.setCancelled(true);
                    islandWorkerMenu.openMenu(event.getPlayer(), 1);
                } else {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("§6§lWarning §r§7Shift Righ-Click to interact with worker.");
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§6§lIsland Worker")) {
            if (event.getClickedInventory().getType() == InventoryType.PLAYER)
                return;

            int slot = event.getSlot();
            if (slot != 6 && slot != 7 && slot != 8 &&
                slot != 15 && slot != 16 && slot != 17 &&
                slot != 24 && slot != 25 && slot != 26)
            {
                event.setCancelled(true);
            }
        }
    }
}

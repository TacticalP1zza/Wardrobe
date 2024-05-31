package me.plainoldmoose.wardrobe;

package com.yourname.wardrobe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class WardrobePlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("Wardrobe Plugin Enabled");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Wardrobe Plugin Disabled");
    }

    // Command to open the wardrobe
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("wardrobe")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                openWardrobe(player);
                return true;
            } else {
                sender.sendMessage("This command can only be used by players, are you an alien????");
                return true;
            }
        }
        return false;
    }

    public void openWardrobe(Player player) {
        Inventory wardrobe = Bukkit.createInventory(null, 27, "Wardrobe");

        // Example: Add a set of armor
        wardrobe.setItem(0, new ItemStack(Material.DIAMOND_HELMET));
        wardrobe.setItem(1, new ItemStack(Material.DIAMOND_CHESTPLATE));
        wardrobe.setItem(2, new ItemStack(Material.DIAMOND_LEGGINGS));
        wardrobe.setItem(3, new ItemStack(Material.DIAMOND_BOOTS));
        

        player.openInventory(wardrobe);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Wardrobe")) {
            event.setCancelled(true);  // Prevent taking items out of the wardrobe
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null && clickedItem.getType() != Material.AIR) {
                // Equip the player with the clicked item
                switch (event.getSlot()) {
                    case 0: player.getInventory().setHelmet(clickedItem); break;
                    case 1: player.getInventory().setChestplate(clickedItem); break;
                    case 2: player.getInventory().setLeggings(clickedItem); break;
                    case 3: player.getInventory().setBoots(clickedItem); break;
                }
            }
        }
    }
}


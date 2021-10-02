package org.keyhame;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class TableFixer extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e){
        if((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) &&
                e.getItem()!=null && e.getItem().getType().name().equals("IC2_TE") &&
                e.getItem().getData().getData() == 88 ) ||
        (e.getClickedBlock() != null &&
                e.getClickedBlock().getType().name().contains("IC2_TE") &&
                e.getClickedBlock().getDrops(new ItemStack(Material.valueOf("IC2_WRENCH_NEW"))) != null &&
                e.getClickedBlock().getDrops(new ItemStack(Material.valueOf("IC2_WRENCH_NEW"))).size() == 1 &&
                ((ItemStack)e.getClickedBlock().getDrops(new ItemStack(Material.valueOf("IC2_WRENCH_NEW"))).toArray()[0]).getData().getData() == 88
                ))
        {
            getLogger().warning("Player "+e.getPlayer() + " try to use IC2_TE_CRAFTER's bug at "+ e.getPlayer().getLocation() +".");
            e.getPlayer().sendMessage(ChatColor.RED + "工业工作台已被禁止.");
            e.setCancelled(true);
        }
    }
}

package org.keyhame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Fixer extends JavaPlugin implements Listener {
    private FileConfiguration config;

    @Override
    public void onLoad() {
        saveDefaultConfig();
        config = getConfig();

    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        try {
            if(event.getInventory().getName().equals("ic2.te.batch_crafter")
                    && event.getCurrentItem().getType().name().equals("IC2_UPGRADE")){
                event.setCancelled(true);
                if(config.getBoolean("isShowOnServer",true)) {
                    getLogger().warning(ChatColor.RED + "Player {name:" + event.getWhoClicked().getName()
                            + ",uuid:" + event.getWhoClicked().getUniqueId()
                            + ",Location:{"+ event.getWhoClicked().getLocation().toString()
                            + "} try to use IC2_Batch_crafter's BUG!");
                }
                if(config.getBoolean("isShowToOp",false)){
                    for(OfflinePlayer player: Bukkit.getOperators()) {
                        if (player.isOnline()){
                            player.getPlayer().sendMessage(ChatColor.RED + "Player {name:" + event.getWhoClicked().getName()
                                    + ",uuid:" + event.getWhoClicked().getUniqueId()
                                    + ",Location:{"+ event.getWhoClicked().getLocation().toString()
                                    + "} try to use IC2_Batch_crafter's BUG!");
                        }
                    }
                }
                if(config.getBoolean("isBan",false)){
                    Player player;
                    if((player = Bukkit.getPlayer(event.getWhoClicked().getUniqueId())) == null){
                        getLogger().info("Can");
                    }
                    Bukkit.banIP(player.getAddress().getAddress().toString());
                    return;
                }
                if(config.getBoolean("warning.enabled",false)){

                }
            }
        }catch (NullPointerException ignored){}
    }
}

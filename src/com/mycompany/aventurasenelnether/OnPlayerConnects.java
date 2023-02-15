/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aventurasenelnether;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author asier
 */
public class OnPlayerConnects implements Listener{
    
    
    public Main instance;
    
    public OnPlayerConnects(Main instance){
        this.instance = instance;
    }
    
    /**
     * Cuando un jugador se conecta por primera vez, le llevará a un lugar seguro en el nether
     * @param e 
     */
    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onPlayerConnects(PlayerJoinEvent e) {
    
        Player player = e.getPlayer();
        String UUID = player.getUniqueId().toString();
        boolean exists = false;
        
        if(instance.getConfig().getConfigurationSection("players") != null){
            Set<String> listSet = instance.getConfig().getConfigurationSection("players").getKeys(false);
            List<String> list = new ArrayList<>();
            list.addAll(listSet);

            for(String id : list){
                if(id.equalsIgnoreCase(UUID)){
                    exists = true;
                }
            }
        }
        
        if(!exists){
            instance.getConfig().set("players."+UUID+".time", 0);
            instance.saveConfig();
            
            ItemStack item = new ItemStack(Material.RESPAWN_ANCHOR, 1);
            player.getInventory().addItem(item);
            //queda tpear al jugador al nether
            Location loc;
            
            World nether = Bukkit.getWorld(instance.getConfig().get("nether-spawn.world-name").toString());
            int x = (int) instance.getConfig().get("nether-spawn.x");
            int y = (int) instance.getConfig().get("nether-spawn.y");
            int z = (int) instance.getConfig().get("nether-spawn.z");
            
            loc = Resources.getProperLocationNether(nether, x, y, z);

            player.teleport(loc);
   
            ItemStack book = Resources.generateBook();
            
            player.getInventory().addItem(book);
        }
                
        
        
    }
}

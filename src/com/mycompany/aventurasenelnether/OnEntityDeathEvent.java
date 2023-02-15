/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aventurasenelnether;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 *
 * @author asier
 */
public class OnEntityDeathEvent implements Listener{
    
    public Main instance;
    
    public OnEntityDeathEvent(Main instance){
        this.instance = instance;
    }
    
    /**
     * Al matar al dragon se deshabilitará la maldición
     * @param e 
     */
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent e) {
        
        Entity entity = e.getEntity();
        Entity killer = e.getEntity().getKiller();

        if (killer instanceof Player){
            
            if(entity instanceof EnderDragon){
                instance.getConfig().set("isMaldicionOver", true);
                instance.saveConfig();

                Bukkit.broadcastMessage(ChatColor.GOLD+"LA MALDICIÓN DEL OVERWORLD HA DESAPARECIDO");
            }
            
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aventurasenelnether;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author asier
 */
public class OnSpawnAnchorInteract implements Listener{
    
    
    public Main instance;
    
    public OnSpawnAnchorInteract(Main instance){
        this.instance = instance;
    }
    
    
    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onSpawnAnchorInteract(PlayerInteractEvent e) {
        if(e.getClickedBlock().getType() == Material.RESPAWN_ANCHOR){
            if(e.getItem() != null){
                if(e.getItem().getType() == Material.GLOWSTONE){
                    e.getPlayer().sendMessage(ChatColor.DARK_RED+"No puedes hacer eso, si necesitas más vidas, has de craftear un nuevo respawn anchor");
                    e.setCancelled(true);
                }
            }

        }
    }
}

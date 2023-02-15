/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aventurasenelnether;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 *
 * @author asier
 */
public class OnBlockBreak implements Listener{
    
    public Main instance;
    
    public OnBlockBreak(Main instance){
        this.instance = instance;
    }
    
    /**
     * Al romper un spawn anchor, no lo dropeará
     * @param e 
     */
    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getBlock().getType() == Material.RESPAWN_ANCHOR){
            e.getBlock().setType(Material.AIR);
            e.setCancelled(true);
        }
    }
    
}

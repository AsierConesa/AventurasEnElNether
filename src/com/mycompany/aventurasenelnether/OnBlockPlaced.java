/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aventurasenelnether;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 *
 * @author asier
 */
public class OnBlockPlaced implements Listener{
    
    
    public Main instance;
    
    public OnBlockPlaced(Main instance){
        this.instance = instance;
    }
    
    /**
     * Al colocar un Spawn anchor, se autorrellenará
     * @param e 
     */
    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onBlockPlaced(BlockPlaceEvent e) {
        if(e.getBlockPlaced().getType() == Material.RESPAWN_ANCHOR){
            Location location = e.getBlockPlaced().getLocation();
            RespawnAnchor anchor = (RespawnAnchor)location.getBlock().getBlockData();
            anchor.setCharges(4);
            e.getBlockPlaced().setBlockData(anchor);
        }
    }
}

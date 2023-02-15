/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aventurasenelnether;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author asier
 */
public class OnPlayerInteractEvent implements Listener{
    
    public Main instance;
    
    public OnPlayerInteractEvent(Main instance){
        this.instance = instance;
    }
    
    /**
     * Al clicar en una cama, no se permitira guardar spawn y se emitirá un mensaje
     * @param e 
     */
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if(e.getClickedBlock() != null){
            if(e.getClickedBlock().getType().name().endsWith("_BED")){
                e.getPlayer().sendMessage(ChatColor.GOLD+"No puedes dormir en camas, solo puedes usar respawn anchors");
                e.setCancelled(true);   
            }
        }

    }
    
}

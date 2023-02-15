/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aventurasenelnether;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author asier
 */
public class OnItemDrop implements Listener{
    
    
    public Main instance;
    
    public OnItemDrop(Main instance){
        this.instance = instance;
    }
            
     
    @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
    public void onItemDrop(PlayerDropItemEvent e) {
        
        Player player = e.getPlayer();
        Item item = e.getItemDrop();
        if(item.getItemStack().getType().equals(Material.ENDER_EYE) || item.getItemStack().getType().equals(Material.CRYING_OBSIDIAN) || item.getItemStack().getType().equals(Material.GILDED_BLACKSTONE)){
        
            new BukkitRunnable(){

                int countdown = 8;
                @Override
                public void run(){
                    if(countdown <= 0 || !player.isOnline() ){ //si esto pasa 
                        this.cancel(); //sal del bucle
                        return;
                        
                    }


                    Location loc = item.getLocation();
                    if(loc.getBlock().getType().equals(Material.CAULDRON)){
                        Collection<Entity> entities = e.getItemDrop().getWorld().getNearbyEntities(loc, 0.8, 0.8, 0.8);
                        Iterator<Entity> iterator = entities.iterator();
                        boolean isFoundedEye = false;
                        boolean isFoundedObs = false;
                        boolean isFoundedGil = false;
                        ItemStack itemOjo = null;
                        ItemStack itemObs = null;
                        ItemStack itemGil = null;
                        
                        if(item.getItemStack().getType().equals(Material.ENDER_EYE)){
                            isFoundedEye = true;
                            itemOjo = item.getItemStack();
                        } 
                        else if(item.getItemStack().getType().equals(Material.GILDED_BLACKSTONE)){
                            isFoundedGil = true;
                            itemGil = item.getItemStack();
                        } 
                        else if(item.getItemStack().getType().equals(Material.CRYING_OBSIDIAN)){
                            isFoundedObs = true;
                            itemObs = item.getItemStack();
                        }
                        
                        while (iterator.hasNext() && (!isFoundedEye || !isFoundedObs || !isFoundedGil)) {
                            Entity entity = iterator.next();
                            if(entity instanceof Item){
                                Item item = (Item) entity;
                                
                                if(item.getItemStack().getType().equals(Material.CRYING_OBSIDIAN)) {
                                    isFoundedObs = true;
                                    itemObs = item.getItemStack();
                                }
                                if(item.getItemStack().getType().equals(Material.GILDED_BLACKSTONE)) {
                                    isFoundedGil = true;
                                    itemGil = item.getItemStack();
                                }
                                if(item.getItemStack().getType().equals(Material.ENDER_EYE)) {
                                    isFoundedEye = true;
                                    itemOjo = item.getItemStack();
                                }
                            }
                            
                        }

                        if((isFoundedEye && isFoundedObs && isFoundedGil)){
                            //GENERA EL AMULETO
                            ItemStack amuleto = new ItemStack(Material.PRISMARINE_SHARD);
                            ItemMeta meta = amuleto.getItemMeta();
                            meta.setCustomModelData(1);
                            meta.setDisplayName(ChatColor.BLUE+""+ChatColor.BOLD+"✼ Amuleto del tiempo ✼");
                            List<String> lore = new ArrayList<>();
                            lore.add("Siempre que esté en tu mano izquierda");
                            lore.add("Se consumirá al pasar al overworld");
                            lore.add(ChatColor.BLACK+".");
                            lore.add(ChatColor.GOLD+"Te otorgará 5 minutos de inmunidad");
                            lore.add(ChatColor.GOLD+"A la maldición del Overworld");
                            meta.setLore(lore);
                            amuleto.setItemMeta(meta);
                            amuleto.setAmount(1);
                            
                            itemOjo.setAmount(0);
                            itemGil.setAmount(0);
                            itemObs.setAmount(0);
                            
                            
                            player.getWorld().dropItemNaturally(loc, amuleto);
                            player.playSound(loc, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 5.0F, 1F);
                            
                            player.getWorld().spawnParticle(Particle.FLAME, loc, 40);
                            
                            this.cancel(); //sal del bucle

                        }
                    }


                    countdown--; 
                }
            }.runTaskTimer(instance, 0, 5); //Repeating task with 0 ticks initial delay, run once per 5 ticks. Make sure you pass a valid instance of your plugin.

            
            
        }
        
        
        //Amuleto del tiempo
        
        if(item.getItemStack().getType().equals(Material.REDSTONE) || item.getItemStack().getType().equals(Material.GLOWSTONE_DUST) || item.getItemStack().getType().equals(Material.GLASS)){
            new BukkitRunnable(){

                int countdown = 8;
                @Override
                public void run(){
                    if(countdown <= 0 || !player.isOnline() ){ //si esto pasa 
                        this.cancel(); //sal del bucle
                        return;
                        
                    }


                    Location loc = item.getLocation();
                    if(loc.getBlock().getType().equals(Material.CAULDRON)){
                        Collection<Entity> entities = e.getItemDrop().getWorld().getNearbyEntities(loc, 0.8, 0.8, 0.8);
                        Iterator<Entity> iterator = entities.iterator();
                        boolean isFoundedRed = false;
                        boolean isFoundedGlo = false;
                        boolean isFoundedGla = false;
                        ItemStack itemRed = null;
                        ItemStack itemGlo = null;
                        ItemStack itemGla = null;
                        
                        switch (item.getItemStack().getType()) {
                            case REDSTONE -> {
                                isFoundedRed = true;
                                itemRed = item.getItemStack();
                            }
                            case GLOWSTONE_DUST -> {
                                isFoundedGlo = true;
                                itemGlo = item.getItemStack();
                            }
                            case GLASS -> {
                                isFoundedGla = true;
                                itemGla = item.getItemStack();
                            }
                            default -> {
                            }
                        }
                        
                        while (iterator.hasNext() && (!isFoundedRed || !isFoundedGlo || !isFoundedGla)) {
                            Entity entity = iterator.next();
                            if(entity instanceof Item){
                                Item item = (Item) entity;
                                if(item.getItemStack().getType().equals(Material.REDSTONE)) {
                                    isFoundedRed = true;
                                    itemRed = item.getItemStack();
                                }
                                if(item.getItemStack().getType().equals(Material.GLOWSTONE_DUST)) {
                                    isFoundedGlo = true;
                                    itemGlo = item.getItemStack();
                                }
                                if(item.getItemStack().getType().equals(Material.GLASS)) {
                                    isFoundedGla = true;
                                    itemGla = item.getItemStack();
                                }
                            }
                            
                        }

                        if((isFoundedRed && isFoundedGlo && isFoundedGla)){
                            if(player.getLevel() >= 10){
                                //GENERA EL AMULETO

                                player.setLevel(player.getLevel()-10);

                                ItemStack amuleto = new ItemStack(Material.PRISMARINE_CRYSTALS);
                                ItemMeta meta = amuleto.getItemMeta();
                                meta.setCustomModelData(1);
                                meta.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"⚜ Amuleto del Sol ⚜");
                                List<String> lore = new ArrayList<>();
                                lore.add("Siempre que esté en tu mano izquierda");
                                lore.add(ChatColor.GOLD+"Te hará inmune a la maldición del overworld");
                                lore.add(ChatColor.BLACK+".");
                                lore.add(ChatColor.DARK_RED+"Pero si estás bajo la luz del sol");
                                lore.add(ChatColor.DARK_RED+"tendrá un 3% de probabilidad de romperse por segundo");
                                meta.setLore(lore);
                                amuleto.setItemMeta(meta);
                                amuleto.setAmount(1);

                                itemRed.setAmount(0);
                                itemGlo.setAmount(0);
                                itemGla.setAmount(0);


                                player.getWorld().dropItemNaturally(loc, amuleto);
                                player.playSound(loc, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 5.0F, 1F);

                                player.getWorld().spawnParticle(Particle.FLAME, loc, 40);

                                this.cancel(); //sal del bucle
                            }

                        }
                    }


                    countdown--; 
                }
            }.runTaskTimer(instance, 0, 5); //Repeating task with 0 ticks initial delay, run once per 5 ticks. Make sure you pass a valid instance of your plugin.

            
            
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aventurasenelnether;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author asier
 */
public class EverySecond {
    
    Main instance;
    
    public EverySecond(Main instance){
        this.instance = instance;
    }
    
    public boolean day(Player player) {
        
        long time = player.getWorld().getTime();

        if(time > 0 && time < 12300) {
            return true;
        } else {
            return false;
        }
        

    }
    
    public boolean weather(Player player){
        boolean ret = false;
        if(player.getWorld().isThundering() || player.getWorld().hasStorm()){
            ret = true;
        }
        return ret;
    }
    
    
    public void start(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
			
            @Override
            public void run() {

                for(Player all : Bukkit.getServer().getOnlinePlayers()) {
                    if(!instance.getConfig().getBoolean("isMaldicionOver")){
                        
                        if(all.getWorld().getEnvironment() == Environment.NORMAL){
                            String UUID = all.getUniqueId().toString();
                            int time = instance.getConfig().getInt("players."+UUID+".time");
                            Location parLoc = all.getLocation();
                            parLoc.setY(parLoc.getY()+1);

                            if(time > 0){
                                //si tienes tiempo > 0 en el config, baja 1, el tiempo
                                instance.getConfig().set("players."+UUID+".time", (time-1));
                                instance.saveConfig();
                                all.getWorld().spawnParticle(Particle.SPELL_WITCH, parLoc, ((int)(Math.ceil((time*0.05)))), 0.5, 0.2, 0.5, 0);
                            }
                            else{
                                ItemStack reloj = all.getInventory().getItemInOffHand();
                                boolean esReloj = false;
                                if(reloj != null){
                                    if(reloj.getType().equals(Material.PRISMARINE_CRYSTALS)){
                                        if(reloj.getItemMeta() != null){
                                            if(reloj.getItemMeta().getCustomModelData() == 1){
                                                esReloj = true;
                                            }
                                        }
                                    }
                                }
                                if(esReloj){
                                    //si no, si tienes un reloj, checkea el cielo y con 1% rompelo
                                    Location loc = all.getLocation();
                                    if((day(all)) && (!weather(all))){
                                        boolean estaSalvo = false;

                                        while(loc.getY() < 319 && !estaSalvo){

                                            loc.setY(loc.getY()+1);
                                            if(!(loc.getBlock().getType().equals(Material.AIR))){
                                                estaSalvo = true;
                                            }
                                        }
                                        if(!estaSalvo){

                                            int prob = (int) (Math.random() * (100 - 0)) + 0;
                                            Location parLoc2 = all.getLocation();
                                            parLoc2.setY(parLoc2.getY()+1);
                                            if(prob <= 2){
                                                //se rompe
                                                all.getInventory().getItemInOffHand().setAmount(all.getInventory().getItemInOffHand().getAmount()-1);
                                                all.sendMessage(ChatColor.DARK_RED+"El sol ha roto tu amuleto del sol");
                                                all.getWorld().playSound(parLoc2, Sound.BLOCK_ANVIL_DESTROY, 5.0F, 1F);
                                            }
                                            else{
                                                all.getWorld().spawnParticle(Particle.FLAME, parLoc2, 10, 0.5, 0.2, 0.5, 0.3);
                                            }
                                        }
                                    }

                                }
                                else{
                                    ItemStack amuleto = all.getInventory().getItemInOffHand();
                                    boolean esAmuleto = false;
                                    if(amuleto != null){
                                        if(amuleto.getType().equals(Material.PRISMARINE_SHARD)){
                                            if(amuleto.getItemMeta() != null){
                                                if(amuleto.getItemMeta().getCustomModelData() == 1){
                                                    esAmuleto = true;
                                                }
                                            }
                                        }
                                    }
                                    if(esAmuleto){
                                        all.getInventory().getItemInOffHand().setAmount(all.getInventory().getItemInOffHand().getAmount()-1);
                                        all.sendMessage(ChatColor.AQUA+"Se ha consumido un amuleto del tiempo");
                                        all.getWorld().playSound(all.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 5.0F, 1F);
                                        all.getWorld().playSound(all.getLocation(), Sound.ITEM_TOTEM_USE, 5.0F, 1F);
                                        all.getWorld().spawnParticle(Particle.TOTEM, all.getLocation(), 50, 0.5, 0.5, 0.5, 0);

                                        instance.getConfig().set("players."+UUID+".time", 300);
                                        instance.saveConfig();
                                        //si no, si tienes amuleto, rompelo y pon tiempo a 300
                                    }
                                    else{
                                        //si no, aplica la maldición
                                        all.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
                                        all.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 1));
                                        all.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2));
                                        all.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 1));
                                    }


                                }



                            }


                        }
                        
                    }
                    
                }
            }
        }, 20L, 20L); //Delay de 1 segundo
    }
}

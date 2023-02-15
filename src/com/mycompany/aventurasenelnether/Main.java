/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aventurasenelnether;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author asier
 */
public class Main extends JavaPlugin{
    
    //VARIABLES
    public static Main instance;
    
    PluginDescriptionFile pluginFile = getDescription();
    
    String tagPluginName = ChatColor.WHITE+"["+ChatColor.GOLD+pluginFile.getName()+ChatColor.WHITE+"] ";
    
    //METODOS
    @Override
    public void onLoad() {
        taggedLog(ChatColor.DARK_RED, "Aventuras en el nether se ha cargado");
    }

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        taggedLog(ChatColor.DARK_RED, "Aventuras en el nether se ha activado");
        
        this.getCommand("aeen").setExecutor(new Comandos(this));
        this.getCommand("recetas").setExecutor(new Comandos(this));
        
        getServer().getPluginManager().registerEvents(new OnPlayerConnects(this), this);
        getServer().getPluginManager().registerEvents(new OnItemDrop(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlaced(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new OnSpawnAnchorInteract(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteractEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnEntityDeathEvent(this), this);
        
        EverySecond everySecond = new EverySecond(this);
        everySecond.start();
        
        
    }

    @Override
    public void onDisable() {
        taggedLog(ChatColor.DARK_RED, "Aventuras en el nether se ha desactivado");
    }

    public void taggedLog(ChatColor color, String mensaje){
        Bukkit.getConsoleSender().sendMessage(tagPluginName+color+mensaje);
    }
}

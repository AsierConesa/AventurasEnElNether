/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aventurasenelnether;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author asier
 */
public class Comandos implements CommandExecutor{
    
    private Main plugin;
    
    public Comandos(Main instance){
        this.plugin = instance;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("recetas")){
            Player player = (Player) sender;
            ItemStack book = Resources.generateBook();
            if(args.length == 0){
                player.sendMessage(ChatColor.GREEN+"Libro de recetas otorgado");
                player.getInventory().addItem(book);
                
            }
            else{
                player.sendMessage(ChatColor.DARK_RED+"A ver, el comando es /recetas, pero bueno, aquí tienes el libro");
                player.getInventory().addItem(book);
            }
        }
        if(label.equalsIgnoreCase("aeen")){
            if(args.length == 0){
                sender.sendMessage(ChatColor.RED+"Usa /aeen help para más información");
            }
            else if(args.length > 0){
                if(sender.hasPermission("aeen.admin")){
                    if(args.length == 1){
                        if(args[0].equalsIgnoreCase("help")){
                            sender.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"Comandos de Aventuras en el nether:");
                            sender.sendMessage(ChatColor.DARK_RED+"/aeen reload - Recarga la configuración del plugin");
                        }
                        else if(args[0].equalsIgnoreCase("reload")){
                            
                            String messageIni = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("reload.messageIni"));
                            String messageFin = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("reload.messageFin"));
                            
                            sender.sendMessage(messageIni);
                            plugin.reloadConfig();
                            sender.sendMessage(messageFin);
                        }
                        else{
                            sender.sendMessage(ChatColor.RED+"Ese comando no existe");
                        }
                    }
                    else{
                        sender.sendMessage(ChatColor.RED+"Ese comando no existe");
                    }
                }
                else{
                    sender.sendMessage(ChatColor.RED+"No tienes permiso para ejecutar ese comando");
                }
                
            }
            
        }
        
        
        return true;
    }
    
}

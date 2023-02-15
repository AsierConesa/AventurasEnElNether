/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aventurasenelnether;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

/**
 *
 * @author asier
 */
public class Resources {
    
    public static ItemStack generateBook(){
        
        
        ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
        bookMeta.setTitle(ChatColor.GOLD+"☠"+ChatColor.DARK_RED+" Aventuras en el nether "+ChatColor.GOLD+"☠");
        bookMeta.setAuthor("BigSmall_8");
        
        List<String> pages = new ArrayList<>();
        pages.add(ChatColor.GOLD+"☠"+ChatColor.DARK_RED+" Aventuras en el nether "+ChatColor.GOLD+"☠\n"
                + "\n"
                + "\n"
                + ChatColor.BLUE+"Guía de recetas\n"
                + ChatColor.AQUA+"- BigSmall_8"
        );
        pages.add(ChatColor.DARK_GREEN+" Explicación\n"
                + "\n"
                + ChatColor.DARK_BLUE+"Maldición del overworld\n"
                + ChatColor.BLUE+"La maldición del overworld se ha expandido por todo el mundo,"
                + " para destruirla has de matar al dragón, pero tu estancia en el overworld será mortal"
        );
        pages.add(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"☫ Amuleto del Tiempo ☫\n"
                + "\n"
                + ChatColor.DARK_RED+"Ingredientes: \n"
                + ChatColor.RED+"- Obsidiana llorosa\n"
                + ChatColor.RED+"- Piedra áurea negra\n"
                + ChatColor.RED+"- Ojo de ender\n"
                + "\n"
                + ChatColor.GOLD+"Mezcla estos items dentro de un caldero para obtenerlo\n"
                + "\n"
        );
        pages.add(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"☫ Amuleto del Tiempo ☫\n"
                + "\n"
                + ChatColor.DARK_AQUA + "Si lo llevas en la mano izquierda, se consumirá y tendrás 5 minutos de inmunidad a la maldición.\n"
        );
        pages.add(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"☤ Amuleto del Sol ☤\n"
                + "\n"
                + ChatColor.GOLD+"Ingredientes: \n"
                + ChatColor.RED+"- Cristal\n"
                + ChatColor.RED+"- Polvo de redstone\n"
                + ChatColor.RED+"- Polvo de piedra luminosa\n"
                + ChatColor.RED+"- 10 niveles de experiencia obtenidos\n"
                + "\n"
                + ChatColor.GOLD+"Mezcla estos items dentro de un caldero para obtenerlo\n"
                + "\n"
        );
        pages.add(ChatColor.GOLD+""+ChatColor.BOLD+"☤ Amuleto del Sol ☤\n"
                + "\n"
                + ChatColor.DARK_AQUA+"Si lo llevas en la mano izquierda, serás inmune a la maldición del overworld, pero tiene un 3% de probabilidad de romperse por segundo si, te da la luz directa del sol"
        );
        pages.add(
                ChatColor.RED+""+ChatColor.BOLD+"Puedes volver a obtener este libro siempre que quieras, usando /recetas"
        );
                
        
        bookMeta.setPages(pages);
        writtenBook.setItemMeta(bookMeta);
        
        return writtenBook;
    }
    
    private static boolean chkRelativeBlock(Block block, BlockFace face, int distance){
     
        Block relativeBlock = block.getRelative(face, distance);
     
        Block footBlock = relativeBlock.getRelative(BlockFace.UP);
        Block headBlock = relativeBlock.getRelative(BlockFace.UP, 2);
     
        if(!relativeBlock.isLiquid() && !relativeBlock.isEmpty()){
            if(footBlock.isEmpty() && headBlock.isEmpty()){
                return true;
            }
        }
     
        return false;
    }
    
    public static Location getProperLocationNether(World world, double x, double y, double z){
     
        boolean safe = false;
        int y2 = (int)y;
        int x2 = (int)x;
        int z2 = (int)z;
        Location location = null;
        Block centerBlock = null;
        while(!safe){
            loop: while(y2 <= 80){
                centerBlock = world.getBlockAt(x2, y2, z2);
                if(chkRelativeBlock(centerBlock, BlockFace.SELF, 0)){
                    location = new Location(world, (double)x2, (double)y2+2d, (double)z2);
                    safe = true;
                    break loop;
                }

                y2++;
            }
            if(!safe){
                x2=+10;
                z2=+10;
                y2 = (int)y;
            }
        }
            
        return location;
    }
    
}

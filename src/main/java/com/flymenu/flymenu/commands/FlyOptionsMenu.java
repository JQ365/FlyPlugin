package com.flymenu.flymenu.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.flymenu.flymenu.Plugin;


public class FlyOptionsMenu implements CommandExecutor {

    private Plugin plugin;

    public FlyOptionsMenu(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if(sender instanceof Player){
            Player p = (Player)sender;
            String prefix = plugin.getConfig().getString("prefix");
            if (p.hasPermission("flymenu.options")) {
                if(p.getAllowFlight()){
                    int i;
                    String guioptionstitle = plugin.getConfig().getString("flyoptions-title-gui");
                    if (guioptionstitle != null) {
                        Integer sizegui = plugin.getConfig().getInt("size-flyoptions");
                        Inventory flyoptionsmenu = Bukkit.createInventory(p, sizegui, ChatColor.translateAlternateColorCodes('&', guioptionstitle));
                        
                        //Buttons
                        ItemStack speed1 = new ItemStack(Material.COPPER_INGOT);
                        ItemStack speed2 = new ItemStack(Material.IRON_INGOT);
                        ItemStack speed3 = new ItemStack(Material.GOLD_INGOT);

                        //Metadata speed1
                        ItemMeta speed1meta = speed1.getItemMeta();
                        String titlespeed1 = plugin.getConfig().getString("title-speed1");

                        if(titlespeed1 != null){
                            speed1meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', titlespeed1));
                        }else{
                            speed1meta.setDisplayName("Speed 1");
                        }

                        List<String> speed1Lore = new ArrayList<>();
                        List<String> lorespeed1 = plugin.getConfig().getStringList("lore-speed1");
                        for(i= 0; i< lorespeed1.size(); i++){
                            speed1Lore.add(ChatColor.translateAlternateColorCodes('&', lorespeed1.get(i)));
                        }

                        speed1meta.setLore(speed1Lore);
                        speed1.setItemMeta(speed1meta);

                        //Metadata speed2
                        ItemMeta speed2meta = speed2.getItemMeta();
                        String titlespeed2 = plugin.getConfig().getString("title-speed2");

                        if(titlespeed2 != null){
                            speed2meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', titlespeed2));
                        }else{
                            speed2meta.setDisplayName("Speed 2");
                        }

                        List<String> speed2Lore = new ArrayList<>();
                        List<String> lorespeed2 = plugin.getConfig().getStringList("lore-speed2");
                        for(i= 0; i< lorespeed2.size(); i++){
                            speed2Lore.add(ChatColor.translateAlternateColorCodes('&', lorespeed2.get(i)));
                        }

                        speed2meta.setLore(speed2Lore);
                        speed2.setItemMeta(speed2meta);

                        //Metadata speed3
                        ItemMeta speed3meta = speed3.getItemMeta();
                        String titlespeed3 = plugin.getConfig().getString("title-speed3");
                        
                        if(titlespeed3 != null){
                            speed3meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', titlespeed3));
                        }else{
                            speed3meta.setDisplayName("Speed 3");
                        }

                        List<String> speed3Lore = new ArrayList<>();
                        List<String> lorespeed3 = plugin.getConfig().getStringList("lore-speed3");

                        for(i= 0; i< lorespeed3.size(); i++){
                            speed3Lore.add(ChatColor.translateAlternateColorCodes('&', lorespeed3.get(i)));
                        }

                        speed3meta.setLore(speed3Lore);
                        speed3.setItemMeta(speed3meta);
                        
                        Integer speed1index = plugin.getConfig().getInt("speed1-item-index");
                        Integer speed2index = plugin.getConfig().getInt("speed2-item-index");
                        Integer speed3index = plugin.getConfig().getInt("speed3-item-index");

                        flyoptionsmenu.setItem(speed1index, speed1);
                        flyoptionsmenu.setItem(speed2index, speed2);
                        flyoptionsmenu.setItem(speed3index, speed3);

                        p.openInventory(flyoptionsmenu);

                    } else {
                        System.out.println("Fly Plugin - The Title of the fly options menu is null");
                    }
                }else{
                    String isflying = plugin.getConfig().getString("is-flying");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + isflying));
                }
            }else{
                
                String perm_msg = plugin.getConfig().getString("perm-msg-options");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + perm_msg));
            }
        }
        return true;
    }
}

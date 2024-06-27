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


public class FlyMenu implements CommandExecutor {

    private Plugin plugin;

    public FlyMenu(Plugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;

            if (p.hasPermission("flymenu.open")) {
                String guititle = plugin.getConfig().getString("flytitle-gui");
                Integer size = plugin.getConfig().getInt("size");
                int i;
                if (guititle != null) {
                    Inventory flymenu = Bukkit.createInventory(p, size, ChatColor.translateAlternateColorCodes('&', guititle));
                    
                    //Buttons
                    ItemStack flyitem = new ItemStack(Material.FEATHER);
                    ItemStack flyoptions = new ItemStack(Material.CLOCK);

                    //Metadata flyitem
                    ItemMeta flymeta = flyitem.getItemMeta();
                    String nameflymode = plugin.getConfig().getString("name-flymode");
                    if(nameflymode != null){
                        flymeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', nameflymode));
                    }else{
                        flymeta.setDisplayName("Fly Mode");
                    }
                    List<String> flyLore = new ArrayList<>();

                    List<String> lorefly = plugin.getConfig().getStringList("lore-flyitem");
                    for(i= 0; i< lorefly.size(); i++){
                        flyLore.add(ChatColor.translateAlternateColorCodes('&', lorefly.get(i)));
                    }
                    flymeta.setLore(flyLore);
                    flyitem.setItemMeta(flymeta);

                    //Metadata flyoptions
                    ItemMeta flymetaoptions = flyoptions.getItemMeta();
                    String nameflyoptions = plugin.getConfig().getString("name-flyoptions");
                    if (nameflyoptions != null) {
                        flymetaoptions.setDisplayName(ChatColor.translateAlternateColorCodes('&', nameflyoptions));
                    }else{
                        flymetaoptions.setDisplayName("Fly Options");
                    }

                    List<String> loreoptions = plugin.getConfig().getStringList("lore-flyoptions-item");
                    List<String> flyOptionsLore = new ArrayList<>();
                    for(i= 0; i< loreoptions.size(); i++){
                        flyOptionsLore.add(ChatColor.translateAlternateColorCodes('&', loreoptions.get(i)));
                    }

                    flymetaoptions.setLore(flyOptionsLore);
                    flyoptions.setItemMeta(flymetaoptions);

                    flymenu.setItem(plugin.getConfig().getInt("flymode-item-index"), flyitem);
                    flymenu.setItem(plugin.getConfig().getInt("flyoptions-item-index"), flyoptions);

                    p.openInventory(flymenu);

                } else {
                    System.out.println("Fly Plugin - There has to be a flymenu title");
                }
            }else{
                String prefix = plugin.getConfig().getString("prefix");
                String perm_msg = plugin.getConfig().getString("permission-message");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + perm_msg));
            }

        }

        return true;
    }
}

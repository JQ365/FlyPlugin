package com.flymenu.flymenu.Listeners;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.flymenu.flymenu.Plugin;


public class FlyInventoryClick implements Listener {

    private Plugin plugin;

    public FlyInventoryClick(Plugin plugin) {
        this.plugin = plugin;
    }

    private ArrayList<Player> fly_list_players = new ArrayList<>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        String flygui = plugin.getConfig().getString("flytitle-gui");
        String prefix = plugin.getConfig().getString("prefix");
        if (flygui != null) {
            if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', flygui))) {
                e.setCancelled(true);

                if(e.getCurrentItem() != null){
                    String off_message = plugin.getConfig().getString("off-message");
                    String on_message = plugin.getConfig().getString("on-message");
                    switch(e.getCurrentItem().getType()) {
                        case FEATHER:
                            if(fly_list_players.contains(p)){
                                p.setAllowFlight(false);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + off_message));
                                fly_list_players.remove(p);
                                break;
                            }else if(!fly_list_players.contains(p)){
                                p.setAllowFlight(true);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + on_message));
                                fly_list_players.add(p);
                                break;
                            }
                        case CLOCK:
                            p.performCommand("flyoptions");
                            break;
                        default:
                            break;  
                    }
                }

            }
        }else{
            System.out.println("Fly Plugin - There has to be a gui title");
        }
        String flyoptionsgui = plugin.getConfig().getString("flyoptions-title-gui");
        if(flyoptionsgui != null){
            if(e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', flyoptionsgui))){
                e.setCancelled(true);
                if(e.getCurrentItem() != null){
                    String msgspeed_1 = plugin.getConfig().getString("message-speed1");
                    String msgspeed_2 = plugin.getConfig().getString("message-speed2");
                    String msgspeed_3 = plugin.getConfig().getString("message-speed3");
                    switch(e.getCurrentItem().getType()){
                        case COPPER_INGOT:
                            p.setFlySpeed(0.05f);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + msgspeed_1));
                            break;
                        case IRON_INGOT:
                            p.setFlySpeed(0.1f);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + msgspeed_2));
                            break;
                        case GOLD_INGOT:
                            p.setFlySpeed(0.25f);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + msgspeed_3));
                            break;
                        default:
                            break;
                    }
                }

            }
        }else{
            System.out.println("Fly Plugin - There has to be a options title");
        }
    }
}

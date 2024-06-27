package com.flymenu.flymenu;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.flymenu.flymenu.Listeners.FlyInventoryClick;
import com.flymenu.flymenu.commands.FlyMenu;
import com.flymenu.flymenu.commands.FlyOptionsMenu;

/*
 * flymenu java plugin
 */
public class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("flymenu");

  @Override
  public void onEnable()
  {
    getCommand("flymenu").setExecutor(new FlyMenu(this));
    getCommand("flyoptions").setExecutor(new FlyOptionsMenu(this));
    getServer().getPluginManager().registerEvents(new FlyInventoryClick(this), this);

    getConfig().options().copyDefaults();
    saveDefaultConfig();
  }

}

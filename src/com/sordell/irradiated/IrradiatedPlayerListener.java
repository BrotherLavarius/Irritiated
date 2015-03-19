package com.sordell.irradiated;

import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class IrradiatedPlayerListener
  implements Listener
{
  private Irradiated plugin;
  
  public IrradiatedPlayerListener(Irradiated instance)
  {
    this.plugin = instance;
  }
  
  @EventHandler
  public void onRightClick(PlayerInteractEvent event)
  {
    Player player = event.getPlayer();
    ItemStack item = event.getItem();
    if ((item != null) && (item.getTypeId() == this.plugin.getConfig().getInt("GeigerID")) && (player.hasPermission("irradiated.geiger"))) {
      this.plugin.doGeiger(player);
    }
  }
  
  @EventHandler
  public void onDeath(PlayerDeathEvent event)
  {
    Player player = event.getEntity();
    this.plugin.playerrads.put(player.getName(), "0");
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event)
  {
    final Player player = event.getPlayer();
    this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
    {
      public void run()
      {
        player.sendMessage("[" + ChatColor.RED + "STT" + ChatColor.WHITE + "] " + ChatColor.WHITE + "Radiation monitoring:" + ChatColor.GREEN + "Online");
      }
    }, 100L);
  }
  
  @EventHandler
  public void onEat(FoodLevelChangeEvent event)
  {
    Player player = (Player)event.getEntity();
    ItemStack food = event.getEntity().getItemInHand();
    if (food.getTypeId() == this.plugin.getConfig().getInt("RadX"))
    {
      String getrads = (String)this.plugin.playerrads.get(player.getName());
      double myrads = 0.0D;
      if (getrads != null) {
        myrads = Double.parseDouble(getrads);
      }
      myrads -= 100.0D;
      this.plugin.playerrads.put(player.getName(), String.valueOf(myrads));
    }
  }
}

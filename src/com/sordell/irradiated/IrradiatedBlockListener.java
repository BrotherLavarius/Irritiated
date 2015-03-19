package com.sordell.irradiated;

import java.util.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;

public class IrradiatedBlockListener
  implements Listener
{
  public Irradiated plugin;
  
  public IrradiatedBlockListener(Irradiated instance)
  {
    this.plugin = instance;
  }
  
  @EventHandler
  public void onBlockSpread(BlockSpreadEvent event)
  {
    String source = event.getSource().getType().name();
    if (source == "GRASS") {
      event.setCancelled(true);
    }
  }
  
  @EventHandler
  public void onBlockBreak(BlockBreakEvent event)
  {
    Player p = event.getPlayer();
    World w = p.getWorld();
    int px = p.getLocation().getBlockX();
    int py = p.getLocation().getBlockY();
    int pz = p.getLocation().getBlockZ();
    String blockref = w.getName() + "-" + px + "-" + py + "-" + pz;
    this.plugin.blockmap.put(blockref, null);
  }
  
  @EventHandler
  public void onBlockPlace(BlockPlaceEvent event)
  {
    Player p = event.getPlayer();
    World w = p.getWorld();
    int px = p.getLocation().getBlockX();
    int py = p.getLocation().getBlockY();
    int pz = p.getLocation().getBlockZ();
    String blockref = w.getName() + "-" + px + "-" + py + "-" + pz;
    this.plugin.blockmap.put(blockref, null);
  }
}


 
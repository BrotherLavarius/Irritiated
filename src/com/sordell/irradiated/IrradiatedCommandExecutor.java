package com.sordell.irradiated;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class IrradiatedCommandExecutor
  implements CommandExecutor
{
  private Irradiated plugin;
  
  public IrradiatedCommandExecutor(Irradiated plugin)
  {
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    Player player = null;
    if ((sender instanceof Player)) {
      player = (Player)sender;
    }
    if (cmd.getName().equalsIgnoreCase("irradiated"))
    {
      String version = this.plugin.getDescription().getVersion();
      sender.sendMessage("[" + ChatColor.RED + "Irradiated" + ChatColor.WHITE + "] Version " + version + " active.");
      return true;
    }
    if (cmd.getName().equalsIgnoreCase("rads"))
    {
      sender.sendMessage("[" + ChatColor.RED + "Irradiated" + ChatColor.WHITE + "]");
      return true;
    }
    if ((cmd.getName().equalsIgnoreCase("geiger")) && (player.hasPermission("irradiated.geiger")))
    {
      if (player == null)
      {
        sender.sendMessage("You can only run this command as a player!");
      }
      else
      {
        World w = player.getWorld();
        if (ArrayUtils.contains(this.plugin.IrradiatedWorlds, w.getName())) {
          this.plugin.doGeiger(player);
        } else {
          sender.sendMessage("Irradiated has not been enabled for world " + w.getName());
        }
      }
      return true;
    }
    return false;
  }
}

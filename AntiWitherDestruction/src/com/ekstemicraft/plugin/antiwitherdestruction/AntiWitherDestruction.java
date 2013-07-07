package com.ekstemicraft.plugin.antiwitherdestruction;

import java.util.List;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiWitherDestruction extends JavaPlugin implements Listener
{
  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(this, this);
  }
  @EventHandler
  public void onExplosionWither(EntityExplodeEvent event1) { if (event1.isCancelled()) return;
    EntityType type = event1.getEntity().getType();
    if (type == EntityType.WITHER) {
      @SuppressWarnings("rawtypes")
	List blocks = event1.blockList();
      event1.blockList().removeAll(blocks);
    }
  }

  @EventHandler
  public void WitherProjectile(EntityExplodeEvent event)
  {
    if (event.isCancelled()) return;
    Entity entity = event.getEntity();

    if ((entity instanceof WitherSkull)) {
      World world = event.getLocation().getWorld();
      Location location = event.getLocation();

      event.setCancelled(true);
      world.playEffect(location, Effect.SMOKE, 50);
    }
  }
  @EventHandler
  public void WitherEatBlocks(EntityChangeBlockEvent event) { EntityType type = event.getEntity().getType();
    if (type == EntityType.WITHER)
      event.setCancelled(true);
  }
}
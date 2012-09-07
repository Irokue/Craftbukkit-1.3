package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;

public class BlockRedstoneBlock extends Block
{
  private boolean a;

  public BlockRedstoneBlock(int i, int j, boolean flag)
  {
    super(i, j, Material.ORE);
    if (flag) {
      b(true);
    }

    this.a = flag;
  }

  public int b() {
    return 30;
  }

  public int getDropType(int par1, Random par2Random, int par3)
  {
    return Block.REDSTONE_BLOCK.id;
  }
  public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
    g(world, i, j, k);
    super.attack(world, i, j, k, entityhuman);
  }

  public void b(World world, int i, int j, int k, Entity entity)
  {
    if ((entity instanceof EntityHuman)) {
      PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, i, j, k, -1, null);
      if (!event.isCancelled()) {
        g(world, i, j, k);
        super.b(world, i, j, k, entity);
      }
    } else {
      EntityInteractEvent event = new EntityInteractEvent(entity.getBukkitEntity(), world.getWorld().getBlockAt(i, j, k));
      world.getServer().getPluginManager().callEvent(event);
      if (!event.isCancelled()) {
        g(world, i, j, k);
        super.b(world, i, j, k, entity);
      }
    }
  }

  public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2)
  {
    g(world, i, j, k);
    return super.interact(world, i, j, k, entityhuman, l, f, f1, f2);
  }

  private void g(World world, int i, int j, int k) {
    h(world, i, j, k);
    if (this.id == Block.REDSTONE_BLOCK.id)
      world.setTypeId(i, j, k, Block.GLOWING_REDSTONE_BLOCK.id);
  }

  public void b(World world, int i, int j, int k, Random random)
  {
    if (this.id == Block.GLOWING_REDSTONE_BLOCK.id)
      world.setTypeId(i, j, k, Block.REDSTONE_BLOCK.id);
  }

  private void h(World world, int i, int j, int k)
  {
    Random random = world.random;
    double d0 = 0.0625D;

    for (int l = 0; l < 6; l++) {
      double d1 = i + random.nextFloat();
      double d2 = j + random.nextFloat();
      double d3 = k + random.nextFloat();

      if ((l == 0) && (!world.r(i, j + 1, k))) {
        d2 = j + 1 + d0;
      }

      if ((l == 1) && (!world.r(i, j - 1, k))) {
        d2 = j + 0 - d0;
      }

      if ((l == 2) && (!world.r(i, j, k + 1))) {
        d3 = k + 1 + d0;
      }

      if ((l == 3) && (!world.r(i, j, k - 1))) {
        d3 = k + 0 - d0;
      }

      if ((l == 4) && (!world.r(i + 1, j, k))) {
        d1 = i + 1 + d0;
      }

      if ((l == 5) && (!world.r(i - 1, j, k))) {
        d1 = i + 0 - d0;
      }

      if ((d1 < i) || (d1 > i + 1) || (d2 < 0.0D) || (d2 > j + 1) || (d3 < k) || (d3 > k + 1))
        world.a("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
    }
  }
}
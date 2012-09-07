package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class BlockMountable extends Block
{
  public BlockMountable(int i, Material material)
  {
    super(i, material);
  }

  public BlockMountable(int i, int j, Material material)
  {
    super(i, j, material);
  }

  public boolean b(World world, int i, int j, int k, EntityPlayer entityplayer)
  {
    return b(world, i, j, k, entityplayer, 0.5F, 0.5F, 0.5F, 0, 0, 0, 0);
  }

  public static boolean b(World world, int i, int j, int k, EntityPlayer entityplayer, float f)
  {
    return b(world, i, j, k, entityplayer, 0.5F, f, 0.5F, 0, 0, 0, 0);
  }

  public static boolean b(World world, int i, int j, int k, EntityPlayer entityplayer, float f, float f1, float f2, int l, int i1, int j1, int k1)
  {
	  return false;
  }
}
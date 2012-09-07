package net.minecraft.server;

import java.util.ArrayList;

public class BlockChaise extends BlockMountable
{
  public BlockChaise(int i, int j)
  {
    super(i, j, Material.WOOD);
  }

  protected BlockChaise(int i, int j, Material material) {
    super(i, j, material);
  }

  public boolean d() {
    return false;
  }

  public int b() {
    return 100;
  }

  public boolean c() {
    return false;
  }

  public void postPlace(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
  {
    int i = MathHelper.floor(par5EntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3;
    int j = par1World.getData(par2, par3, par4) & 0x4;

    if (i == 0)
    {
      par1World.setData(par2, par3, par4, 0x2 | j);
    }

    if (i == 1)
    {
      par1World.setData(par2, par3, par4, 0x1 | j);
    }

    if (i == 2)
    {
      par1World.setData(par2, par3, par4, 0x3 | j);
    }

    if (i == 3)
    {
      par1World.setData(par2, par3, par4, 0x0 | j);
    }
  }

  public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {
    int l = world.getData(i, j, k);
    int i1 = l & 0x3;
    float f = 0.0F;
    float f1 = 0.5F;
    float f2 = 0.5F;
    float f3 = 1.0F;

    if ((l & 0x4) != 0) {
      f = 0.5F;
      f1 = 1.0F;
      f2 = 0.0F;
      f3 = 0.5F;
    }

    a(0.2F, 0.0F, 0.2F, 0.8F, 0.5F, 0.8F);
    super.a(world, i, j, k, axisalignedbb, arraylist, null);

    if (i1 == 0)
    {
      a(0.7F, 0.5F, 0.2F, 0.8F, 1.0F, 0.8F);
      super.a(world, i, j, k, axisalignedbb, arraylist, null);
    }
    else if (i1 == 1)
    {
      a(0.2F, 0.5F, 0.2F, 0.3F, 1.0F, 0.8F);
      super.a(world, i, j, k, axisalignedbb, arraylist, null);
    }
    else if (i1 == 2)
    {
      a(0.2F, 0.5F, 0.7F, 0.8F, 1.0F, 0.8F);
      super.a(world, i, j, k, axisalignedbb, arraylist, null);
    }
    else if (i1 == 3)
    {
      a(0.2F, 0.5F, 0.2F, 0.8F, 1.0F, 0.3F);
      super.a(world, i, j, k, axisalignedbb, arraylist, null);
    }

    a(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
  }
}
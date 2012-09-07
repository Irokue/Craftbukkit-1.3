package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.event.CraftEventFactory;

public class BlockGrappeCrops extends BlockFlower
{
  protected BlockGrappeCrops(int par1, int par2)
  {
    super(par1, par2);
    this.textureId = par2;
    b(true);
    float f = 0.5F;
    a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
  }

  protected boolean d_(int par1)
  {
    return par1 == Block.SOIL.id;
  }

  public void b(World par1World, int par2, int par3, int par4, Random par5Random)
  {
    super.b(par1World, par2, par3, par4, par5Random);

    if (par1World.getLightLevel(par2, par3 + 1, par4) >= 9)
    {
      int l = par1World.getData(par2, par3, par4);
      if (l < 2)
      {
        float f = i(par1World, par2, par3, par4);
        if (par5Random.nextInt((int)(25.0F / f) + 1) == 0)
        {
          l++; CraftEventFactory.handleBlockGrowEvent(par1World, par2, par3, par4, this.id, l);
        }
      }
    }
  }

  public void g(World par1World, int par2, int par3, int par4)
  {
    par1World.setData(par2, par3, par4, 2);
  }

  private float i(World par1World, int par2, int par3, int par4)
  {
    float f = 1.0F;
    int i = par1World.getTypeId(par2, par3, par4 - 1);
    int j = par1World.getTypeId(par2, par3, par4 + 1);
    int k = par1World.getTypeId(par2 - 1, par3, par4);
    int l = par1World.getTypeId(par2 + 1, par3, par4);
    int i1 = par1World.getTypeId(par2 - 1, par3, par4 - 1);
    int j1 = par1World.getTypeId(par2 + 1, par3, par4 - 1);
    int k1 = par1World.getTypeId(par2 + 1, par3, par4 + 1);
    int l1 = par1World.getTypeId(par2 - 1, par3, par4 + 1);
    boolean flag = (k == this.id) || (l == this.id);
    boolean flag1 = (i == this.id) || (j == this.id);
    boolean flag2 = (i1 == this.id) || (j1 == this.id) || (k1 == this.id) || (l1 == this.id);

    for (int i2 = par2 - 1; i2 <= par2 + 1; i2++)
    {
      for (int j2 = par4 - 1; j2 <= par4 + 1; j2++)
      {
        int k2 = par1World.getTypeId(i2, par3 - 1, j2);
        float f1 = 0.0F;

        if (k2 == Block.SOIL.id)
        {
          f1 = 1.0F;

          if (par1World.getData(i2, par3 - 1, j2) > 0)
          {
            f1 = 3.0F;
          }
        }

        if ((i2 != par2) || (j2 != par4))
        {
          f1 /= 4.0F;
        }

        f += f1;
      }
    }

    if ((flag2) || ((flag) && (flag1)))
    {
      f /= 2.0F;
    }

    return f;
  }

  public int a(int par1, int par2)
  {
    if (par2 < 0)
    {
      par2 = 2;
    }

    return this.textureId + par2;
  }

  public int b()
  {
    return 32;
  }

  public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1)
  {
    super.dropNaturally(world, i, j, k, l, f, 0);

    if (!world.isStatic) {
      int j1 = 1 + i1;

      for (int k1 = 0; k1 < j1; k1++) {
        int n = world.getData(i, j, k);
        if ((world.random.nextInt(15) <= l) && (n < 2)) {
          float f1 = 0.7F;
          float f2 = world.random.nextFloat() * f1 + (1.0F - f1) * 0.5F;
          float f3 = world.random.nextFloat() * f1 + (1.0F - f1) * 0.5F;
          float f4 = world.random.nextFloat() * f1 + (1.0F - f1) * 0.5F;
          EntityItem entityitem = new EntityItem(world, i + f2, j + f3, k + f4, new ItemStack(Item.RAISIN_SEED));

          entityitem.pickupDelay = 10;
          world.addEntity(entityitem);
        } else {
          if (n != 2)
            continue;
          float f1 = 0.7F;
          float f2 = world.random.nextFloat() * f + (1.0F - f) * 0.5F;
          float f3 = world.random.nextFloat() * f + (1.0F - f) * 0.5F;
          float f4 = world.random.nextFloat() * f + (1.0F - f) * 0.5F;
          EntityItem entityitem = new EntityItem(world, i + f2, j + f3, k + f4, new ItemStack(Item.RAISIN_SEED));
          entityitem.pickupDelay = 10;
          world.addEntity(entityitem);
        }
      }
    }
  }

  public int getDropType(int par1, Random par2Random, int par3)
  {
    if (par1 == 2)
    {
      return Item.RAISIN.id;
    }

    return -1;
  }

  public int a(Random par1Random)
  {
    return 2;
  }
}
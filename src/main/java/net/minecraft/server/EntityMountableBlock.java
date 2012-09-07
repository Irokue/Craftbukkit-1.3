package net.minecraft.server;

import java.io.PrintStream;

public class EntityMountableBlock extends Entity
{
  protected int orgBlockPosX;
  protected int orgBlockPosY;
  protected int orgBlockPosZ;
  protected int orgBlockID;

  public EntityMountableBlock(World world, double d, double d1, double d2)
  {
    super(world);
    this.X = true;
    this.m = true;
    this.width = 0.0F;
    this.height = 0.0F;
    setPosition(d, d1, d2);
  }

  public EntityMountableBlock(World world, EntityPlayer entityplayer, int i, int j, int k, float f, float f1, float f2)
  {
    super(world);
    this.X = true;
    this.m = true;
    this.width = 0.0F;
    this.height = 0.0F;
    this.orgBlockPosX = i;
    this.orgBlockPosY = j;
    this.orgBlockPosZ = k;
    this.orgBlockID = world.getTypeId(i, j, k);
    setPosition(f, f1, f2);
  }

  public boolean c(EntityHuman entityplayer)
  {
    System.out.println("Be4 Mounting !");
    if (!super.c(entityplayer))
    {
      if ((this.passenger == null) || (this.passenger == entityplayer))
      {
        entityplayer.mount(this);
        System.out.println("Mounting !");
        return true;
      }

      return false;
    }

    return true;
  }

  public void F_()
  {
    aA();
  }

  public void aA()
  {
    if ((this.passenger == null) || (this.passenger.dead))
    {
      die();
    }
    else if (this.world.getTypeId(this.orgBlockPosX, this.orgBlockPosY, this.orgBlockPosZ) != this.orgBlockID)
    {
      c((EntityPlayer)this.passenger);
    }

    this.ticksLived += 1;
  }

  public void a(NBTTagCompound nbttagcompound)
  {
  }

  public void b(NBTTagCompound nbttagcompound)
  {
  }

  protected void a()
  {
  }

}
package net.minecraft.server;

public class ItemRaisin extends ItemFood
{
  public ItemRaisin(int par1, int par2, boolean par3)
  {
    super(par1, par2, par3);
  }

  public ItemStack b(ItemStack par1ItemStack, World par2World, EntityHuman par3EntityPlayer)
  {
    super.b(par1ItemStack, par2World, par3EntityPlayer);
    par3EntityPlayer.inventory.pickup(new ItemStack(Item.RAISIN_SEED));
    return par1ItemStack;
  }
}
package net.minecraft.server;

public class ItemVin extends ItemFood
{
  public ItemVin(int i)
  {
    super(i, 4, false);
    this.maxStackSize = 1;
  }

  public ItemStack b(ItemStack par1ItemStack, World par2World, EntityHuman par3EntityPlayer)
  {
    super.b(par1ItemStack, par2World, par3EntityPlayer);
    return new ItemStack(Item.VIN_VIDE);
  }
}
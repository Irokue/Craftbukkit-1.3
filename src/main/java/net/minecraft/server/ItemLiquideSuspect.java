package net.minecraft.server;


public class ItemLiquideSuspect extends ItemFood {

	protected ItemLiquideSuspect(int par1) {
		super(par1, 4, false);
		maxStackSize = 1;
	}
	
	public ItemStack b(ItemStack par1ItemStack, World par2World, EntityHuman par3EntityPlayer)
    {
        super.b(par1ItemStack, par2World, par3EntityPlayer);
        return new ItemStack(Item.GLASS_BOTTLE);
    }

}
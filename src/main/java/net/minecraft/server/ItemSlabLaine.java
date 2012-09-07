package net.minecraft.server;


public class ItemSlabLaine extends ItemBlock
{
    public ItemSlabLaine(int par1)
    {
        super(par1);
        setMaxDurability(0);
        a(true);
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public int filterData(int par1)
    {
        return par1;
    }


    public String c(ItemStack par1ItemStack)
    {
        return (new StringBuilder()).append(super.getName()).toString();
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS !
     */
    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        if (itemstack.count == 0) {
            return false;
        } else if (!entityhuman.e(i, j, k)) {
            return false;
        } else {
            int i1 = world.getTypeId(i, j, k);
            int j1 = world.getData(i, j, k);
            int k1 = j1 & 7;
            boolean flag = (j1 & 8) != 0;

            if ((l == 1 && !flag || l == 0 && flag) && i1 == Block.DEMI_DALLE_LAINE.id && k1 == itemstack.getData()) {
                return super.interactWith(itemstack, entityhuman, world, i, j, k, -1, f, f1, f2); // CraftBukkit - handle this in super
            } else {
                return this.a(itemstack, entityhuman, world, i, j, k, l) ? true : super.interactWith(itemstack, entityhuman, world, i, j, k, l, f, f1, f2);
            }
        }
    }

    private boolean a(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l) {
    {
	        if (l == 0) {
	            --j;
	        }
	
	        if (l == 1) {
	            ++j;
	        }
	
	        if (l == 2) {
	            --k;
	        }
	
	        if (l == 3) {
	            ++k;
	        }
	
	        if (l == 4) {
	            --i;
	        }
	
	        if (l == 5) {
	            ++i;
	        }
	
	        int i1 = world.getTypeId(i, j, k);
	        int j1 = world.getData(i, j, k);
	        int k1 = j1 & 7;
	
	        if (i1 == Block.DEMI_DALLE_LAINE.id && k1 == itemstack.getData()) {
	            if (world.b(Block.DEMI_DALLE_LAINE_ENVERS.e(world, i, j, k)) && world.setTypeIdAndData(i, j, k, Block.DEMI_DALLE_LAINE_ENVERS.id, k1)) {
	                world.makeSound((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), Block.DEMI_DALLE_LAINE_ENVERS.stepSound.getName(), (Block.DEMI_DALLE_LAINE_ENVERS.stepSound.getVolume1() + 1.0F) / 2.0F, Block.DEMI_DALLE_LAINE_ENVERS.stepSound.getVolume2() * 0.8F);
	                --itemstack.count;
	            }
	
	            return true;
	        } else {
	            return false;
	        }
    	}
    }
}

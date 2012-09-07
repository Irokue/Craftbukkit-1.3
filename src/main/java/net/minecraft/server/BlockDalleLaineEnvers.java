package net.minecraft.server;

import java.util.Random;

public class BlockDalleLaineEnvers extends Block {

    public static final String[] a = new String[] { "stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick"};

    private boolean blockType;
    
    public BlockDalleLaineEnvers(int i, boolean flag) {
    	super(i, 64, Material.CLOTH);
        blockType = flag;
        if(!flag)
        this.a(CreativeModeTab.b);
        h(255);
    }

    public boolean c()
    {
    	return blockType;
    }
    
    public boolean d()
    {
    	return blockType;
    }

    
//    public void postPlace(World world, int i, int j, int k, int l, float f, float f1, float f2) {
//    	
//    	if(l == 0 && !blockType)
//    	{
//    		int i1 = world.getData(i, j, k);
//    		world.set(i, j, k, Block.FOIN.id , i1);
//    	}
//    }
    
    protected int getDropData(int i)
	  {
		  return i;
	  }
    
    public int a(int i, int j) {
        int k = j & 7;

        return k == 0 ? (i <= 1 ? 6 : 5) : (k == 1 ? (i == 0 ? 208 : (i == 1 ? 176 : 192)) : (k == 2 ? 4 : (k == 3 ? 16 : (k == 4 ? Block.BRICK.textureId : (k == 5 ? Block.SMOOTH_BRICK.textureId : 6)))));
    }

    public int a(int i) {
        return this.a(i, 0);
    }
    
    public int a(Random random) {
        return this.blockType ? 2 : 1;
    }
    
    public int getDropType(int i, Random random, int j) {
        return Block.DEMI_DALLE_LAINE.id;
    }

    protected ItemStack c_(int i) {
        return new ItemStack(Block.DEMI_DALLE_LAINE.id, 2, i & 7);
    }
}
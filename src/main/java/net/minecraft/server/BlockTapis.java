package net.minecraft.server;

public class BlockTapis extends Block {

	protected BlockTapis(int i, int j) {
		super(i, j, Material.CLOTH);
		this.a(CreativeModeTab.c);
	}

	  public AxisAlignedBB e(World world, int i, int j, int k) {
		  return null;
	  }

	  public int a(int par1, int par2)
	    {
	        if (par2 == 0)
	        {
	            return textureId;
	        }
	        else
	        {
	            par2 = ~(par2 & 0xf);
	            return 113 + ((par2 & 8) >> 3) + (par2 & 7) * 16;
	        }
	    }
	  
	  protected int getDropData(int i)
	  {
		  return i;
	  }
	  
	  public boolean c() {
		    return false;
		  }
	  public boolean d() {
		    return false;
		  }
}

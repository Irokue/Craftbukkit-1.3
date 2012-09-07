package net.minecraft.server;

import java.util.Random;

public class BlockNuage extends BlockHalfTransparant {

	protected BlockNuage(int arg0, int arg1, Material arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
        this.a(CreativeModeTab.b);
	}
	  public boolean c() {
		    return false;
		  }
	 public int a(Random random) {
	        return 0;
	    }
	  public AxisAlignedBB e(World world, int i, int j, int k) {
		  return null;
	  }
	 
	  public void a(World world, int i, int j, int k, Entity entity) {
	        entity.motX *= 0.4D;
	        entity.motY *= 0.4D;
	        entity.motZ *= 0.4D;
	    }
	 
	   public int e() {
	        return 0;
	    }
	   
	   protected boolean q_() {
	        return this.c() && !this.isTileEntity;
	    }
	
}

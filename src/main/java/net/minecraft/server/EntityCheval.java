package net.minecraft.server;


public class EntityCheval extends EntityAnimal {

	float f = 1.1F;
	public EntityCheval(World arg0) {
		super(arg0);
        texture = "/mob/Cheval.png";
        a(1.4F, 1.4F);
        getNavigation().a(true);
        /*goalSelector.a(0, new PathfinderGoalFloat(this));
        goalSelector.a(1, new PathfinderGoalPanic(this, 0.5f));
        goalSelector.a(2, new PathfinderGoalBreed(this, 0.5f));
        goalSelector.a(3, new PathfinderGoalTempt(this,0.5f, Item.WHEAT.id, false));
        goalSelector.a(4, new PathfinderGoalFollowParent(this, 0.5f));
        goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.5f));
        goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6F));
        goalSelector.a(7, new PathfinderGoalRandomLookaround(this));*/

        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalPanic(this, 0.5F));
        this.goalSelector.a(2, new PathfinderGoalBreed(this, 0.5F));
        this.goalSelector.a(3, new PathfinderGoalTempt(this, 0.5F, Item.WHEAT.id, false));
        this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 0.5F));
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.5F));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
        this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
	}
	
	protected void be()
	{
		if (passenger != null )
		{
			EntityHuman joueur = (EntityHuman)passenger;
			if(!H()  && this.H)
			{
				 motX+=((EntityHuman)joueur).motX*10*f;
				 motY+=((EntityHuman)joueur).motY*f;
				 motZ+=((EntityHuman)joueur).motZ*10*f;
			}
			else if(!H()  && !this.H)
			{
				motX+=((EntityHuman)joueur).motX*4*f;
				motY+=((EntityHuman)joueur).motY*f;
				motZ+=((EntityHuman)joueur).motZ*4*f;
			}
			else
			{
				 motX+=((EntityHuman)joueur).motX*f;
				 motY+=((EntityHuman)joueur).motY*f;
				 motZ+=((EntityHuman)joueur).motZ*f;
			}
			pitch=0;
			yaw=this.lastYaw=joueur.yaw;
			if (joueur.bu && this.H)
			{
				motY=0.75f;
			}
			if (H() && joueur.bu )
			{
				motY=0.75f;
			}
		}else
		{
			super.bd();
		}
		
		if (G && this.H)
		{
			motY=0.65f;
		}
	}
	
	public boolean aV()
	{
		if(passenger != null) return false;
		return true;
	}
	
	  protected void a()
	    {
	        super.a();
	        datawatcher.a(16, Byte.valueOf((byte)0));
	    }
	  
	  
	  public void b(NBTTagCompound nbttagcompound) {
	        super.b(nbttagcompound);
	        nbttagcompound.setBoolean("Saddle", this.getSaddled());
	    }

	    public void a(NBTTagCompound nbttagcompound) {
	        super.a(nbttagcompound);
	        this.setSaddled(nbttagcompound.getBoolean("Saddle"));
	    }
	    
	    protected String aQ() {
	        return "mob.cheval";
	    }

	    protected String aR() {
	        return "mob.cheval";
	    }

	    protected String aS() {
	        return "mob.chevaldeath";
	    }
	
	    public boolean c(EntityHuman entityhuman) {
	    	entityhuman.mount(this);
	    	return true;
	    }
	    

	    /**
	     * Returns true if the pig is saddled.
	     */
	    public boolean getSaddled()
	    {
	        return (datawatcher.getByte(16) & 1) != 0;
	    }

	    /**
	     * Set or remove the saddle of the pig.
	     */
	    public void setSaddled(boolean par1)
	    {
	        if (par1)
	        {
	            datawatcher.watch(16, Byte.valueOf((byte)1));
	        }
	        else
	        {
	            datawatcher.watch(16, Byte.valueOf((byte)0));
	        }
	    }
	@Override
	public EntityAnimal createChild(EntityAnimal arg0) {
		return new EntityCheval(this.world);
	}
	@Override
	public int getMaxHealth() {
		return 30;
	}

}

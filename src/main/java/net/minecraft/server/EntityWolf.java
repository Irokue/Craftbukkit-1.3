package net.minecraft.server;

public class EntityWolf extends EntityTameableAnimal {

    private float e;
    private float f;
    private boolean g;
    private boolean h;
    private float i;
    private float j;

    public EntityWolf(World world) {
        super(world);
        this.texture = "/mob/wolf.png";
        this.a(0.6F, 0.8F);
        this.bw = 0.3F;
        this.getNavigation().a(true);
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, this.d);
        this.goalSelector.a(3, new PathfinderGoalLeapAtTarget(this, 0.4F));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, this.bw, true));
        this.goalSelector.a(5, new PathfinderGoalFollowOwner(this, this.bw, 10.0F, 2.0F));
        this.goalSelector.a(6, new PathfinderGoalBreed(this, this.bw));
        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, this.bw));
        this.goalSelector.a(8, new PathfinderGoalBeg(this, 8.0F));
        this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(9, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalOwnerHurtByTarget(this));
        this.targetSelector.a(2, new PathfinderGoalOwnerHurtTarget(this));
        this.targetSelector.a(3, new PathfinderGoalHurtByTarget(this, true));
        this.targetSelector.a(4, new PathfinderGoalRandomTargetNonTamed(this, EntitySheep.class, 16.0F, 200, false));
    }

    public boolean aV() {
        return true;
    }

    public void b(EntityLiving entityliving) {
        super.b(entityliving);
        if (entityliving instanceof EntityHuman) {
            this.setAngry(true);
        }
    }

    protected void bd() {
        this.datawatcher.watch(18, Integer.valueOf(this.getHealth()));
    }

    public int getMaxHealth() {
        return this.isTamed() ? 20 : 8;
    }

    protected void a() {
        super.a();
        this.datawatcher.a(18, new Integer(this.getHealth()));
        this.datawatcher.a(19, new Byte((byte) 0));
    }

    protected boolean e_() {
        return false;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setBoolean("Angry", this.isAngry());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setAngry(nbttagcompound.getBoolean("Angry"));
    }

    protected boolean ba() {
        return this.isAngry();
    }

    protected String aQ() {
        return this.isAngry() ? "mob.wolf.growl" : (this.random.nextInt(3) == 0 ? (this.isTamed() && this.datawatcher.getInt(18) < 10 ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String aR() {
        return "mob.wolf.hurt";
    }

    protected String aS() {
        return "mob.wolf.death";
    }

    protected float aP() {
        return 0.4F;
    }

    protected int getLootId() {
        return -1;
    }

    public void d() {
        super.d();
        if (!this.world.isStatic && this.g && !this.h && !this.l() && this.onGround) {
            this.h = true;
            this.i = 0.0F;
            this.j = 0.0F;
            this.world.broadcastEntityEffect(this, (byte) 8);
        }
    }

    public void h_() {
        super.h_();
        this.f = this.e;
        if (this.bv()) {
            this.e += (1.0F - this.e) * 0.4F;
        } else {
            this.e += (0.0F - this.e) * 0.4F;
        }

        if (this.bv()) {
            this.bx = 10;
        }

        if (this.G()) {
            this.g = true;
            this.h = false;
            this.i = 0.0F;
            this.j = 0.0F;
        } else if ((this.g || this.h) && this.h) {
            if (this.i == 0.0F) {
                this.world.makeSound(this, "mob.wolf.shake", this.aP(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            }

            this.j = this.i;
            this.i += 0.05F;
            if (this.j >= 2.0F) {
                this.g = false;
                this.h = false;
                this.j = 0.0F;
                this.i = 0.0F;
            }

            if (this.i > 0.4F) {
                float f = (float) this.boundingBox.b;
                int i = (int) (MathHelper.sin((this.i - 0.4F) * 3.1415927F) * 7.0F);

                for (int j = 0; j < i; ++j) {
                    float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;

                    this.world.a("splash", this.locX + (double) f1, (double) (f + 0.8F), this.locZ + (double) f2, this.motX, this.motY, this.motZ);
                }
            }
        }
    }

    public float getHeadHeight() {
        return this.length * 0.8F;
    }

    public int bf() {
        return this.isSitting() ? 20 : super.bf();
    }

    public boolean damageEntity(DamageSource damagesource, int i) {
        Entity entity = damagesource.getEntity();

        this.d.a(false);
        if (entity != null && !(entity instanceof EntityHuman) && !(entity instanceof EntityArrow)) {
            i = (i + 1) / 2;
        }

        return super.damageEntity(damagesource, i);
    }

    public boolean k(Entity entity) {
        int i = this.isTamed() ? 4 : 2;

        return entity.damageEntity(DamageSource.mobAttack(this), i);
    }

    public boolean c(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (this.isTamed()) {
            if (itemstack != null && Item.byId[itemstack.id] instanceof ItemFood) {
                ItemFood itemfood = (ItemFood) Item.byId[itemstack.id];

                if (itemfood.h() && this.datawatcher.getInt(18) < 20) {
                    if (!entityhuman.abilities.canInstantlyBuild) {
                        --itemstack.count;
                    }

                    this.heal(itemfood.getNutrition());
                    if (itemstack.count <= 0) {
                        entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
                    }

                    return true;
                }
            }

            if (entityhuman.name.equalsIgnoreCase(this.getOwnerName()) && !this.world.isStatic && !this.b(itemstack)) {
                this.d.a(!this.isSitting());
                this.bu = false;
                this.setPathEntity((PathEntity) null);
            }
        } else if (itemstack != null && itemstack.id == Item.BONE.id && !this.isAngry()) {
            if (!entityhuman.abilities.canInstantlyBuild) {
                --itemstack.count;
            }

            if (itemstack.count <= 0) {
                entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
            }

            if (!this.world.isStatic) {
                // CraftBukkit - added event call and isCancelled check.
                if (this.random.nextInt(3) == 0 && !org.bukkit.craftbukkit.event.CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled()) {
                    this.setTamed(true);
                    this.setPathEntity((PathEntity) null);
                    this.b((EntityLiving) null);
                    this.d.a(true);
                    this.setHealth(20);
                    this.setOwnerName(entityhuman.name);
                    this.e(true);
                    this.world.broadcastEntityEffect(this, (byte) 7);
                } else {
                    this.e(false);
                    this.world.broadcastEntityEffect(this, (byte) 6);
                }
            }

            return true;
        }

        return super.c(entityhuman);
    }

    public boolean b(ItemStack itemstack) {
        return itemstack == null ? false : (!(Item.byId[itemstack.id] instanceof ItemFood) ? false : ((ItemFood) Item.byId[itemstack.id]).h());
    }

    public int bl() {
        return 8;
    }

    public boolean isAngry() {
        return (this.datawatcher.getByte(16) & 2) != 0;
    }

    public void setAngry(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -3)));
        }
    }

    public EntityAnimal createChild(EntityAnimal entityanimal) {
        EntityWolf entitywolf = new EntityWolf(this.world);

        entitywolf.setOwnerName(this.getOwnerName());
        entitywolf.setTamed(true);
        return entitywolf;
    }

    public void i(boolean flag) {
        byte b0 = this.datawatcher.getByte(19);

        if (flag) {
            this.datawatcher.watch(19, Byte.valueOf((byte) 1));
        } else {
            this.datawatcher.watch(19, Byte.valueOf((byte) 0));
        }
    }

    public boolean mate(EntityAnimal entityanimal) {
        if (entityanimal == this) {
            return false;
        } else if (!this.isTamed()) {
            return false;
        } else if (!(entityanimal instanceof EntityWolf)) {
            return false;
        } else {
            EntityWolf entitywolf = (EntityWolf) entityanimal;

            return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false : this.s() && entitywolf.s());
        }
    }

    public boolean bv() {
        return this.datawatcher.getByte(19) == 1;
    }
}

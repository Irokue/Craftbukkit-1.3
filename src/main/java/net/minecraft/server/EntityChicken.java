package net.minecraft.server;

public class EntityChicken extends EntityAnimal {

    public boolean d = false;
    public float e = 0.0F;
    public float f = 0.0F;
    public float g;
    public float h;
    public float i = 1.0F;
    public int j;

    public EntityChicken(World world) {
        super(world);
        this.texture = "/mob/chicken.png";
        this.a(0.3F, 0.7F);
        this.j = this.random.nextInt(6000) + 6000;
        float f = 0.25F;

        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalPanic(this, 0.38F));
        this.goalSelector.a(2, new PathfinderGoalBreed(this, f));
        this.goalSelector.a(3, new PathfinderGoalTempt(this, 0.25F, Item.WHEAT.id, false));
        this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 0.28F));
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, f));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
        this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
    }

    public boolean aV() {
        return true;
    }

    public int getMaxHealth() {
        return 4;
    }

    public void d() {
        super.d();
        this.h = this.e;
        this.g = this.f;
        this.f = (float) ((double) this.f + (double) (this.onGround ? -1 : 4) * 0.3D);
        if (this.f < 0.0F) {
            this.f = 0.0F;
        }

        if (this.f > 1.0F) {
            this.f = 1.0F;
        }

        if (!this.onGround && this.i < 1.0F) {
            this.i = 1.0F;
        }

        this.i = (float) ((double) this.i * 0.9D);
        if (!this.onGround && this.motY < 0.0D) {
            this.motY *= 0.6D;
        }

        this.e += this.i * 2.0F;
        if (!this.isBaby() && !this.world.isStatic && --this.j <= 0) {
            this.world.makeSound(this, "mob.chickenplop", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.b(Item.EGG.id, 1);
            this.j = this.random.nextInt(6000) + 6000;
        }
    }

    protected void a(float f) {}

    protected String aQ() {
        return "mob.chicken";
    }

    protected String aR() {
        return "mob.chickenhurt";
    }

    protected String aS() {
        return "mob.chickenhurt";
    }

    protected int getLootId() {
        return Item.FEATHER.id;
    }

    protected void dropDeathLoot(boolean flag, int i) {
        // CraftBukkit start - whole method
        java.util.List<org.bukkit.inventory.ItemStack> loot = new java.util.ArrayList<org.bukkit.inventory.ItemStack>();
        int j = this.random.nextInt(3) + this.random.nextInt(1 + i);

        if (j > 0) {
            loot.add(new org.bukkit.inventory.ItemStack(Item.FEATHER.id, j));
        }

        if (this.isBurning()) {
            loot.add(new org.bukkit.inventory.ItemStack(Item.COOKED_CHICKEN.id, 1));
        } else {
            loot.add(new org.bukkit.inventory.ItemStack(Item.RAW_CHICKEN.id, 1));
        }

        org.bukkit.craftbukkit.event.CraftEventFactory.callEntityDeathEvent(this, loot);
        // CraftBukkit end
    }

    public EntityAnimal createChild(EntityAnimal entityanimal) {
        return new EntityChicken(this.world);
    }
}

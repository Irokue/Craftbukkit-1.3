package net.minecraft.server;

public class EntityBlaze extends EntityMonster {

    private float d = 0.5F;
    private int e;
    private int g;

    public EntityBlaze(World world) {
        super(world);
        this.texture = "/mob/fire.png";
        this.fireProof = true;
        this.damage = 6;
        this.aV = 10;
    }

    public int getMaxHealth() {
        return 20;
    }

    protected void a() {
        super.a();
        this.datawatcher.a(16, new Byte((byte) 0));
    }

    protected String aQ() {
        return "mob.blaze.breathe";
    }

    protected String aR() {
        return "mob.blaze.hit";
    }

    protected String aS() {
        return "mob.blaze.death";
    }

    public float c(float f) {
        return 1.0F;
    }

    public void d() {
        if (!this.world.isStatic) {
            if (this.G()) {
                this.damageEntity(DamageSource.DROWN, 1);
            }

            --this.e;
            if (this.e <= 0) {
                this.e = 100;
                this.d = 0.5F + (float) this.random.nextGaussian() * 3.0F;
            }

            if (this.m() != null && this.m().locY + (double) this.m().getHeadHeight() > this.locY + (double) this.getHeadHeight() + (double) this.d) {
                this.motY += (0.30000001192092896D - this.motY) * 0.30000001192092896D;
            }
        }

        if (this.random.nextInt(24) == 0) {
            this.world.makeSound(this.locX + 0.5D, this.locY + 0.5D, this.locZ + 0.5D, "fire.fire", 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F);
        }

        if (!this.onGround && this.motY < 0.0D) {
            this.motY *= 0.6D;
        }

        for (int i = 0; i < 2; ++i) {
            this.world.a("largesmoke", this.locX + (this.random.nextDouble() - 0.5D) * (double) this.width, this.locY + this.random.nextDouble() * (double) this.length, this.locZ + (this.random.nextDouble() - 0.5D) * (double) this.width, 0.0D, 0.0D, 0.0D);
        }

        super.d();
    }

    protected void a(Entity entity, float f) {
        if (this.attackTicks <= 0 && f < 2.0F && entity.boundingBox.e > this.boundingBox.b && entity.boundingBox.b < this.boundingBox.e) {
            this.attackTicks = 20;
            this.k(entity);
        } else if (f < 30.0F) {
            double d0 = entity.locX - this.locX;
            double d1 = entity.boundingBox.b + (double) (entity.length / 2.0F) - (this.locY + (double) (this.length / 2.0F));
            double d2 = entity.locZ - this.locZ;

            if (this.attackTicks == 0) {
                ++this.g;
                if (this.g == 1) {
                    this.attackTicks = 60;
                    this.e(true);
                } else if (this.g <= 4) {
                    this.attackTicks = 6;
                } else {
                    this.attackTicks = 100;
                    this.g = 0;
                    this.e(false);
                }

                if (this.g > 1) {
                    float f1 = MathHelper.c(f) * 0.5F;

                    this.world.a((EntityHuman) null, 1009, (int) this.locX, (int) this.locY, (int) this.locZ, 0);

                    for (int i = 0; i < 1; ++i) {
                        EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.world, this, d0 + this.random.nextGaussian() * (double) f1, d1, d2 + this.random.nextGaussian() * (double) f1);

                        entitysmallfireball.locY = this.locY + (double) (this.length / 2.0F) + 0.5D;
                        this.world.addEntity(entitysmallfireball);
                    }
                }
            }

            this.yaw = (float) (Math.atan2(d2, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
            this.b = true;
        }
    }

    protected void a(float f) {}

    protected int getLootId() {
        return Item.BLAZE_ROD.id;
    }

    public boolean isBurning() {
        return this.n();
    }

    protected void dropDeathLoot(boolean flag, int i) {
        if (flag) {
            // CraftBukkit start
            java.util.List<org.bukkit.inventory.ItemStack> loot = new java.util.ArrayList<org.bukkit.inventory.ItemStack>();
            int j = this.random.nextInt(2 + i);

            if (j > 0) {
                loot.add(new org.bukkit.inventory.ItemStack(Item.BLAZE_ROD.id, j));
            }

            org.bukkit.craftbukkit.event.CraftEventFactory.callEntityDeathEvent(this, loot);
            // CraftBukkit end
        }
    }

    public boolean n() {
        return (this.datawatcher.getByte(16) & 1) != 0;
    }

    public void e(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.datawatcher.watch(16, Byte.valueOf(b0));
    }

    protected boolean o() {
        return true;
    }
}

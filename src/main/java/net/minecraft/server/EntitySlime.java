package net.minecraft.server;

import org.bukkit.event.entity.SlimeSplitEvent; // CraftBukkit

public class EntitySlime extends EntityLiving implements IMonster {

    public float a;
    public float b;
    public float c;
    private int jumpDelay = 0;

    public EntitySlime(World world) {
        super(world);
        this.texture = "/mob/slime.png";
        int i = 1 << this.random.nextInt(3);

        this.height = 0.0F;
        this.jumpDelay = this.random.nextInt(20) + 10;
        this.setSize(i);
    }

    protected void a() {
        super.a();
        this.datawatcher.a(16, new Byte((byte) 1));
    }

    public void setSize(int i) {
        this.datawatcher.watch(16, new Byte((byte) i));
        this.a(0.6F * (float) i, 0.6F * (float) i);
        this.setPosition(this.locX, this.locY, this.locZ);
        this.setHealth(this.getMaxHealth());
        this.aV = i;
    }

    public int getMaxHealth() {
        int i = this.getSize();

        return i * i;
    }

    public int getSize() {
        return this.datawatcher.getByte(16);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Size", this.getSize() - 1);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setSize(nbttagcompound.getInt("Size") + 1);
    }

    protected String i() {
        return "slime";
    }

    protected String o() {
        return "mob.slime";
    }

    public void h_() {
        if (!this.world.isStatic && this.world.difficulty == 0 && this.getSize() > 0) {
            this.dead = true;
        }

        this.b += (this.a - this.b) * 0.5F;
        this.c = this.b;
        boolean flag = this.onGround;

        super.h_();
        if (this.onGround && !flag) {
            int i = this.getSize();

            for (int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float) i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float) i * 0.5F * f1;

                this.world.a(this.i(), this.locX + (double) f2, this.boundingBox.b, this.locZ + (double) f3, 0.0D, 0.0D, 0.0D);
            }

            if (this.p()) {
                this.world.makeSound(this, this.o(), this.aP(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.a = -0.5F;
        } else if (!this.onGround && flag) {
            this.a = 1.0F;
        }

        this.l();
    }

    protected void be() {
        this.bb();
        EntityHuman entityhuman = this.world.findNearbyVulnerablePlayer(this, 16.0D); // CraftBukkit TODO: EntityTargetEvent

        if (entityhuman != null) {
            this.a(entityhuman, 10.0F, 20.0F);
        }

        if (this.onGround && this.jumpDelay-- <= 0) {
            this.jumpDelay = this.k();
            if (entityhuman != null) {
                this.jumpDelay /= 3;
            }

            this.bu = true;
            if (this.r()) {
                this.world.makeSound(this, this.o(), this.aP(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.br = 1.0F - this.random.nextFloat() * 2.0F;
            this.bs = (float) (1 * this.getSize());
        } else {
            this.bu = false;
            if (this.onGround) {
                this.br = this.bs = 0.0F;
            }
        }
    }

    protected void l() {
        this.a *= 0.6F;
    }

    protected int k() {
        return this.random.nextInt(20) + 10;
    }

    protected EntitySlime j() {
        return new EntitySlime(this.world);
    }

    public void die() {
        int i = this.getSize();

        if (!this.world.isStatic && i > 1 && this.getHealth() <= 0) {
            int j = 2 + this.random.nextInt(3);

            // CraftBukkit start
            SlimeSplitEvent event = new SlimeSplitEvent((org.bukkit.entity.Slime) this.getBukkitEntity(), j);
            this.world.getServer().getPluginManager().callEvent(event);

            if (!event.isCancelled() && event.getCount() > 0) {
                j = event.getCount();
            } else {
                super.die();
                return;
            }
            // CraftBukkit end

            for (int k = 0; k < j; ++k) {
                float f = ((float) (k % 2) - 0.5F) * (float) i / 4.0F;
                float f1 = ((float) (k / 2) - 0.5F) * (float) i / 4.0F;
                EntitySlime entityslime = this.j();

                entityslime.setSize(i / 2);
                entityslime.setPositionRotation(this.locX + (double) f, this.locY + 0.5D, this.locZ + (double) f1, this.random.nextFloat() * 360.0F, 0.0F);
                this.world.addEntity(entityslime, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.SLIME_SPLIT); // CraftBukkit - SpawnReason
            }
        }

        super.die();
    }

    public void b_(EntityHuman entityhuman) {
        if (this.m()) {
            int i = this.getSize();

            if (this.l(entityhuman) && this.e(entityhuman) < 0.6D * (double) i * 0.6D * (double) i && entityhuman.damageEntity(DamageSource.mobAttack(this), this.n())) {
                this.world.makeSound(this, "mob.slimeattack", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    protected boolean m() {
        return this.getSize() > 1;
    }

    protected int n() {
        return this.getSize();
    }

    protected String aR() {
        return "mob.slime";
    }

    protected String aS() {
        return "mob.slime";
    }

    protected int getLootId() {
        return this.getSize() == 1 ? Item.SLIME_BALL.id : 0;
    }

    public boolean canSpawn() {
        Chunk chunk = this.world.getChunkAtWorldCoords(MathHelper.floor(this.locX), MathHelper.floor(this.locZ));

        return this.world.getWorldData().getType() == WorldType.FLAT && this.random.nextInt(4) != 1 ? false : ((this.getSize() == 1 || this.world.difficulty > 0) && this.random.nextInt(10) == 0 && chunk.a(987234911L).nextInt(10) == 0 && this.locY < 40.0D ? super.canSpawn() : false);
    }

    protected float aP() {
        return 0.4F * (float) this.getSize();
    }

    public int bf() {
        return 0;
    }

    protected boolean r() {
        return this.getSize() > 1;
    }

    protected boolean p() {
        return this.getSize() > 2;
    }
}

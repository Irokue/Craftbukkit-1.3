package net.minecraft.server;

public class PathfinderGoalBreakDoor extends PathfinderGoalDoorInteract {

    private int i;
    private int j = -1;

    public PathfinderGoalBreakDoor(EntityLiving entityliving) {
        super(entityliving);
    }

    public boolean a() {
        return !super.a() ? false : !this.e.a_(this.a.world, this.b, this.c, this.d);
    }

    public void e() {
        super.e();
        this.i = 0;
    }

    public boolean b() {
        double d0 = this.a.e((double) this.b, (double) this.c, (double) this.d);

        return this.i <= 240 && !this.e.a_(this.a.world, this.b, this.c, this.d) && d0 < 4.0D;
    }

    public void c() {
        super.c();
        this.a.world.f(this.a.id, this.b, this.c, this.d, -1);
    }

    public void d() {
        super.d();
        if (this.a.au().nextInt(20) == 0) {
            this.a.world.triggerEffect(1010, this.b, this.c, this.d, 0);
        }

        ++this.i;
        int i = (int) ((float) this.i / 240.0F * 10.0F);

        if (i != this.j) {
            this.a.world.f(this.a.id, this.b, this.c, this.d, i);
            this.j = i;
        }

        if (this.i == 240 && this.a.world.difficulty == 3) {
            // CraftBukkit start
            if (org.bukkit.craftbukkit.event.CraftEventFactory.callEntityBreakDoorEvent(this.a, this.b, this.c, this.d).isCancelled()) {
                this.e();
                return;
            }
            // CraftBukkit end

            this.a.world.setTypeId(this.b, this.c, this.d, 0);
            this.a.world.triggerEffect(1012, this.b, this.c, this.d, 0);
            this.a.world.triggerEffect(2001, this.b, this.c, this.d, this.e.id);
        }
    }
}

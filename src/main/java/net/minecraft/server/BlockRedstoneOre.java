package net.minecraft.server;

import java.util.Random;

import org.bukkit.event.entity.EntityInteractEvent; // CraftBukkit

public class BlockRedstoneOre extends Block {

    private boolean a;

    public BlockRedstoneOre(int i, int j, boolean flag) {
        super(i, j, Material.STONE);
        if (flag) {
            this.b(true);
        }

        this.a = flag;
    }

    public int p_() {
        return 30;
    }

    public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
        this.l(world, i, j, k);
        super.attack(world, i, j, k, entityhuman);
    }

    public void b(World world, int i, int j, int k, Entity entity) {
        // CraftBukkit start
        if (entity instanceof EntityHuman) {
            org.bukkit.event.player.PlayerInteractEvent event = org.bukkit.craftbukkit.event.CraftEventFactory.callPlayerInteractEvent((EntityHuman) entity, org.bukkit.event.block.Action.PHYSICAL, i, j, k, -1, null);
            if (!event.isCancelled()) {
                this.l(world, i, j, k);
                super.b(world, i, j, k, entity);
            }
        } else {
            EntityInteractEvent event = new EntityInteractEvent(entity.getBukkitEntity(), world.getWorld().getBlockAt(i, j, k));
            world.getServer().getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                this.l(world, i, j, k);
                super.b(world, i, j, k, entity);
            }
        }
        // CraftBukkit end
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        this.l(world, i, j, k);
        return super.interact(world, i, j, k, entityhuman, l, f, f1, f2);
    }

    private void l(World world, int i, int j, int k) {
        this.n(world, i, j, k);
        if (this.id == Block.REDSTONE_ORE.id) {
            world.setTypeId(i, j, k, Block.GLOWING_REDSTONE_ORE.id);
        }
    }

    public void b(World world, int i, int j, int k, Random random) {
        if (this.id == Block.GLOWING_REDSTONE_ORE.id) {
            world.setTypeId(i, j, k, Block.REDSTONE_ORE.id);
        }
    }

    public int getDropType(int i, Random random, int j) {
        return Item.REDSTONE.id;
    }

    public int getDropCount(int i, Random random) {
        return this.a(random) + random.nextInt(i + 1);
    }

    public int a(Random random) {
        return 4 + random.nextInt(2);
    }

    public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
        super.dropNaturally(world, i, j, k, l, f, i1);
        /* CraftBukkit start - delegate to getExpDrop
        if (this.getDropType(l, world.random, i1) != this.id) {
            int j1 = 1 + world.random.nextInt(5);

            this.g(world, i, j, k, j1);
        } */
    }

    public int getExpDrop(World world, int l, int i1) {
        if (this.getDropType(l, world.random, i1) != this.id) {
            int j1 = 1 + world.random.nextInt(5);

            return j1;
        }

        return 0;
        // CraftBukkit end
    }

    private void n(World world, int i, int j, int k) {
        Random random = world.random;
        double d0 = 0.0625D;

        for (int l = 0; l < 6; ++l) {
            double d1 = (double) ((float) i + random.nextFloat());
            double d2 = (double) ((float) j + random.nextFloat());
            double d3 = (double) ((float) k + random.nextFloat());

            if (l == 0 && !world.r(i, j + 1, k)) {
                d2 = (double) (j + 1) + d0;
            }

            if (l == 1 && !world.r(i, j - 1, k)) {
                d2 = (double) (j + 0) - d0;
            }

            if (l == 2 && !world.r(i, j, k + 1)) {
                d3 = (double) (k + 1) + d0;
            }

            if (l == 3 && !world.r(i, j, k - 1)) {
                d3 = (double) (k + 0) - d0;
            }

            if (l == 4 && !world.r(i + 1, j, k)) {
                d1 = (double) (i + 1) + d0;
            }

            if (l == 5 && !world.r(i - 1, j, k)) {
                d1 = (double) (i + 0) - d0;
            }

            if (d1 < (double) i || d1 > (double) (i + 1) || d2 < 0.0D || d2 > (double) (j + 1) || d3 < (double) k || d3 > (double) (k + 1)) {
                world.a("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    protected ItemStack c_(int i) {
        return new ItemStack(Block.REDSTONE_ORE);
    }
}

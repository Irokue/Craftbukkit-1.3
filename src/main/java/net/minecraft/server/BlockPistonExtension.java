package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockPistonExtension extends Block {

    private int a = -1;

    public BlockPistonExtension(int i, int j) {
        super(i, j, Material.PISTON);
        this.a(h);
        this.c(0.5F);
    }

    public void remove(World world, int i, int j, int k, int l, int i1) {
        super.remove(world, i, j, k, l, i1);
        if ((i1 & 7) >= Facing.OPPOSITE_FACING.length) return; // CraftBukkit - fixed a piston AIOOBE issue
        int j1 = Facing.OPPOSITE_FACING[f(i1)];

        i += Facing.b[j1];
        j += Facing.c[j1];
        k += Facing.d[j1];
        int k1 = world.getTypeId(i, j, k);

        if (k1 == Block.PISTON.id || k1 == Block.PISTON_STICKY.id) {
            i1 = world.getData(i, j, k);
            if (BlockPiston.f(i1)) {
                Block.byId[k1].c(world, i, j, k, i1, 0);
                world.setTypeId(i, j, k, 0);
            }
        }
    }

    public int a(int i, int j) {
        int k = f(j);

        return i == k ? (this.a >= 0 ? this.a : ((j & 8) != 0 ? this.textureId - 1 : this.textureId)) : (k < 6 && i == Facing.OPPOSITE_FACING[k] ? 107 : 108);
    }

    public int b() {
        return 17;
    }

    public boolean d() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public boolean canPlace(World world, int i, int j, int k) {
        return false;
    }

    public boolean canPlace(World world, int i, int j, int k, int l) {
        return false;
    }

    public int a(Random random) {
        return 0;
    }

    public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        int l = world.getData(i, j, k);

        switch (f(l)) {
        case 0:
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
            this.a(0.375F, 0.25F, 0.375F, 0.625F, 1.0F, 0.625F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
            break;

        case 1:
            this.a(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
            this.a(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
            break;

        case 2:
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
            this.a(0.25F, 0.375F, 0.25F, 0.75F, 0.625F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
            break;

        case 3:
            this.a(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
            this.a(0.25F, 0.375F, 0.0F, 0.75F, 0.625F, 0.75F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
            break;

        case 4:
            this.a(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
            this.a(0.375F, 0.25F, 0.25F, 0.625F, 0.75F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
            break;

        case 5:
            this.a(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
            this.a(0.0F, 0.375F, 0.25F, 0.75F, 0.625F, 0.75F);
            super.a(world, i, j, k, axisalignedbb, list, entity);
        }

        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getData(i, j, k);

        switch (f(l)) {
        case 0:
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
            break;

        case 1:
            this.a(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
            break;

        case 2:
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
            break;

        case 3:
            this.a(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
            break;

        case 4:
            this.a(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
            break;

        case 5:
            this.a(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        int i1 = f(world.getData(i, j, k));
        if ((i1 & 7) >= Facing.OPPOSITE_FACING.length) return; // CraftBukkit - fixed a piston AIOOBE issue
        int j1 = world.getTypeId(i - Facing.b[i1], j - Facing.c[i1], k - Facing.d[i1]);

        if (j1 != Block.PISTON.id && j1 != Block.PISTON_STICKY.id) {
            world.setTypeId(i, j, k, 0);
        } else {
            Block.byId[j1].doPhysics(world, i - Facing.b[i1], j - Facing.c[i1], k - Facing.d[i1], l);
        }
    }

    public static int f(int i) {
        return i & 7;
    }
}

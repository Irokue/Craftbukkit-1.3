package net.minecraft.server;

public class Slot {

    public final int index; // CraftBukkit - private -> public
    public final IInventory inventory;
    public int d;
    public int e;
    public int f;

    public Slot(IInventory iinventory, int i, int j, int k) {
        this.inventory = iinventory;
        this.index = i;
        this.e = j;
        this.f = k;
    }

    public void a(ItemStack itemstack, ItemStack itemstack1) {
        if (itemstack != null && itemstack1 != null) {
            if (itemstack.id == itemstack1.id) {
                int i = itemstack1.count - itemstack.count;

                if (i > 0) {
                    this.a(itemstack, i);
                }
            }
        }
    }

    protected void a(ItemStack itemstack, int i) {}

    protected void c(ItemStack itemstack) {}

    public void b(ItemStack itemstack) {
        this.e();
    }

    public boolean isAllowed(ItemStack itemstack) {
        return true;
    }

    public ItemStack getItem() {
        return this.inventory.getItem(this.index);
    }

    public boolean d() {
        return this.getItem() != null;
    }

    public void set(ItemStack itemstack) {
        this.inventory.setItem(this.index, itemstack);
        this.e();
    }

    public void e() {
        this.inventory.update();
    }

    public int a() {
        return this.inventory.getMaxStackSize();
    }

    public ItemStack a(int i) {
        return this.inventory.splitStack(this.index, i);
    }

    public boolean a(IInventory iinventory, int i) {
        return iinventory == this.inventory && i == this.index;
    }
}

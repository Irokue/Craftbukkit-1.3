package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// CraftBukkit start
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.inventory.InventoryView;
// CraftBukkit end

public abstract class Container {

    public List a = new ArrayList();
    public List b = new ArrayList();
    public int windowId = 0;
    private short e = 0;
    protected List listeners = new ArrayList();
    private Set f = new HashSet();

    // CraftBukkit start
    public boolean checkReachable = true;
    public abstract InventoryView getBukkitView();
    public void transferTo(Container other, org.bukkit.craftbukkit.entity.CraftHumanEntity player) {
        InventoryView source = this.getBukkitView(), destination = other.getBukkitView();
        ((CraftInventory) source.getTopInventory()).getInventory().onClose(player);
        ((CraftInventory) source.getBottomInventory()).getInventory().onClose(player);
        ((CraftInventory) destination.getTopInventory()).getInventory().onOpen(player);
        ((CraftInventory) destination.getBottomInventory()).getInventory().onOpen(player);
    }
    // CraftBukkit end

    public Container() {}

    protected Slot a(Slot slot) {
        slot.d = this.b.size();
        this.b.add(slot);
        this.a.add(null);
        return slot;
    }

    public void addSlotListener(ICrafting icrafting) {
        if (this.listeners.contains(icrafting)) {
            throw new IllegalArgumentException("Listener already listening");
        } else {
            this.listeners.add(icrafting);
            icrafting.a(this, this.a());
            this.b();
        }
    }

    public List a() {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            Slot slot = (Slot) iterator.next();

            arraylist.add(slot.getItem());
        }

        return arraylist;
    }

    public void b() {
        for (int i = 0; i < this.b.size(); ++i) {
            ItemStack itemstack = ((Slot) this.b.get(i)).getItem();
            ItemStack itemstack1 = (ItemStack) this.a.get(i);

            if (!ItemStack.matches(itemstack1, itemstack)) {
                itemstack1 = itemstack == null ? null : itemstack.cloneItemStack();
                this.a.set(i, itemstack1);
                Iterator iterator = this.listeners.iterator();

                while (iterator.hasNext()) {
                    ICrafting icrafting = (ICrafting) iterator.next();

                    icrafting.a(this, i, itemstack1);
                }
            }
        }
    }

    public boolean a(EntityHuman entityhuman, int i) {
        return false;
    }

    public Slot a(IInventory iinventory, int i) {
        Iterator iterator = this.b.iterator();

        Slot slot;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            slot = (Slot) iterator.next();
        } while (!slot.a(iinventory, i));

        return slot;
    }

    public Slot getSlot(int i) {
        return (Slot) this.b.get(i);
    }

    public ItemStack b(int i) {
        Slot slot = (Slot) this.b.get(i);

        return slot != null ? slot.getItem() : null;
    }

    public ItemStack clickItem(int i, int j, boolean flag, EntityHuman entityhuman) {
        ItemStack itemstack = null;

        if (j > 1) {
            return null;
        } else {
            if (j == 0 || j == 1) {
                PlayerInventory playerinventory = entityhuman.inventory;

                if (i == -999) {
                    if (playerinventory.getCarried() != null && i == -999) {
                        if (j == 0) {
                            entityhuman.drop(playerinventory.getCarried());
                            playerinventory.setCarried((ItemStack) null);
                        }

                        if (j == 1) {
                            // CraftBukkit start - store a reference
                            ItemStack itemstack1 = playerinventory.getCarried();
                            if (itemstack1.count > 0) {
                                entityhuman.drop(itemstack1.a(1));
                            }

                            if (itemstack1.count == 0) {
                                // CraftBukkit end
                                playerinventory.setCarried((ItemStack) null);
                            }
                        }
                    }
                } else if (flag) {
                    ItemStack itemstack1 = this.b(i);

                    if (itemstack1 != null) {
                        int k = itemstack1.id;

                        itemstack = itemstack1.cloneItemStack();
                        Slot slot = (Slot) this.b.get(i);

                        if (slot != null && slot.getItem() != null && slot.getItem().id == k) {
                            this.b(i, j, flag, entityhuman);
                        }
                    }
                } else {
                    if (i < 0) {
                        return null;
                    }

                    Slot slot1 = (Slot) this.b.get(i);

                    if (slot1 != null) {
                        ItemStack itemstack2 = slot1.getItem();
                        ItemStack itemstack3 = playerinventory.getCarried();

                        if (itemstack2 != null) {
                            itemstack = itemstack2.cloneItemStack();
                        }

                        int l;

                        if (itemstack2 == null) {
                            if (itemstack3 != null && slot1.isAllowed(itemstack3)) {
                                l = j == 0 ? itemstack3.count : 1;
                                if (l > slot1.a()) {
                                    l = slot1.a();
                                }

                                // CraftBukkit start
                                if (itemstack3.count >= l) {
                                    slot1.set(itemstack3.a(l));
                                }
                                // CraftBukkit end

                                if (itemstack3.count == 0) {
                                    playerinventory.setCarried((ItemStack) null);
                                }
                            }
                        } else if (itemstack3 == null) {
                            l = j == 0 ? itemstack2.count : (itemstack2.count + 1) / 2;
                            ItemStack itemstack4 = slot1.a(l);

                            playerinventory.setCarried(itemstack4);
                            if (itemstack2.count == 0) {
                                slot1.set((ItemStack) null);
                            }

                            slot1.b(playerinventory.getCarried());
                        } else if (slot1.isAllowed(itemstack3)) {
                            if (itemstack2.id == itemstack3.id && (!itemstack2.usesData() || itemstack2.getData() == itemstack3.getData()) && ItemStack.equals(itemstack2, itemstack3)) {
                                l = j == 0 ? itemstack3.count : 1;
                                if (l > slot1.a() - itemstack2.count) {
                                    l = slot1.a() - itemstack2.count;
                                }

                                if (l > itemstack3.getMaxStackSize() - itemstack2.count) {
                                    l = itemstack3.getMaxStackSize() - itemstack2.count;
                                }

                                itemstack3.a(l);
                                if (itemstack3.count == 0) {
                                    playerinventory.setCarried((ItemStack) null);
                                }

                                itemstack2.count += l;
                            } else if (itemstack3.count <= slot1.a()) {
                                slot1.set(itemstack3);
                                playerinventory.setCarried(itemstack2);
                            }
                        } else if (itemstack2.id == itemstack3.id && itemstack3.getMaxStackSize() > 1 && (!itemstack2.usesData() || itemstack2.getData() == itemstack3.getData()) && ItemStack.equals(itemstack2, itemstack3)) {
                            l = itemstack2.count;
                            if (l > 0 && l + itemstack3.count <= itemstack3.getMaxStackSize()) {
                                itemstack3.count += l;
                                itemstack2 = slot1.a(l);
                                if (itemstack2.count == 0) {
                                    slot1.set((ItemStack) null);
                                }

                                slot1.b(playerinventory.getCarried());
                            }
                        }

                        slot1.e();
                    }
                }
            }

            return itemstack;
        }
    }

    protected void b(int i, int j, boolean flag, EntityHuman entityhuman) {
        this.clickItem(i, j, flag, entityhuman);
    }

    public void a(EntityHuman entityhuman) {
        PlayerInventory playerinventory = entityhuman.inventory;

        if (playerinventory.getCarried() != null) {
            entityhuman.drop(playerinventory.getCarried());
            playerinventory.setCarried((ItemStack) null);
        }
    }

    public void a(IInventory iinventory) {
        this.b();
    }

    public void setItem(int i, ItemStack itemstack) {
        this.getSlot(i).set(itemstack);
    }

    public boolean b(EntityHuman entityhuman) {
        return !this.f.contains(entityhuman);
    }

    public void a(EntityHuman entityhuman, boolean flag) {
        if (flag) {
            this.f.remove(entityhuman);
        } else {
            this.f.add(entityhuman);
        }
    }

    public abstract boolean c(EntityHuman entityhuman);

    protected boolean a(ItemStack itemstack, int i, int j, boolean flag) {
        boolean flag1 = false;
        int k = i;

        if (flag) {
            k = j - 1;
        }

        Slot slot;
        ItemStack itemstack1;

        if (itemstack.isStackable()) {
            while (itemstack.count > 0 && (!flag && k < j || flag && k >= i)) {
                slot = (Slot) this.b.get(k);
                itemstack1 = slot.getItem();
                if (itemstack1 != null && itemstack1.id == itemstack.id && (!itemstack.usesData() || itemstack.getData() == itemstack1.getData()) && ItemStack.equals(itemstack, itemstack1)) {
                    int l = itemstack1.count + itemstack.count;

                    if (l <= itemstack.getMaxStackSize()) {
                        itemstack.count = 0;
                        itemstack1.count = l;
                        slot.e();
                        flag1 = true;
                    } else if (itemstack1.count < itemstack.getMaxStackSize()) {
                        itemstack.count -= itemstack.getMaxStackSize() - itemstack1.count;
                        itemstack1.count = itemstack.getMaxStackSize();
                        slot.e();
                        flag1 = true;
                    }
                }

                if (flag) {
                    --k;
                } else {
                    ++k;
                }
            }
        }

        if (itemstack.count > 0) {
            if (flag) {
                k = j - 1;
            } else {
                k = i;
            }

            while (!flag && k < j || flag && k >= i) {
                slot = (Slot) this.b.get(k);
                itemstack1 = slot.getItem();
                if (itemstack1 == null) {
                    slot.set(itemstack.cloneItemStack());
                    slot.e();
                    itemstack.count = 0;
                    flag1 = true;
                    break;
                }

                if (flag) {
                    --k;
                } else {
                    ++k;
                }
            }
        }

        return flag1;
    }
}

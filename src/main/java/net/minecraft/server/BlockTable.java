package net.minecraft.server;

public class BlockTable extends Block
{
  public BlockTable(int i, int j)
  {
    super(i, j, Material.WOOD);
  }

  protected BlockTable(int i, int j, Material material) {
    super(i, j, material);
  }

  public boolean c() {
    return false;
  }

  public boolean d() {
    return false;
  }
}
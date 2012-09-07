package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet133Money extends Packet
{
  public double money;
  public int playerId;

  public Packet133Money()
  {
  }

  public Packet133Money(int playerId, int money)
  {
    this.playerId = playerId;
    this.money = money;
  }

  public void a(DataInputStream datainputstream)
    throws IOException
  {
    this.playerId = datainputstream.readInt();
    this.money = datainputstream.readDouble();
  }

  public void a(DataOutputStream dataoutputstream) throws IOException
  {
    dataoutputstream.writeInt(this.playerId);
    dataoutputstream.writeDouble(this.money);
  }

  public void handle(NetHandler nethandler)
  {
    nethandler.a(this);
  }

  public int a()
  {
    return (int)(12.0D * this.money);
  }
}
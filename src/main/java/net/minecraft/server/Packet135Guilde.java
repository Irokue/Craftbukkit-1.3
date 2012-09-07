package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet135Guilde extends Packet
{
  public String guildeRank = "";
  public String guilde = "";
  public int playerId;

  public Packet135Guilde()
  {
  }

  public Packet135Guilde(int playerId, String guilde, String guildeRank)
  {
    this.playerId = playerId;
    this.guilde = guilde;
    this.guildeRank = guildeRank;
  }

  public void a(DataInputStream datainputstream) throws IOException
  {
    this.playerId = datainputstream.readInt();
    this.guilde = a(datainputstream, 30);
    this.guildeRank = a(datainputstream, 20);
  }

  public void a(DataOutputStream dataoutputstream) throws IOException
  {
    dataoutputstream.writeInt(this.playerId);
    if (this.guilde == null) this.guilde = "Aucune Guilde";
    a(this.guilde, dataoutputstream);
    if (this.guildeRank == null) this.guildeRank = "Aucun Rang";
    a(this.guildeRank, dataoutputstream);
  }

  public void handle(NetHandler nethandler)
  {
    nethandler.a(this);
  }

  public int a()
  {
    return 24;
  }
}
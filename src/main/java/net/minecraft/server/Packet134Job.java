package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet134Job extends Packet
{
  public int jobLevel;
  public int jobExperience;
  public int jobMaxExperience;
  public String job = "";
  public int playerId;

  public Packet134Job()
  {
  }

  public Packet134Job(int playerId, String job, int jobLevel, int jobExperience, int jobMaxExperience)
  {
    this.playerId = playerId;
    this.job = job;
    this.jobExperience = jobExperience;
    this.jobMaxExperience = jobMaxExperience;
    this.jobLevel = jobLevel;
  }

  public void a(DataInputStream datainputstream) throws IOException
  {
    this.playerId = datainputstream.readInt();
    this.job = a(datainputstream, 20);
    this.jobLevel = datainputstream.readInt();
    this.jobExperience = datainputstream.readInt();
    this.jobMaxExperience = datainputstream.readInt();
  }

  public void a(DataOutputStream dataoutputstream) throws IOException
  {
    dataoutputstream.writeInt(this.playerId);
    if (this.job == null) this.job = "Aucun";
    a(this.job, dataoutputstream);
    dataoutputstream.writeInt(this.jobLevel);
    dataoutputstream.writeInt(this.jobExperience);
    dataoutputstream.writeInt(this.jobMaxExperience);
  }

  public void handle(NetHandler nethandler)
  {
    nethandler.a(this);
  }

  public int a()
  {
    return 12;
  }
}
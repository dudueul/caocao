using System;
using System.Xml;
using BattleRecorder;

[Serializable]
public sealed class GuildSiegeDefenceObjInfo : IEventXmlSerializable, IEventEEXSerializable, IWritable
{
	public int slotNumber;

	public int coordIdx;

	public int coordPos;

	public byte key
	{
		get
		{
			return 0;
		}
	}

	public void ImportFromXml(XmlNode xmlNode)
	{
	}

	public override string ToString()
	{
		return null;
	}

	public static GuildSiegeDefenceObjInfo Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public void Write(OptimizedBinaryWriter w)
	{
	}
}

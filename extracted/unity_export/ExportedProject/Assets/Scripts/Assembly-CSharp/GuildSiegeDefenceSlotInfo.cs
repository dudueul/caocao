using System;
using System.Collections.Generic;
using System.Xml;
using BattleRecorder;

[Serializable]
public sealed class GuildSiegeDefenceSlotInfo : IEventXmlSerializable, IEventEEXSerializable, IWritable
{
	public int slotNumber;

	public int bfGroupIdx;

	public EnumData.GuildSiegeObjectType objectType;

	public List<int> bfIndices;

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

	public static GuildSiegeDefenceSlotInfo Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public void Write(OptimizedBinaryWriter w)
	{
	}
}

using System;
using System.Xml;
using BattleRecorder;
using Caocao;

[Serializable]
public sealed class SpawnUnitInfo : IEventXmlSerializable, IEventEEXSerializable, IWritable
{
	public int turnMin;

	public int turnMax;

	public int charId;

	public UnitSide unitSide;

	public ForceFlag turnType;

	public string nickName;

	public int bfIdx;

	public int unitLv;

	public int aiType;

	public TargetInfo aiTargetUnitInfo;

	public IntVector2 aiTargetPos;

	public byte key
	{
		get
		{
			return 0;
		}
	}

	public bool CheckSpawn()
	{
		return false;
	}

	public void SpawnUnit(IntVector2 coord, UnitDirection direct, int spawnIndex, bool usePermanentData, bool useSpecSpawnData)
	{
	}

	public void ImportFromXml(XmlNode xmlNode)
	{
	}

	public override string ToString()
	{
		return null;
	}

	public static SpawnUnitInfo Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public void Write(OptimizedBinaryWriter w)
	{
	}
}

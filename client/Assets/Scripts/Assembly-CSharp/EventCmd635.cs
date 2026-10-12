using System;
using System.Collections.Generic;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd635 : EventCmdBase
{
	public static readonly string ms_code;

	public ForceFlag forceFlag;

	public EnumData.SpawnPosType spawnPosType;

	public List<IntVector2> spawnCoords;

	public int spawnerBfIdx;

	public bool usePermanentData;

	public bool useSpecSpawnData;

	public List<SpawnUnitInfo> spawnUnitInfos;

	private int spawnIndex;

	private UnitData spawner;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override void _Apply()
	{
	}

	private void FindSpawner()
	{
	}

	public override void _Revert()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

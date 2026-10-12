using System;
using System.Xml;

[Serializable]
public class EventCmd528 : EventCmdBase
{
	public static readonly string ms_code;

	public SpawnInfo spawnInfo;

	[NonSerialized]
	private BattleMapObject m_object;

	[NonSerialized]
	private UnitData m_unit;

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

	public override void _Revert()
	{
	}

	private void SetMapObjectTypeSpawner()
	{
	}

	private void SetUnitTypeSpawner(Unit unit)
	{
	}

	private bool ValidCheckObjectType()
	{
		return false;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

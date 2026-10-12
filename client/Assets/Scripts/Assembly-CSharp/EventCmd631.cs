using System;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd631 : EventCmdBase
{
	public static readonly string ms_code;

	public int bfGroup;

	public int bfMaxCount;

	public IntRect rect;

	public UnitDirection direct;

	public int nAIIndex;

	public TargetInfo aiTargetUnitInfo;

	public IntVector2 aiTargetPos;

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

	public void SetUnit(BattleFieldDefenseUnit defenseUnit)
	{
	}

	public void SetSoldier(BattleFieldDefenseUnit defenseUnit)
	{
	}

	public override void _Revert()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

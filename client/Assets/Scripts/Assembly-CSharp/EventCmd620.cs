using System;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd620 : EventCmdBase
{
	public static readonly string ms_code;

	public int bfIndex;

	public int groupNo;

	public UnitDirection direct;

	public int cashedNo;

	public EnumData.BattleOwnTeamDeckType optimalJob;

	public bool isHide;

	public IntVector2 pos;

	public int aiType;

	public TargetInfo aiTargetUnitInfo;

	public IntVector2 aiTargetPos;

	public int formationIndex;

	public EnumData.PvpPositionType pvpPositionType;

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

	public UnitData SetUnit(UserUnit userUnit)
	{
		return null;
	}

	public override void _Revert()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

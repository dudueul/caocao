using System;
using System.Xml;
using Caocao;

[Serializable]
public class SlotInfo : IEventXmlSerializable, IEventEEXSerializable
{
	public int bfIndex;

	public UnitSide unitSide;

	public IntVector2 spawnPos;

	public UnitDirection spawnDirection;

	public EnumData.BattleOwnTeamDeckType optimalJob;

	public bool isHidden;

	public int aiType;

	public TargetInfo aiTargetUnitInfo;

	public IntVector2 aiTargetPos;

	public int groupNo;

	public int friendNo;

	public int cashedNo;

	public int formationIndex;

	public EnumData.PvpPositionType pvpPositionType;

	public SlotInfo()
	{
	}

	public SlotInfo(UnitSide side)
	{
	}

	public virtual void ImportFromXml(XmlNode xmlNode)
	{
	}

	public override string ToString()
	{
		return null;
	}
}

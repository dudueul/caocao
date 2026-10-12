using System;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd628 : EventCmdBase
{
	public static readonly string ms_code;

	public int bfIndex;

	public int groupNo;

	public UnitDirection direct;

	public bool isHide;

	public IntVector2 pos;

	public int aiType;

	public TargetInfo aiTargetUnitInfo;

	public IntVector2 aiTargetPos;

	public int correctedLevel;

	public int slotIndex;

	public bool isElite;

	public int addStrPct;

	public int addChaPct;

	public int addWisPct;

	public int addDexPct;

	public int addLuckPct;

	public int addAtkPct;

	public int addDefPct;

	public int addIntePct;

	public int addAgiPct;

	public int addMorPct;

	public int addHpPct;

	public int addMpPct;

	private UnitData m_unit;

	private bool m_appliedStats;

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

	private void ApplyPassives(ThousandBattleInfo.UnitInfoBase unitInfoBase)
	{
	}

	private void EquipTreasures(ThousandBattleInfo.UnitInfoBase unitInfoBase)
	{
	}

	public override void _Revert()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

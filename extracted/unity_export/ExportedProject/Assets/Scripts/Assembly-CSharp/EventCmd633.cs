using System;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd633 : EventCmdBase
{
	public static readonly string ms_code;

	public UnitDirection direct;

	public bool isHide;

	public IntVector2 pos;

	public int aiType;

	public TargetInfo aiTargetUnitInfo;

	public IntVector2 aiTargetPos;

	public EnumData.RaidType raidType;

	public EnumData.RaidUseType raidUseType;

	public EnumData.RaidDifficulty raidDifficulty;

	public string nick;

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

	public override bool _ApplyOnLoading()
	{
		return false;
	}

	public override void _Revert()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

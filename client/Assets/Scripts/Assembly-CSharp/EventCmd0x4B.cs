using System;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd0x4B : EventCmdBase
{
	public static readonly string ms_code;

	public int nBFIndex;

	public UnitDirection nDirect;

	public IntVector2 vPos;

	public bool bHide;

	public int nGroupNo;

	public int nFriendNo;

	public int nCashedNo;

	public EnumData.BattleOwnTeamDeckType nOptimalJob;

	public int formationIndex;

	public EnumData.PvpPositionType pvpPositionType;

	[NonSerialized]
	private UnitData m_unitData;

	[NonSerialized]
	private bool alreadyDeployed;

	[NonSerialized]
	private IntVector2 prevGridPos;

	[NonSerialized]
	private bool prevHiddenByEvent;

	[NonSerialized]
	private UnitDirection prevUnitDirection;

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

	private void OnComplete()
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

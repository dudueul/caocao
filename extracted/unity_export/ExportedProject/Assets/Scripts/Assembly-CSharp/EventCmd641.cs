using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd641 : EventCmdBase
{
	public static readonly string ms_code;

	public UnitDirection nDirect;

	public bool isHide;

	public IntVector2 vPos;

	public int aiType;

	public TargetInfo aiTargetUnitInfo;

	public IntVector2 aiTargetPos;

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

	[DebuggerHidden]
	private IEnumerator ProcessSpawnWorldboss()
	{
		return null;
	}

	private void AddSpAction(int worldBossPassiveId)
	{
	}

	private void AddRandomSpAction(int randomGroup)
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

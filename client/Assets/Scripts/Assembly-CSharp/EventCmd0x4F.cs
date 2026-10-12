using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd0x4F : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo1;

	public TargetInfo targetUnitInfo2;

	public UnitDirection nDirect;

	public bool bDirecting;

	public bool bDelayBeforeAct;

	public bool bDelayAfterAct;

	public bool isGrouping;

	public bool useMultiTargetOption;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator ChangeBattleUnitDirection()
	{
		return null;
	}

	private void ChangeDirection(UnitData unit, UnitDirection faceDirection)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

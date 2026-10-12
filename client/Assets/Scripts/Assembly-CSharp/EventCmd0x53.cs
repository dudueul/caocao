using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x53 : EventCmdBase
{
	public static readonly string ms_code;

	public bool bDeath;

	public TargetInfo targetUnitInfo;

	public bool waiting;

	public bool showRetreatMsg;

	public bool notCheckMission;

	public bool hideAsEvent;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator RetreatUnit()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator RetreatOneUnit(UnitData unitData)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

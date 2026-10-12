using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using Thingsoft.Pool;

[Serializable]
public class EventCmd542 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator __BattleMove(Thingsoft.Pool.UnitData units)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd334 : EventCmdBase
{
	public static readonly string ms_code;

	public int battleFieldId;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator Wait(List<BattleFieldIcon> targetIcons, float time)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

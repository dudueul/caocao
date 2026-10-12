using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x64 : EventCmdBase
{
	public static readonly string ms_code;

	public bool bIsOurTroop;

	public EnumData.DuelAction nTourneyActionIndex;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override void Skip()
	{
	}

	[DebuggerHidden]
	private IEnumerator __Run()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

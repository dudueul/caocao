using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x5F : EventCmdBase
{
	public static readonly string ms_code;

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

	private static void UpdateMission()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

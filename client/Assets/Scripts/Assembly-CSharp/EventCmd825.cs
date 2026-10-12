using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using LitJson;

[Serializable]
public class EventCmd825 : EventCmdBase
{
	public static readonly string ms_code;

	public int townId;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void OnProcessLoseTown(JsonData response)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowTownLostEffect()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

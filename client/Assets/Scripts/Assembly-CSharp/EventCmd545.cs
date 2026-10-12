using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd545 : EventCmdBase
{
	public static readonly string ms_code;

	public EnumData.ArenaSpeechType speechType;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator __Run(ArenaDialog pdDialog)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator __Speech(int unitID, EnumData.DialogType dialogType, int faceId, string nick, UnitData unitData, string dialogMsg)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

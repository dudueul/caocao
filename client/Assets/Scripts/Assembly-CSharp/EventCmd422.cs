using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd422 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public string dialogMessage;

	public bool waiting;

	public EnumData.DialogType typeParam;

	public int faceType;

	public EnumData.IllustDialogIllustDirect direct;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitDialogClose()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowDialog(int unitId, string unitName)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

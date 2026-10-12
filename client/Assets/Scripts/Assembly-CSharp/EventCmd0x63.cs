using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x63 : EventCmdBase
{
	public static readonly string ms_code;

	public bool bIsOurTroop;

	public List<string> lDialog;

	public bool bDelay;

	public int faceType;

	public EnumData.DialogType typeParam;

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

	private void AddDialog(string sDialog)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

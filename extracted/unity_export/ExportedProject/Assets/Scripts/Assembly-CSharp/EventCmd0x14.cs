using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x14 : EventCmdBase
{
	public static readonly string ms_code;

	public List<Dialog> lDialogList;

	public bool m_UICameraEventOn;

	public override string ToString()
	{
		return null;
	}

	public void AddDialog(Dialog dialogBase)
	{
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitDialogClose()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd0x67 : EventCmdBase
{
	public static readonly string ms_code;

	public int nNumber;

	public string description;

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
	private IEnumerator WaitForClosing(GameObject popUpWindow)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

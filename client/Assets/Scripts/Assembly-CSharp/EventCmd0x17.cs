using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd0x17 : EventCmdBase
{
	public static readonly string ms_code;

	public string m_sStringTemp;

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
	private IEnumerator Wait(GameObject placeObj)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

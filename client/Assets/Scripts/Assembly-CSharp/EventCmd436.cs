using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd436 : EventCmdBase
{
	public static readonly string ms_code;

	public string prefab;

	public bool wait;

	public bool force;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override void _Apply()
	{
	}

	[DebuggerHidden]
	private IEnumerator Wait(GameObject go)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

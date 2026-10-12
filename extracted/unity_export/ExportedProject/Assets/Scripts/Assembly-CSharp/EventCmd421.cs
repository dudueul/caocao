using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd421 : EventCmdBase
{
	public static readonly string ms_code;

	public string prefab;

	public string animation;

	public string texture;

	public bool off;

	private GameObject panelObj;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator Wait(GameObject panel)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

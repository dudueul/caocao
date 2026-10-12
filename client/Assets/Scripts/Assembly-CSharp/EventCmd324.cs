using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd324 : EventCmdBase
{
	public static readonly string ms_code;

	public UnitInfo unitInfo;

	public bool waiting;

	private GameObject panelObj;

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

	private void SetDescrition(GameObject obj)
	{
	}

	public void ClosePanel()
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

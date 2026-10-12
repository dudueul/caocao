using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd0x18 : EventCmdBase
{
	public static readonly string ms_code;

	public string m_eventName;

	public string m_placeName;

	public string m_time;

	private GameObject panelObj;

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

	private void ShowEventInformation()
	{
	}

	private void ShowPlaceInformation()
	{
	}

	private void ShowAll()
	{
	}

	private void GetScenarioYearInform(UILabelLocalized label)
	{
	}

	public void ClosePanel()
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

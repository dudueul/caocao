using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x59 : EventCmdBase
{
	public static readonly string ms_code;

	public int nExcessGold;

	public bool bFinal;

	public List<EventCellPatterns.Event_Trophy> lTrophyList;

	public bool scenarioBlock01;

	public bool scenarioBlock02;

	public List<bool> scenarioBlockList;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator RunCoroutine()
	{
		return null;
	}

	private void AddScenarioBlock(bool isBlock, bool isAvailableLock)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowResultPopup()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator WaitAndShowResultPanel()
	{
		return null;
	}

	private void AddItem(EventCellPatterns.Event_Trophy sTrophy)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

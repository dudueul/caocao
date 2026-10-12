using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd0x3B : EventCmdBase
{
	public static readonly string ms_code;

	public List<int> unitList;

	public EnumData.UnitJoining nJoining;

	public int nStep;

	public bool useShortAni;

	private GameObject panelObj;

	private UnitInfo unitInfo;

	public bool waiting;

	private int popupCompleted;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator __Apply()
	{
		return null;
	}

	public override void _Apply()
	{
	}

	private void ShowUnitDescription()
	{
	}

	private void SetDescrition(GameObject obj)
	{
	}

	private void ShowUnitChangeState()
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

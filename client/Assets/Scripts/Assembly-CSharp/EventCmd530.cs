using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd530 : EventCmdBase
{
	public static readonly string ms_code;

	public IntVector2 entryPos;

	public IntVector2 exitPos;

	public int triggerVariable;

	public int count;

	public bool noEffect;

	[NonSerialized]
	private int limitCount;

	[NonSerialized]
	private bool closeGate;

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

	private void ChckWarp()
	{
	}

	[DebuggerHidden]
	private IEnumerator DoWarp(UnitData unitData)
	{
		return null;
	}

	private void ShowEffect(UnitData unitData)
	{
	}

	public bool isActiveWarpZone()
	{
		return false;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

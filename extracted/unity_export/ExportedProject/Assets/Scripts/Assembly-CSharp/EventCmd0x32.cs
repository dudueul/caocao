using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using Caocao;
using UnityEngine;

[Serializable]
public class EventCmd0x32 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public IntVector2 vPos;

	public bool moveFromCurrentGrid;

	public UnitDirection nDirect;

	public bool bWaiting;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator __ScenarioMove(UnitController unitCtrl)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator __BattleMove(UnitController unitCtrl)
	{
		return null;
	}

	private void ChangeDirection(GameObject unit, UnitDirection faceDirection)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

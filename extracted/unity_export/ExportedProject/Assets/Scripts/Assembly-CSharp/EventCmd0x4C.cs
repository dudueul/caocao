using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x4C : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public IntVector2 vPos;

	public EnumData.RevivalPosType showPosType;

	public TargetInfo showPosTargetInfo;

	private Dictionary<UnitData, IntVector2> m_prevGridPositions;

	public override string ToString()
	{
		return null;
	}

	private IntVector2 FindShowPos(UnitData unit)
	{
		return default(IntVector2);
	}

	public override void Run()
	{
	}

	public override void Skip()
	{
	}

	private bool ShowUnit(bool apply, bool showEffect)
	{
		return false;
	}

	[DebuggerHidden]
	private IEnumerator Waiting()
	{
		return null;
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

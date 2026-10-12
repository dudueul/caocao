using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using Caocao;
using UnityEngine;

[Serializable]
public class EventCmd0x30 : EventCmdBase
{
	public static readonly string ms_code;

	public UnitInfo unitInfo;

	public IntVector2 vPos;

	public UnitDirection nDirect;

	public EnumData.EventUnitAnimFsmState nActionID;

	protected GameObject unitParents;

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

	public override bool _ApplyOnLoading()
	{
		return false;
	}

	[DebuggerHidden]
	private IEnumerator SetAnimation(UnitData unit)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator Appear(UnitData unit)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

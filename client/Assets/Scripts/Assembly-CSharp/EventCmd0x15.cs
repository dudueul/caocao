using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x15 : EventCmdBase
{
	public static readonly string ms_code;

	public List<TargetInfo> targetUnitInfos;

	public string dialogMessage;

	public bool Waiting;

	public EnumData.DialogType typeParam;

	public int faceType;

	public bool m_UICameraEventOn;

	public EnumData.DialogPortraitPosition direct;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator __Run(int unitID, int faceId, string nick, UnitData unitData)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

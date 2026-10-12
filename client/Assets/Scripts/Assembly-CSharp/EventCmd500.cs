using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd500 : EventCmdBase
{
	public static readonly string ms_code;

	public List<TargetInfo> targetUnitInfos;

	public string dialogMessage;

	public bool Waiting;

	public EnumData.DialogType typeParam;

	public int faceType;

	public bool m_UICameraEventOn;

	public bool forbidBattleCamMove;

	public EnumData.DialogPortraitPosition direct;

	public string showFaceName;

	public string showNickname;

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

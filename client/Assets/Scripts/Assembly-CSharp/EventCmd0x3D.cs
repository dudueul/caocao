using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class EventCmd0x3D : EventCmdBase
{
	public static readonly string ms_code;

	public ObscuredInt rewardId;

	public int nItemGradeIndex;

	public int unitID;

	public bool bChangeView;

	public string editorItemName;

	public ObscuredInt rewardType;

	public int nItemQuantity;

	public ObscuredInt saveFlagID;

	private int m_prevItemFlag;

	private int m_prevSaveFlag;

	private RewardInfo m_gainedRewardInfo;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator AnimateInBattle()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator GetItemInBattle()
	{
		return null;
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	[DebuggerHidden]
	private IEnumerator GetItemInScenario()
	{
		return null;
	}

	private GameObject ShowItemPopup()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

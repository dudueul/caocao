using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd0x50 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo1;

	public TargetInfo targetUnitInfo2;

	public EnumData.BattleUnitAnimFsmState nBattleActionID;

	public bool bDelayBeforeAct;

	public bool bDelayAfterAct;

	private Coroutine currentCoroutine;

	public bool useMultiTargetOption;

	[NonSerialized]
	private List<UnitData> unitDatas;

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

	[DebuggerHidden]
	private IEnumerator ChangeBattleUnitAnimation(UnitData unitData)
	{
		return null;
	}

	private void OnAnimationEventTriggered(tk2dSpriteAnimator animator, tk2dSpriteAnimationClip clip, int currentFrameIdx)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}

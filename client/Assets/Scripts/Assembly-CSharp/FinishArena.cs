using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class FinishArena : FinishBase
{
	private UITexture m_texEnemyFace;

	private GameObject m_objClear;

	private GameObject m_objBoss;

	private UILabelLocalized m_labelStageName;

	private List<GameObject> sealObjs;

	private UILabelLocalized m_labelDesc;

	private Transform m_objClearReward;

	private Transform m_objStampReward;

	private Transform m_objCoinReward;

	private PrefabInstance m_instCoinReward;

	private UIGrid m_gridStampReward;

	private Transform m_btnSupply;

	private Transform m_btnExit;

	private Action restartCallback;

	private int arenaLevel;

	private int prevCoin;

	public void OnInit(Transform parent)
	{
	}

	protected override void OnDestroy()
	{
	}

	public override void ShowFinish()
	{
	}

	public void UpdatePanelUI()
	{
	}

	public void ShowVictoryGrade(int grade)
	{
	}

	private void SetButtonsEnable(bool isEnable)
	{
	}

	[DebuggerHidden]
	private IEnumerator SendArenaBattleEnd()
	{
		return null;
	}

	protected override string PrepareBattleResult(bool win, bool setInjury, bool cancelProtect)
	{
		return null;
	}

	private void EndBattleProcess(JsonData response)
	{
	}

	private void OnProcessBattleEnd(JsonData response)
	{
	}

	[DebuggerHidden]
	private IEnumerator OnPostEffect(int addUserExp)
	{
		return null;
	}

	private void ShowStampingEffect()
	{
	}

	public void UpdateChildUIStatus()
	{
	}

	protected void Clear()
	{
	}

	protected virtual void GoToArena()
	{
	}
}

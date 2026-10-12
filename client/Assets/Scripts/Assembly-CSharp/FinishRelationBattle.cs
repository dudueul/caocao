using System;
using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class FinishRelationBattle : FinishBase
{
	private UILabelLocalized m_labellTitle;

	private UIGrid m_gridFace;

	private GameObject m_victoryObj;

	private UILabelLocalized m_victoryTitle;

	private GameObject m_loseObj;

	private UILabelLocalized m_loseDesc;

	private Transform m_btnCloseVictory;

	private Transform m_btnCloseLose;

	private Transform m_btnRetryVictory;

	private Transform m_btnRetryLose;

	private Action restartCallback;

	private Action exitCallback;

	private bool alreadyCompletedRelation;

	private void Start()
	{
	}

	public static void Show(Action restartCallback = null, Action exitCallback = null)
	{
	}

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

	private void UpdateFaces()
	{
	}

	private void SetButtonsEnable(bool isEnable)
	{
	}

	[DebuggerHidden]
	private IEnumerator SendRelationBattleEnd()
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

	public void UpdateChildUIStatus()
	{
	}
}

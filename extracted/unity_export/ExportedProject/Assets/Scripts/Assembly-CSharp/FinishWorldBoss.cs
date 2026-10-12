using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class FinishWorldBoss : FinishBase
{
	private UIButton m_btnClose;

	private Transform m_itemGridUI;

	private WorldBossState m_worldBossState;

	public void OnInit(Transform parent)
	{
	}

	protected override void OnDestroy()
	{
	}

	public override void ShowFinish()
	{
	}

	[DebuggerHidden]
	private IEnumerator SendWorldBossEnd(bool isWin)
	{
		return null;
	}

	public void UpdateChildUIStatus()
	{
	}

	public void UpdatePanelUI()
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

	[DebuggerHidden]
	protected override IEnumerator ShowPedlarInstanceEffect()
	{
		return null;
	}

	private void SetButtonsEnable(bool isEnable)
	{
	}

	private void UpdateInfo(JsonData response)
	{
	}
}

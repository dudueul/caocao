using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class FinishRaid : FinishBase
{
	private UIButton m_btnClose;

	private UIButton m_btnReplay;

	private UILabelLocalized m_labelReplayCost;

	private Raid m_raidData;

	[SerializeField]
	private Transform m_itemGridUI;

	public RaidBossState m_raidBossState;

	private void Start()
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

	[DebuggerHidden]
	private IEnumerator SendRaidBattleEnd(bool isWin)
	{
		return null;
	}

	private void UpdateRaidInfo(JsonData response)
	{
	}

	private void UpdateReplayButtonState()
	{
	}

	private void OnReplayClicked()
	{
	}

	private void RefreshSummonInBattle()
	{
	}

	private void SummonInBattle()
	{
	}

	protected override void OnFailedProcessReplay()
	{
	}

	protected override void RestartBattle()
	{
	}
}

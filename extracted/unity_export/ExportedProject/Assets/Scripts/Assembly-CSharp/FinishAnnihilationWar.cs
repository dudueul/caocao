using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class FinishAnnihilationWar : FinishBase
{
	private UIButton m_btnClose;

	[SerializeField]
	private Transform m_itemGridUI;

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

	[DebuggerHidden]
	private IEnumerator SendAnnihilationWarEnd(int point, bool isAttack, bool isWin)
	{
		return null;
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

	private void UpdateAnnihilationWarInfo(JsonData response)
	{
	}

	private string GetResultRankPercent(int curRank, int rankCnt)
	{
		return null;
	}

	public void OnBtnProfile()
	{
	}

	public void OnBtnUserInfo()
	{
	}
}

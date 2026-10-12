using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class FinishSham : FinishBase
{
	private UIButton m_btnClose;

	[SerializeField]
	private UILabelLocalized m_occupyPctUI;

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
	private IEnumerator SendShamBattleEnd(int townId, ulong opponentId, bool isWin, float occupationPct, int foodPlunder, int silverPlunder)
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

	private float GetOccupationPct()
	{
		return 0f;
	}

	private void UpdateShamPointInfo(int rank, double rankPct, int rankDiff, int point, int bonusPoint, int bonusPct)
	{
	}
}

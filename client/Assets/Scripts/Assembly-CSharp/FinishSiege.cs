using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class FinishSiege : FinishBase
{
	[SerializeField]
	private UILabelLocalized uiLabel_UserExp;

	[SerializeField]
	private UILabelLocalized uiLabel_Silver;

	[SerializeField]
	private UILabelLocalized uiLabel_Food;

	[SerializeField]
	private UILabelLocalized uiLabel_Stamp;

	[SerializeField]
	private UILabelLocalized uiLabel_Honer;

	private UIButton m_btnClose;

	[SerializeField]
	private Transform m_itemGridUI;

	[SerializeField]
	private UILabelLocalized m_occupyPctUI;

	private bool m_willShowPopup;

	private int m_tempMayorUnitId;

	private int m_tempMayorUnitTownId;

	private int m_tempMayorUnitBfIdx;

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

	private void CheckCommonMissionForDefaultUpgrade()
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

	public void UpdateGoods(int addSilver, int addFood, int addStamp, int addHoner, int addUserExp)
	{
	}

	private float GetOccupationPct()
	{
		return 0f;
	}

	[DebuggerHidden]
	private IEnumerator SendMultiBattleEnd(int townId, ulong opponentId, int mayorUnitId, bool isWin, float occupationPct, int rankID, string message)
	{
		return null;
	}
}

using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class FinishBattleField : FinishBase
{
	[SerializeField]
	private UILabelLocalized uiLabel_Gold;

	[SerializeField]
	private UILabelLocalized uiLabel_Silver;

	[SerializeField]
	private UILabelLocalized uiLabel_Food;

	private UIButton m_btnBFDefense;

	private UIButton m_btnClose;

	[SerializeField]
	private Transform m_itemGridUI;

	private bool m_willShowPopup;

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
	private IEnumerator SendBattleFieldBattleEnd(bool isWin)
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

	private void SetButtonsActive()
	{
	}

	public void UpdateGoods(int addGold, int addSilver, int addFood)
	{
	}
}

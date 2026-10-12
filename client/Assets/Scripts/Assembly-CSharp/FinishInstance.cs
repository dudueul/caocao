using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class FinishInstance : FinishBase
{
	[SerializeField]
	private UILabelLocalized uiLabel_UserExp;

	[SerializeField]
	private UILabelLocalized uiLabel_Silver;

	[SerializeField]
	private UILabelLocalized uiLabel_Food;

	[SerializeField]
	private UILabelLocalized uiLabel_Stamp;

	private UIButton m_btnReplay;

	private UIButton m_btnGoWorldMap;

	private UIButton m_btnClose;

	private Transform m_bonus;

	[SerializeField]
	private Transform m_itemGridUI;

	private List<GameObject> sealObjs;

	private bool m_wasOwnTerritory;

	private bool m_townLost;

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

	public void ShowVictoryGrade(int grade)
	{
	}

	public void UpdateChildUIStatus()
	{
	}

	public void UpdatePanelUI()
	{
	}

	[DebuggerHidden]
	private IEnumerator SendInstanceBattleEnd(int instanceId, int mayorUnitId, bool isWin, int stamp)
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

	private void ShowStampingEffect()
	{
	}

	public void UpdateGoods(int addSilver, int addFood, int addStamp, int addUserExp)
	{
	}
}

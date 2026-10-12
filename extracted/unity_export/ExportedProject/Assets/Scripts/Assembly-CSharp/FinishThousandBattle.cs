using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class FinishThousandBattle : FinishBase
{
	[SerializeField]
	private UILabelLocalized uiLabel_UserExp;

	[SerializeField]
	private UILabelLocalized uiLabel_Silver;

	[SerializeField]
	private UILabelLocalized uiLabel_Stamp;

	[SerializeField]
	private UILabelLocalized uiLabel_StageName;

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
	private IEnumerator SendThousandBatleEnd(bool isWin)
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

	public void UpdateGoods(int addSilver, int addStamp, int addUserExp)
	{
	}

	private void UpdateThousandBattleInfo(JsonData response)
	{
	}
}

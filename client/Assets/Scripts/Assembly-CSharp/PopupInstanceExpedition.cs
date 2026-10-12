using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class PopupInstanceExpedition : CaocaoUIModalPopup
{
	[SerializeField]
	private UILabelLocalized m_instanceCountLabel;

	[SerializeField]
	private UILabelLocalized m_remainTimeLabel;

	[SerializeField]
	private UIButton m_confirmBtn;

	[SerializeField]
	private UILabelLocalized m_confirmLabel;

	[SerializeField]
	private Transform[] m_unitUIs;

	private int m_beforeUserRank;

	private List<int> m_sendStates;

	private List<RewardInfo> m_rewardInfos;

	private List<ObscuredInt> m_clearInstances;

	private List<ObscuredInt> m_curClearInstances;

	private Action OnAutoClose;

	private TimeSpan m_remainTime;

	private EnumData.ExpeditionStateType m_expeditionStateType
	{
		get
		{
			return EnumData.ExpeditionStateType.None;
		}
	}

	public static void Show()
	{
	}

	public void UpdateStateUI()
	{
	}

	private void UpdateUnits()
	{
	}

	public void OnClickConform()
	{
	}

	private void CancelExpedition()
	{
	}

	private void EndExpedition()
	{
	}

	[DebuggerHidden]
	private IEnumerator SendRequestInstanceExpedition(float wait)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ProcessExpeditionPostProcess()
	{
		return null;
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}

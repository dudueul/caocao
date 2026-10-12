using System;
using System.Collections.Generic;
using UnityEngine;

public class PopupAutoClearInstanceReward : CaocaoUIModalPopup
{
	public UILabelLocalized titleLabel;

	public UIGrid uiGrid;

	private List<RewardInfo> m_rewards;

	private Action m_callback;

	private Transform m_bonus;

	public static void Show(List<RewardInfo> rewards, string title, bool isBonus, Action callback)
	{
	}

	private void UpdateUI()
	{
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

	public void OnClose()
	{
	}
}

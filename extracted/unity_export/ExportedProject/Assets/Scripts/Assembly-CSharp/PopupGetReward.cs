using System;
using System.Collections.Generic;

public class PopupGetReward : CaocaoUIModalPopup
{
	public UIGrid uiGrid;

	public UILabelLocalized labelDesc;

	private List<RewardInfo> m_rewards;

	private Action m_callback;

	public static void Show(List<RewardInfo> rewards, Action callback)
	{
	}

	public static void Show(List<RewardInfo> rewards, string desc, Action callback)
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
}

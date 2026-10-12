using System;
using System.Collections.Generic;

public class PopupTreasureSelectDirect : CaocaoUIModalPopup
{
	public UIGrid grid;

	private List<int> m_id;

	private Action onClose;

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
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

	public static void Show(int id, Action onClose)
	{
	}

	public static void Show(int[] ids, Action onClose)
	{
	}

	public static void Show(List<RewardInfo> rewards, Action onClose)
	{
	}

	private void UpdateUI()
	{
	}

	private void OnClickItem(ItemUI itemUI)
	{
	}
}

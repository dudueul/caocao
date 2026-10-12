using System;

public class PanelPopupNeedGold : PanelPopup2Buttons
{
	private int m_lackGold;

	private Action m_callback;

	public static bool CheckGold(int needGold)
	{
		return false;
	}

	public void Apply(int lackGold)
	{
	}

	protected override void OnSetContext()
	{
	}

	private PopupState ShowCashShopPopup()
	{
		return PopupState.KeepOpen;
	}
}

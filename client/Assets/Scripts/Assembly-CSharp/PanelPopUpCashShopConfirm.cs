using System;
using CaoCaoIAB;

public class PanelPopUpCashShopConfirm : CaocaoUIModalPopup
{
	public UITexture itemTexture;

	private UserSelectBox m_userSelectBox;

	private int productId;

	private int buyCount;

	private string price;

	public Action callbackFunc;

	private CashShopItemInfo itemInfo
	{
		set
		{
		}
	}

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

	private void UpdateUI()
	{
	}

	private void UpdateRibbon(CashShopItemInfo.SaleRibbon type)
	{
	}

	private void UpdatePrice(int type, string price)
	{
	}

	private void UpdateSaleInfo(int buyCount, int saleCount)
	{
	}

	private void UpdateSelectBoxRewards()
	{
	}

	public static void CreatePopup(CashShopItemInfo itemInfo, Action callback)
	{
	}

	public static void CreatePopup(int productId, int buyCount, string price, Action callback)
	{
	}

	public static void CreatePopup(GoodsShopItemInfo itemInfo, Action callback)
	{
	}

	public static void CreatePopup(UserSelectBox userSelectBox, SkuDetails skuDetails, Action callback)
	{
	}

	public void OnClickDetail()
	{
	}

	public void OnClickSpcCommTransAct()
	{
	}

	public void OnClickSettlementAct()
	{
	}

	public void OnClickCancel()
	{
	}

	public void OnClickConfirm()
	{
	}

	public void OnShowItemTooltip(ItemUI itemUI)
	{
	}
}

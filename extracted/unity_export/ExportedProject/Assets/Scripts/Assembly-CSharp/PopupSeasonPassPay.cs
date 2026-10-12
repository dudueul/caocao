using System;
using LitJson;

public class PopupSeasonPassPay : CaocaoUIModalPopup
{
	private SeasonPass pdSeasonPass;

	private CashShopItemInfo m_cashshopItemInfo;

	private Action lvUpCallback;

	private UILabelLocalized m_descEffect;

	private UILabelLocalized m_descPeriod;

	private UILabelLocalized m_price;

	private UILabelLocalized descEffect
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized descPeriod
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized price
	{
		get
		{
			return null;
		}
	}

	protected override void Awake()
	{
	}

	public static void Show(SeasonPass pdSeasonPass, Action lvUpCallback)
	{
	}

	public static void Refresh()
	{
	}

	public void OnShowTermsButton()
	{
	}

	public void OnClickSpcCommTransAct()
	{
	}

	public void OnClickSettlementAct()
	{
	}

	public void OnPurchaseButton()
	{
	}

	private void OnGetPurchaseInfoResponse(JsonData response)
	{
	}

	private void SettlementSuccess()
	{
	}

	private void UpdateUI()
	{
	}

	protected override void OnDestroy()
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

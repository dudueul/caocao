using UnityEngine;

public class TreasureBoxGridItem : MonoBehaviour
{
	public class PurchaseButton
	{
		private Transform m_transform;

		private UISprite m_Icon;

		private UILabelLocalized m_numberLabel;

		private UILabelLocalized m_textLabel;

		private UILabelLocalized m_priceLabel;

		private UIButton m_button;

		public PurchaseButton(Transform transform)
		{
		}

		public void UpdatePriceWithIcon(int buyCount, int buyPrice, string spriteName, EventDelegate.Callback onClickListener)
		{
		}

		public void UpdateTextWithBuyCount(int buyCount, string text, EventDelegate.Callback onClickListener)
		{
		}

		public void UpdateText(string text, EventDelegate.Callback onClickListener)
		{
		}

		public void SetActive(bool active)
		{
		}
	}

	public class BottomInfo
	{
		private Transform m_transform;

		private Transform m_goods;

		private Transform m_ticket;

		private UISprite m_ticketIcon;

		private UILabelLocalized m_ticketCount;

		private UILabelLocalized m_desc;

		public BottomInfo(Transform transform)
		{
		}

		public void SetActiveGoods(bool active)
		{
		}

		public void SetActive(bool active)
		{
		}

		public void SetActiveTicket(bool active)
		{
		}

		public void UpdateTicket(EnumData.ItemType iconType, int itemCount)
		{
		}

		public void UpdateTreasureBoxTicket(int itemCount)
		{
		}

		public void SetActiveDesc(bool active)
		{
		}

		public void UpdateDesc(string key)
		{
		}

		public void UpdateDesc(string key, params object[] args)
		{
		}
	}

	private TreasureBoxItemInfo m_treasureBoxInfo;

	private static string ms_cooltimeFormat;

	private static string ms_termtimeFormat;

	private static string ms_vipLockColorTag;

	private static string ms_whiteColorTag;

	public PurchaseButton purchaseCenter { get; private set; }

	public PurchaseButton purchaseTop { get; private set; }

	public PurchaseButton purchaseBottom { get; private set; }

	public BottomInfo bottomInfo { get; private set; }

	public UILabelLocalized coolTime { get; private set; }

	public Transform exButton { get; private set; }

	public UILabelLocalized tooltipDesc { get; private set; }

	public UILabelLocalized productName { get; private set; }

	public UITexture productImage { get; private set; }

	public Transform dim { get; private set; }

	public int order
	{
		get
		{
			return 0;
		}
	}

	public bool IsSame(TreasureBoxItemInfo boxInfo)
	{
		return false;
	}

	public void UpdateUI(TreasureBoxItemInfo boxInfo)
	{
	}

	private void UpdateCoolTime()
	{
	}

	private void UpdateLimitTime()
	{
	}

	private void SetPurchaseButton(TreasureBoxItemInfo boxInfo)
	{
	}

	private TreasurePurchasePopup.PopupMode GetMode(TreasureBoxItemInfo boxInfo, out int costIdx, int prevCostIdx = -1)
	{
		costIdx = default(int);
		return TreasurePurchasePopup.PopupMode.Free;
	}

	private void SetSingleButton(TreasureBoxItemInfo boxInfo, int costIdx, TreasurePurchasePopup.PopupMode mode)
	{
	}

	private void SetDoubleButton(TreasureBoxItemInfo boxInfo, int costIdx1, int costIdx2, TreasurePurchasePopup.PopupMode mode1, TreasurePurchasePopup.PopupMode mode2)
	{
	}

	private void _setButton(PurchaseButton button, TreasureBoxItemInfo boxInfo, int costIdx, TreasurePurchasePopup.PopupMode mode)
	{
	}

	private void SetSingleButtonForm(TreasureBoxItemInfo boxInfo)
	{
	}

	private void SetDoubleButtonForm(TreasureBoxItemInfo boxInfo)
	{
	}

	private void SetVipCondition(BoxShop boxPd)
	{
	}

	private void SetAdvantageGuide(BoxShop boxPd)
	{
	}

	private void SetMileage(BoxShop boxPd)
	{
	}

	private void SetPackageGuide()
	{
	}

	private void UpdateTooltipDescription()
	{
	}

	private void OnTreasureBoxPurchaseClicked(TreasurePurchasePopup.PopupMode mode, int costIdx)
	{
	}

	private void UpdateLastTermtime()
	{
	}

	private void UpdateLastCooltime()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}
}

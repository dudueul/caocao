using System;
using UnityEngine;

public class CashShopGridItem : MonoBehaviour
{
	public class PurchaseButton
	{
		private Transform m_transform;

		private UISprite m_Icon;

		private UILabelLocalized m_numberLabel;

		private UILabelLocalized m_textLabel;

		private UILabelLocalized m_priceLabel;

		private UILabelLocalized m_priceOnlyLabel;

		private UIButton m_button;

		public PurchaseButton(Transform transform)
		{
		}

		public void Destroy()
		{
		}

		public void PurchaseButtonAllDisable()
		{
		}

		public void UpdatePriceWithIcon(int buyPrice, string spriteName, EventDelegate.Callback onClickListener)
		{
		}

		public void UpdatePriceWithIcon(int buyCount, int buyPrice, string spriteName, EventDelegate.Callback onClickListener)
		{
		}

		public void UpdatePriceWithIcon(int buyCount, string text, string spriteName, EventDelegate.Callback onClickListener, bool turnOnIcon = true)
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

		public void SetState(UIButtonColor.State state)
		{
		}

		public void SetClickable(bool clickable)
		{
		}
	}

	public class BottomInfo
	{
		private Transform m_transform;

		private Transform m_ticket;

		private UISprite m_ticketIcon;

		private UILabelLocalized m_ticketCount;

		private Transform m_goods;

		private UISprite m_goodsIcon;

		private UISprite m_goodsTicketIcon;

		private UILabelLocalized m_goodsValue;

		private UILabelLocalized m_desc;

		public BottomInfo(Transform transform)
		{
		}

		public void BottomInfoAllDisable()
		{
		}

		public void SetActiveTicket(bool active)
		{
		}

		public void SetActiveGoods(bool active)
		{
		}

		public void SetActiveDesc(bool active)
		{
		}

		public void UpdateGoods(string goodsValue, string goodsIcon = "")
		{
		}

		public void UpdateTicket(EnumData.ItemType iconType, int itemId, int itemCount)
		{
		}

		public void UpdateDesc(string key)
		{
		}

		public void UpdateDesc(string key, params object[] args)
		{
		}
	}

	public class TopInfo
	{
		private Transform m_transform;

		private Transform m_ticket;

		private UISprite m_ticketIcon;

		private UILabelLocalized m_ticketCount;

		private Transform m_comment;

		private Transform m_bonus;

		private Transform m_bonusGauge;

		private Transform m_bonusGaugeValue;

		private UILabelLocalized m_bonusGaugeDesc;

		private UILabelLocalized m_desc;

		public TopInfo(Transform transform)
		{
		}

		public void TopInfoAllDisable()
		{
		}

		public void SetActiveDesc(bool active)
		{
		}

		public void SetDesc(Color color, string format, params object[] args)
		{
		}

		public bool IsSalesLimit(UserLinkedGoodsNode userLinkedGoodsNode)
		{
			return false;
		}

		public bool IsSalesLimit(GoodsShopItemInfo shopInfo)
		{
			return false;
		}

		public bool IsSalesLimit(CashShopItemInfo shopInfo)
		{
			return false;
		}

		public void SetAdvantageGuide(BoxShop boxPd)
		{
		}

		public void SetAdvantageGuide(RuneBoxShop boxPd)
		{
		}

		private void SetPackageGuide()
		{
		}

		private void SetMileage(BoxShop boxPd)
		{
		}

		private void SetMileage(RuneBoxShop boxPd)
		{
		}
	}

	public class IconInfo
	{
		private const int ItemIconCount = 4;

		private Transform m_transform;

		private UITexture m_Icon;

		private Transform m_item;

		private Transform m_saleRibbon;

		private UILabelLocalized m_saleRibbonLabel;

		private Transform[] m_instItems;

		private Transform m_itemType;

		private UITexture m_itemTypeIcon;

		public IconInfo(Transform transform)
		{
		}

		public void IconInfoAllDisable()
		{
		}

		public void SetMainTexture(string iconName)
		{
		}

		public void SetSaleRibbon(int ribbon)
		{
		}

		public void SetSaleRibbon(CashShopItemInfo.SaleRibbon ribbon)
		{
		}

		public void SetRewardItems(int rewardId)
		{
		}

		public void SetTypeIconTexture(string iconName)
		{
		}

		public void OnShowItemTooltip(ItemUI itemUI)
		{
		}
	}

	public class TooltipInfo
	{
		private Transform m_transform;

		private UILabelLocalized m_desc;

		private UIButton m_btnInfo;

		private BoxCollider m_exButtonCollider;

		private UIScrollView m_scrollView;

		public TooltipInfo(Transform transform, Transform exButton)
		{
		}

		public void Destroy()
		{
		}

		public void UpdateInfo(CashShopItemInfo info)
		{
		}

		public void UpdateInfo(GoodsShopItemInfo info)
		{
		}

		public void UpdateInfo(TreasureBoxItemInfo info)
		{
		}

		public void UpdateInfo(RuneBoxItemInfo info)
		{
		}

		public void ResetPositionScrollView()
		{
		}
	}

	private CashShopItemInfo m_cashShopInfo;

	private TreasureBoxItemInfo m_treasureBoxInfo;

	private GoodsShopItemInfo m_goodsShopInfo;

	private RuneBoxItemInfo m_runeBoxInfo;

	private LinkedGoods m_linkedGoodsPd;

	private UserLinkedGoodsNode m_userLinkedGoodsNode;

	private bool isInit;

	private PanelPopupBaseShop.TabMenu m_tabMenu;

	private static string ms_cooltimeFormat;

	private static string ms_termtimeFormat;

	private static string ms_vipLockColorTag;

	private static string ms_whiteColorTag;

	private static string ms_remainTimeUnitFormat;

	public PurchaseButton purchaseCenter { get; private set; }

	public PurchaseButton purchaseTop { get; private set; }

	public PurchaseButton purchaseBottom { get; private set; }

	public BottomInfo bottomInfo { get; private set; }

	public TopInfo topInfo { get; private set; }

	public IconInfo iconInfo { get; private set; }

	public UILabelLocalized coolTime { get; private set; }

	public Transform exButton { get; private set; }

	public TooltipInfo tooltipInfo { get; private set; }

	public UILabelLocalized productName { get; private set; }

	public Transform dim { get; private set; }

	public string productId
	{
		get
		{
			return null;
		}
	}

	public bool IsSame(TreasureBoxItemInfo boxInfo)
	{
		return false;
	}

	public bool IsSame(CashShopItemInfo itemInfo)
	{
		return false;
	}

	public bool IsSame(GoodsShopItemInfo goodsShopInfo)
	{
		return false;
	}

	private void SetInfo(CashShopItemInfo cashInfo, GoodsShopItemInfo goodsInfo, TreasureBoxItemInfo treasureBoxInfo, RuneBoxItemInfo runeBoxItemInfo, UserLinkedGoodsNode userLinkedGoodsNode, LinkedGoods linkedGoodsPd)
	{
	}

	public void UpdateUI(PanelPopupBaseShop.TabMenu tabMenu, TreasureBoxItemInfo treasureBoxInfo)
	{
	}

	public void UpdateUI(PanelPopupBaseShop.TabMenu tabMenu, RuneBoxItemInfo runeBoxInfo)
	{
	}

	public void UpdateUI(PanelPopupBaseShop.TabMenu tabMenu, CashShopItemInfo itemInfo, UserLinkedGoodsNode userLinkedNode, LinkedGoods linkedGoodsPd)
	{
	}

	public void UpdateUI(PanelPopupBaseShop.TabMenu tabMenu, GoodsShopItemInfo itemInfo, UserLinkedGoodsNode userLinkedNode, LinkedGoods linkedGoodsPd)
	{
	}

	public void UpdateUI(PanelPopupBaseShop.TabMenu tabMenu, CashShopItemInfo itemInfo, RecommendShopItemInfo recommendShopItemInfo = null)
	{
	}

	public void UpdateUI(PanelPopupBaseShop.TabMenu tabMenu, GoodsShopItemInfo itemInfo, RecommendShopItemInfo recommendShopItemInfo = null)
	{
	}

	private string GetGoodsIcon(EnumData.GoodsType goodsType)
	{
		return null;
	}

	private void UpdateCoolTime()
	{
	}

	private void UpdateLimitTime()
	{
	}

	private void SetPurchaseButton(CashShopItemInfo shopInfo)
	{
	}

	private void SetPurchaseButton(GoodsShopItemInfo shopInfo)
	{
	}

	private void SetPurchaseButton(CashShopItemInfo shopInfo, UserLinkedGoodsNode userLinkedGoodsNode)
	{
	}

	private void SetPurchaseButton(GoodsShopItemInfo shopInfo, UserLinkedGoodsNode userLinkedGoodsNode)
	{
	}

	private void SetPurchaseButton(TreasureBoxItemInfo boxInfo)
	{
	}

	private void SetPurchaseButton(RuneBoxItemInfo runeBoxItemInfo)
	{
	}

	private TreasurePurchasePopup.PopupMode GetMode(TreasureBoxItemInfo boxInfo, out int costIdx, int prevCostIdx = -1)
	{
		costIdx = default(int);
		return TreasurePurchasePopup.PopupMode.Free;
	}

	private void SetAllButtonDisable()
	{
	}

	private void SetSingleButton(GoodsShopItemInfo shopInfo)
	{
	}

	private void SetSingleButton(CashShopItemInfo shopInfo)
	{
	}

	private void SetSingleButton(CashShopItemInfo shopInfo, UserLinkedGoodsNode userLinkedGoodsNode)
	{
	}

	private void SetSingleButton(GoodsShopItemInfo shopInfo, UserLinkedGoodsNode userLinkedGoodsNode)
	{
	}

	private void SetSingleButton(TreasureBoxItemInfo boxInfo, int costIdx, TreasurePurchasePopup.PopupMode mode)
	{
	}

	private void SetDoubleButton(TreasureBoxItemInfo boxInfo, int costIdx1, int costIdx2, TreasurePurchasePopup.PopupMode mode1, TreasurePurchasePopup.PopupMode mode2)
	{
	}

	private void SetDoubleButton(RuneBoxItemInfo boxInfo, int count1, int count2)
	{
	}

	private void _setButton(PurchaseButton button, CashShopItemInfo shopInfo)
	{
	}

	private void _setButton(PurchaseButton button, GoodsShopItemInfo shopInfo)
	{
	}

	private void _setButton(PurchaseButton button, CashShopItemInfo cashshopInfo, GoodsShopItemInfo goodsshopInfo, UserLinkedGoodsNode userLinkedGoodsNode)
	{
	}

	private void _setButton(PurchaseButton button, TreasureBoxItemInfo boxInfo, int costIdx, TreasurePurchasePopup.PopupMode mode, bool priceOnly)
	{
	}

	private void _setButton(PurchaseButton button, RuneBoxItemInfo runeBoxItemInfo, int count)
	{
	}

	private void SetSingleButtonForm(GoodsShopItemInfo shopInfo)
	{
	}

	private void SetSingleButtonForm(CashShopItemInfo shopInfo)
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

	private void OnTreasureBoxPurchaseClicked(TreasurePurchasePopup.PopupMode mode, int costIdx)
	{
	}

	private void OnRuneBoxPurchaseClicked(int buyCount)
	{
	}

	private void OnLinkedGoodsPurchaseClick(bool precedingCondition)
	{
	}

	private void UpdateLastTermtime()
	{
	}

	private void UpdateLastCooltime()
	{
	}

	private void UpdateTimeTag()
	{
	}

	private TimeSpan UpdateRemainingTimeGoodsShop(TimeSpan remainTime)
	{
		return default(TimeSpan);
	}

	private TimeSpan UpdateRemainingTimeCashShop(TimeSpan remainTime)
	{
		return default(TimeSpan);
	}

	private void UpdateRemainingTime()
	{
	}

	private void OnGoodsShopPurchaseClicked()
	{
	}

	private void OnPurchaseClicked()
	{
	}

	private void OnConfirm()
	{
	}

	private void SettlementSuccess()
	{
	}

	private void OnProtectPurchaseClicked()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	private void AllDisable()
	{
	}

	private void Init(PanelPopupBaseShop.TabMenu tabMenu)
	{
	}
}

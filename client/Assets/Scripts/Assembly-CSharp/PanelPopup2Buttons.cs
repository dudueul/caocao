using System;
using System.Collections.Generic;
using UnityEngine;

public class PanelPopup2Buttons : PanelPopupBase
{
	private Func<PopupState> OnYes__BackingField;

	private Func<PopupState> OnPurchase__BackingField;

	private Func<PopupState> OnCancel__BackingField;

	private Func<PopupState> OnConfirm__BackingField;

	private Func<PopupState> OnChangeValue__BackingField;

	private Action<int> OnAddTurnChangeSelection__BackingField;

	private Action<string> OnNationNameChange__BackingField;

	private Action<string> OnNationNameSubmit__BackingField;

	private Action<int> OnSlider__BackingField;

	private UILabelLocalized labelBtnPurchase;

	private const float SLIDER_SOUND_GAP = 0.05f;

	private float lastSliderSoundTime;

	private UISlider slider;

	private UILabelLocalized sliderLabelMin;

	private UILabelLocalized sliderLabelMax;

	private UILabelLocalized sliderLabelCur;

	private int sliderMinVal;

	private int sliderMaxVal;

	private int sliderCurVal;

	private int sliderEachVal;

	private int sliderLimitVal;

	private int prevSliderValue;

	private DateTime expireTime;

	private static readonly string[] ms_turnAddPatterns;

	private string remainTimeTextFormat;

	private bool timePaused;

	public Action onSetContext;

	protected GameObject titleLabel
	{
		get
		{
			return null;
		}
	}

	protected GameObject centerTextOneLine
	{
		get
		{
			return null;
		}
	}

	protected GameObject centerTextTwoLine
	{
		get
		{
			return null;
		}
	}

	protected GameObject centerTextFourLine
	{
		get
		{
			return null;
		}
	}

	protected GameObject centerTextNoTitle
	{
		get
		{
			return null;
		}
	}

	protected GameObject resource1
	{
		get
		{
			return null;
		}
	}

	protected GameObject resource2
	{
		get
		{
			return null;
		}
	}

	protected GameObject nationName
	{
		get
		{
			return null;
		}
	}

	protected GameObject turn
	{
		get
		{
			return null;
		}
	}

	protected GameObject ticket
	{
		get
		{
			return null;
		}
	}

	protected GameObject itemInfo
	{
		get
		{
			return null;
		}
	}

	protected GameObject resourcesList
	{
		get
		{
			return null;
		}
	}

	protected GameObject itemResource1
	{
		get
		{
			return null;
		}
	}

	protected GameObject itemResource2
	{
		get
		{
			return null;
		}
	}

	protected GameObject itemResource3
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnYes
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnPurchase
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnPurchase2
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnCancel
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnConfirmOneBtn
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnLackGoodsPurchase
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnLackGoodsCancel
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnTicket
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnSliderPrevBtn
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnSliderNextBtn
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnYesReverse
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnCancelReverse
	{
		get
		{
			return null;
		}
	}

	protected GameObject _1btn
	{
		get
		{
			return null;
		}
	}

	protected GameObject _2btn
	{
		get
		{
			return null;
		}
	}

	protected GameObject _2btnReverse
	{
		get
		{
			return null;
		}
	}

	protected GameObject turnAdd1
	{
		get
		{
			return null;
		}
	}

	protected GameObject turnAdd2
	{
		get
		{
			return null;
		}
	}

	protected GameObject turnAdd3
	{
		get
		{
			return null;
		}
	}

	protected GameObject turnAdd5
	{
		get
		{
			return null;
		}
	}

	protected GameObject blackBG
	{
		get
		{
			return null;
		}
	}

	protected GameObject guildCreate
	{
		get
		{
			return null;
		}
	}

	protected GameObject guildName
	{
		get
		{
			return null;
		}
	}

	protected GameObject guildMark
	{
		get
		{
			return null;
		}
	}

	public string title
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string yes
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Tuple<string, EnumData.GoodsType, int> purchase
	{
		set
		{
		}
	}

	public Tuple<EnumData.GoodsType, int> purchase2
	{
		set
		{
		}
	}

	public Tuple<string, int, int> purchaseByToken
	{
		set
		{
		}
	}

	public Tuple<string, EnumData.GoodsType, int> lackGoodsPurchase
	{
		set
		{
		}
	}

	public Tuple<string, EnumData.GoodsType, int> sell
	{
		set
		{
		}
	}

	public int ticketBtn
	{
		set
		{
		}
	}

	public string cancel
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string confirm
	{
		set
		{
		}
	}

	public string yesReverse
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string cancelReverse
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public event Func<PopupState> OnYes
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Func<PopupState> OnPurchase
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Func<PopupState> OnCancel
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Func<PopupState> OnConfirm
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Func<PopupState> OnChangeValue
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<int> OnAddTurnChangeSelection
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<string> OnNationNameChange
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<string> OnNationNameSubmit
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<int> OnSlider
	{
		add
		{
		}
		remove
		{
		}
	}

	public static PanelPopup2Buttons CreateGoodsShopPopup(GoodsShopItemInfo itemInfo)
	{
		return null;
	}

	private static bool CheckGoods(GoodsShop pdData)
	{
		return false;
	}

	private static void ShowNeedGoldPopup()
	{
	}

	public static PanelPopup2Buttons CreateGFCheckMinor(GameFlowNodeBase popupCaller)
	{
		return null;
	}

	public static PanelPopup2Buttons CreateGuildConfirmPopup(string guildName, Texture markTexture, Action onPurchase)
	{
		return null;
	}

	public static PanelPopup2Buttons CreateGuildMarkConfirmPopup(Action onPurchase)
	{
		return null;
	}

	public static PanelPopup2Buttons CreateUseSubscriptionItemPopup(ItemLocker locker, UserItem userItem, Action onUse)
	{
		return null;
	}

	public static PanelPopup2Buttons CreateInstance()
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

	public void SetTitleContext(string key, params object[] args)
	{
	}

	public void SetLabel1(string key, params object[] args)
	{
	}

	public void SetLabel2(string key, params object[] args)
	{
	}

	public void SetLabel3(string key, params object[] args)
	{
	}

	public void SetLabel4(string key, params object[] args)
	{
	}

	public void SetSubLabel(string key, params object[] args)
	{
	}

	public void SetNoTitleLabel(string key, params object[] args)
	{
	}

	public void SetTicket(int count)
	{
	}

	public void SetGuild(string guildName, Texture markTexture, int guildWarRank)
	{
	}

	public void SetYesLabel(string key, params object[] args)
	{
	}

	public void UpdatePurchase2(EnumData.GoodsType goodsType, int amount)
	{
	}

	public void SetResource(EnumData.GoodsType type, int amount, bool lackResource = false)
	{
	}

	public void SetResource(EnumData.GoodsType type0, int amount0, EnumData.GoodsType type1, int amount1, bool lackResource = false)
	{
	}

	public void SetResourcePassivePoint(int amount, bool lackResource = false)
	{
	}

	public void SetStoreRefreshText(int todayCount, int maxCount)
	{
	}

	public void SetRemainedTime(DateTime expireTime, string body, string timeTextFormat = "")
	{
	}

	public void SetSelectBoxRefreshText(int refreshCount, int maxCount)
	{
	}

	public void SetFreeAutoClearCenterText(int usedFreeCount, int availableFreeCount)
	{
	}

	public void SetTicketAutoClearCenterText(int itemCount)
	{
	}

	public void SetTicketWorldbossCenterText(int participationCount)
	{
	}

	public void SetResetUnitTraining(Item item, int amount, int cost)
	{
	}

	public void SetTreasureSocketPassive(int amount, int itemId, string text, params string[] textArgs)
	{
	}

	private void SetSubscriptionData()
	{
	}

	public void SetStateAutoClearCenterText(int itemCount, int freeCount, int clearCount)
	{
	}

	public void SetSubscriptionRemainTime(Item itemInfo)
	{
	}

	public void SetNationName(string nationNameStr)
	{
	}

	public void SetTurn(int cost1, int cost2, int cost3, int cost5)
	{
	}

	public void SetItemReinforce(UserItem userItem)
	{
	}

	protected void ReinforceGaugeEffect(int to, Action callback)
	{
	}

	protected void SetReinforceGauge(int value)
	{
	}

	protected void UpdateReinforceItemInfo(UserItem userItem)
	{
	}

	public void SetEnablePurchaseBtn(bool enable)
	{
	}

	public void SetUnitLevPractice(UserUnit userUnit)
	{
	}

	protected void UpdatePracticeUnitLevInfo(UserUnit userUnit)
	{
	}

	protected int GetPracticeUnitLevNeedCost(UserUnit userUnit)
	{
		return 0;
	}

	public void SetCraftAllInfo(Item item, int amount, int cost, Dictionary<int, int> items, bool lackResource = false)
	{
	}

	public void SetItemInfo(Item item, int amount, int cost, bool lackResource = false)
	{
	}

	public void SetItemList(Dictionary<int, int> items)
	{
	}

	public void SetItemResource(Item item, int cost, bool lackResource = false)
	{
	}

	public void SetItemResource(Item item0, int cost0, Item item1, int cost1, bool lackResource = false)
	{
	}

	public void SetItemResource3(Item item, int cost, bool lackResource = false, Action<ItemUI> action = null)
	{
	}

	public void SetSlider(int min, int max, int cur, int each, int limit, string key, params object[] args)
	{
	}

	private void SetPressedBtns(Transform tr, Action<int> callback)
	{
	}

	public void OnSliderPrevButton(int count)
	{
	}

	public void OnSliderNextButton(int count)
	{
	}

	private void OnUpdateSliderControl()
	{
	}

	private void UpdateSlider()
	{
	}

	private void UpdateSliderUi()
	{
	}

	public int GetSliderValue()
	{
		return 0;
	}

	protected void SetSlotAvailable()
	{
	}

	private void SetParentUIRoot()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected virtual void OnSetContext()
	{
	}

	public void OnBtnClick()
	{
	}

	public void OnSelect(bool selected)
	{
	}

	public void OnInputChange()
	{
	}

	public void OnInputSubmit()
	{
	}

	private void OnAddTurnSelected(GameObject buttonObject)
	{
	}

	private void OnUpdateRemainedTime()
	{
	}

	protected override void OnDestroy()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void FocusNationName(bool focus)
	{
	}

	public void ClickYes()
	{
	}

	public void SetAutoInstanceStart(bool isBtnAutoMoveStateEnabled)
	{
	}

	public void OnAutoRecovery()
	{
	}

	public void OnAutoFast()
	{
	}

	public void OnEXPBoost()
	{
	}

	public void OnAutoMoveState()
	{
	}

	private string GetMoveStateString(EnumData.AutoMoveStateType type)
	{
		return null;
	}

	public static PanelPopup2Buttons CreateAICancel(Action turnOffAI = null)
	{
		return null;
	}

	public static PanelPopup2Buttons CreateBuyProtects(CashShopProtectInfo protectInfo)
	{
		return null;
	}

	private static bool CheckGoods(int amount)
	{
		return false;
	}

	private static void ShowNeedGold()
	{
	}

	public static PanelPopup2Buttons CreateSelectItemPopup(int itemId, Action onYesCallback)
	{
		return null;
	}

	private void OnClickItem(ItemUI itemUI)
	{
	}

	public static PanelPopup2Buttons StorBuySellPopup(EnumData.ShopMode shopMode, int totalPrice, Action<List<StoreItem>, List<int>> onPurchaseCallback, List<StoreItem> buyItems, List<int> buyAmounts)
	{
		return null;
	}
}

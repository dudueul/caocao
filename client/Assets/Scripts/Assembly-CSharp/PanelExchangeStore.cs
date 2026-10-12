using System.Collections.Generic;
using ExchangeStore;
using LitJson;

public class PanelExchangeStore : PanelSelfRegister2UIComponentsMonobehaviour
{
	public CaocaoTabButtons tabButtons;

	protected LeftBox m_leftBox;

	protected RightBox m_rightBox;

	protected BtnClose m_btnClose;

	protected BtnPurchase m_btnPurchase;

	private ExchangeShop m_currentShop;

	private List<ExchangeShop> m_currentShopList;

	private readonly ExchangeShopComparer exchangeComparer;

	private static PanelExchangeStore self;

	private ExchangeStoreHistorys m_exchangeStoreHistorys;

	public ExchangeShop currentShop
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public static bool InProgressEvent
	{
		get
		{
			return false;
		}
	}

	public static void Show()
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	protected override void Awake()
	{
	}

	public void OnEnable()
	{
	}

	public void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnFilterChanged(EnumData.ItemFilterType filterType)
	{
	}

	private void OnSelectStoreItem(StoreItem storeItem)
	{
	}

	private void OnCloseButtonClicked()
	{
	}

	private void ShowSoldoutPopup()
	{
	}

	private void OnPurchaseButtonClicked()
	{
	}

	private void DoShortCut(Shortcut shortCut)
	{
	}

	private bool CanPurchase(Item costItem, int cost)
	{
		return false;
	}

	private void OnPurchased(JsonData response)
	{
	}

	public static List<ExchangeShop> GetActivateShopList()
	{
		return null;
	}

	private void UpdateUI()
	{
	}

	private void CloseShop()
	{
	}

	private void UpdateShopData(ExchangeShop exchangeShop, List<EnumData.ItemSlotType> itemSlotTypes)
	{
	}

	private string GetAlarmText(int alaramRibbon)
	{
		return null;
	}

	public void OnTabChanged(CaocaoButton button)
	{
	}

	private void RequestExchangeShopHistory()
	{
	}
}

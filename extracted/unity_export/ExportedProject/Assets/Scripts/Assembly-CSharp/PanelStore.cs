using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PanelStore : PanelSelfRegister2UIComponentsMonobehaviour
{
	private Transform m_leftBox;

	private GameObject m_topBG;

	private UIReuseGrid m_itemGrid;

	private UIReuseScrollView m_gridScrollView;

	private FilterItemType m_filterItemType;

	private GoodsUI m_guildPointUI;

	private GameObject m_materialItemUI;

	private StoreOrderActivateTabBtn m_orderActivateTabBtn;

	private Transform m_rightBox;

	private TooltipContentsGenerator m_itemInfo;

	private StoreItemNumControl m_itemNumControl;

	public Transform block;

	public UILabelLocalized blockDesc1;

	public UILabelLocalized blockDesc2;

	private UIButton m_btnClose;

	private UILabelLocalized m_title;

	private StoreBtnPurchase m_btnPurchase;

	private StoreBtnSell m_btnSell;

	private GameObject m_exMark;

	private GameObject m_exMarkGuild;

	public Action<EnumData.ShopMode> OnShopModeChanged;

	private EnumData.ShopMode m_currentTab;

	private Action<StoreItem> OnStoreItemUISelected;

	private StoreItem m_selectedStoreItem;

	private StoreBottomInfo m_storeBottomInfo;

	public CaocaoTabButtons tabButtons;

	public static Dictionary<EnumData.ShopMode, List<StoreItem>> shopMode2ItemList;

	private int prevMarketTechLv;

	private List<StoreItem> boughtItems;

	private List<int> boughtItemAmounts;

	private PanelPopup2Buttons storePopup;

	public static PanelStore self;

	public static Action OnShowPanel;

	public static Action OnHidePanel;

	public Transform leftBox
	{
		get
		{
			return null;
		}
	}

	public GameObject topBG
	{
		get
		{
			return null;
		}
	}

	public UIReuseGrid itemGrid
	{
		get
		{
			return null;
		}
	}

	public UIReuseScrollView gridScrollView
	{
		get
		{
			return null;
		}
	}

	public FilterItemType filterItemType
	{
		get
		{
			return null;
		}
	}

	public GoodsUI guildPointUI
	{
		get
		{
			return null;
		}
	}

	public GameObject materialItemUI
	{
		get
		{
			return null;
		}
	}

	public StoreOrderActivateTabBtn orderActivateTabBtn
	{
		get
		{
			return null;
		}
	}

	public Transform rightBox
	{
		get
		{
			return null;
		}
	}

	public TooltipContentsGenerator itemInfo
	{
		get
		{
			return null;
		}
	}

	public StoreItemNumControl itemNumControl
	{
		get
		{
			return null;
		}
	}

	public UIButton btnClose
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized title
	{
		get
		{
			return null;
		}
	}

	public StoreBtnPurchase btnPurchase
	{
		get
		{
			return null;
		}
	}

	public StoreBtnSell btnSell
	{
		get
		{
			return null;
		}
	}

	public GameObject exMark
	{
		get
		{
			return null;
		}
	}

	public GameObject exMarkGuild
	{
		get
		{
			return null;
		}
	}

	public EnumData.ShopMode currentTab
	{
		get
		{
			return EnumData.ShopMode.MARKET;
		}
		set
		{
		}
	}

	private StoreItem selectedStoreItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public StoreBottomInfo storeBottomInfo
	{
		get
		{
			return null;
		}
	}

	public static List<StoreItem> GetItemListByShopMode(EnumData.ShopMode shopMode)
	{
		return null;
	}

	protected override void Awake()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	private void OnItemFilterUpdated(EnumData.ItemFilterType filter)
	{
	}

	private void OnItemShopModeChanged(EnumData.ShopMode shopMode)
	{
	}

	private void OnUpdateTech()
	{
	}

	private void OnPurchaseBtnClicked()
	{
	}

	private void OnGuildPurchaseBtnClicked()
	{
	}

	private void OnPurchased(JsonData response)
	{
	}

	private void OnGuildPurchased(JsonData jsonData)
	{
	}

	private void OnPurchasedMaterialStore(JsonData response)
	{
	}

	protected void SetUsingGoodsType(EnumData.ShopMode shopMode)
	{
	}

	private void OnSellBtnClicked()
	{
	}

	private void ProcessSell(List<StoreItem> sellItem, List<int> sellAmounts)
	{
	}

	private void OnSold(JsonData response)
	{
	}

	private void OnRefreshBtnClicked()
	{
	}

	private void ProcessRefresh()
	{
	}

	private void OnRefresh(JsonData response)
	{
	}

	private void UpdateItemList(EnumData.ShopMode shopMode, List<EnumData.ItemSlotType> slotTypes, bool resetPosition = true)
	{
	}

	private void RemoveAllItems()
	{
	}

	private void GetItemListFromServer(EnumData.ShopMode shopMode)
	{
	}

	private void OnRefreshItemList(JsonData response)
	{
	}

	private void OnUpdateGuildPoint()
	{
	}

	private void OnUpdateGuildStoreHistory()
	{
	}

	private void OnGuildError(string error, ref bool handled)
	{
	}

	protected virtual void OnGetItemListFromServer(JsonData response)
	{
	}

	private List<StoreItem> GetStoreSellItemList(EnumData.ShopMode shopMode)
	{
		return null;
	}

	private void UpdateMaterialShopItemList()
	{
	}

	private void SetTimer()
	{
	}

	private void OnTick()
	{
	}

	private void ItemSelected(StoreItem storeItem)
	{
	}

	public static void Show()
	{
	}

	public static void Show(EnumData.ShopMode shopMode)
	{
	}

	public static void SetGuildShop(bool guildShop, bool clearFilterTypes = false)
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	private void UpdateGuildItemList(int guildLv)
	{
	}

	public static void Hide()
	{
	}

	public void SetMaterialTicketInfo()
	{
	}

	private static string GetButtonName(EnumData.ShopMode shopMode)
	{
		return null;
	}

	private void UpdateStoreItemDeserializeCallback(bool selected)
	{
	}

	private void OnUpdatedItem()
	{
	}

	private void OnUpdatedItems()
	{
	}

	private void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}
}

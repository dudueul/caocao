using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class PanelTreasureCraft : CaocaoUIPanel
{
	public enum TreasureCraftType
	{
		Craft = 0,
		Compose = 1,
		Change = 2,
		Piece = 3,
		Enhance = 4,
		PassiveChange = 5,
		PassiveChangeFixed = 6,
		TreasureCreate = 7,
		TreasureSocketPassive = 8
	}

	public enum TreasureChangeDetailType
	{
		Change = 0,
		Sell = 1
	}

	protected class PassiveChangeContainer
	{
		public int selectedPassive;

		public UILabelLocalized selectedName;

		public ItemUI treasure;

		public ulong srl;

		public GameObject selectedEffect;

		public EnhancePassiveUI passive1UI;

		public EnhancePassiveUI passive2UI;

		public GameObject passive1Checkbox;

		public GameObject passive2Checkbox;

		public UILabelLocalized passive1Name;

		public UILabelLocalized passive2Name;

		public UILabelLocalized passive1Condition;

		public UILabelLocalized passive2Condition;

		public string GetSelectedPassiveName()
		{
			return null;
		}

		public int GetCost()
		{
			return 0;
		}

		public int GetCostTicket()
		{
			return 0;
		}

		public void UpdatePassive(UserItem userItem, int index)
		{
		}

		private void UpdateUI(UserItem userItem, int index, EnhancePassiveUI ui, UILabelLocalized nameLabel, UILabelLocalized conditionLabel)
		{
		}

		public void Clear()
		{
		}
	}

	private TreasureMix m_treasureMixForCraft;

	private TreasureMix m_treasureMixForCompose;

	private TreasureChange m_treasureChange;

	private TreasureMix m_treasureMixForExchange;

	private TreasureSale m_treasureSale;

	private TreasureChangeDetailType m_changeDetailType;

	private TreasureCraftType m_craftType;

	private ObscuredInt m_maxTier;

	private ObscuredInt m_composeTier;

	private ObscuredInt m_changeTier;

	private ObscuredInt m_treasureSellTier;

	private ObscuredInt m_enhanceTier;

	private ObscuredInt m_passiveChangeTier;

	private ObscuredInt m_pieceTierIndex;

	private ObscuredInt m_count;

	private List<Tuple<int, int, int>> m_needCraftMatItemList;

	private Dictionary<int, int> m_craftItems;

	private int m_craftMatItemCost;

	private List<UserItem> m_userItems;

	private List<EnumData.ItemSlotType> m_exclude;

	private bool m_isCrafting;

	private bool m_showEquippedInfo;

	private List<Tuple<int, int, string>> pieceTierMinMaxDescList;

	private Dictionary<int, string> enhanceTierMinMaxDescList;

	public TreasureListFilter m_filter;

	public bool m_turnOnFilter;

	private Action onCloseFilterPanel__BackingField;

	private GameObject m_materialTitleObj;

	private GameObject m_filterBtnObj;

	private UIReuseGrid m_leftGrid;

	private UIReuseScrollView m_leftScrollView;

	private GameObject m_itemTierObj;

	private UISprite m_itemTierBG;

	private UILabelLocalized m_tierLabel;

	private GameObject m_tierBtn;

	private CaocaoTabButtons tabButtons;

	private UILabelLocalized m_desc;

	private GameObject[] m_stars;

	private GameObject m_craftCountObj;

	private UILabelLocalized m_craftCountLabel;

	private GameObject m_craftCountBtnObj;

	private Transform m_rightTrans;

	private Transform m_leftTrans;

	private Transform m_buttonsTrans;

	private GameObject m_craftPanel;

	private GameObject m_composePanel;

	private GameObject m_changePanel;

	private GameObject m_piecePanel;

	private GameObject m_reinforcePanel;

	private GameObject m_passiveChangePanel;

	private GameObject m_treasureCreatePanel;

	private GameObject m_excludeSettingObj;

	private GameObject m_showListButton;

	private GameObject m_eventExBtn;

	private Dictionary<ulong, bool> m_selectedItemSrlMap;

	private PrefabInstance[] m_materials;

	private UILabelLocalized[] m_materialNames;

	private UILabelLocalized[] m_materialCounts;

	private UISprite m_lineTop;

	private UISprite[] m_lines;

	private int[] m_slotSequence;

	private ItemUI[] m_treasureForCompose;

	private Dictionary<ulong, bool> m_itemsEquipInfo;

	private const int m_treasureChangeMaxCount = 14;

	private const int m_treasureSellMaxCount = 21;

	private ItemUI[] m_treasureForChange;

	private ItemUI[] m_treasureForSell;

	private PanelTreasureEnhanceDetail m_enhanceDetail;

	private ItemUI m_treasureForEnhance;

	private ulong m_selectedTreasureSrlForEnhance;

	private GameObject m_enhanceSelectedEffect;

	private UILabelLocalized m_treasureForEnhanceName;

	private GameObject m_craftBtn;

	private GameObject m_pieceBtn;

	private GameObject m_sellBtn;

	private GameObject m_ticketBtn;

	private UILabelLocalized m_ticket;

	private UILabelLocalized m_ownedTicket;

	private bool m_useEnhanceKeeper;

	private GameObject m_enhanceKeeperChecker;

	private UILabelLocalized m_requireEnhanceKeeper;

	private UILabelLocalized m_ownedEnhanceKeeper;

	private bool m_useEnhanceGuarantee;

	private GameObject m_enhanceGuaranteeChecker;

	private UILabelLocalized m_requireEnhanceGuarentee;

	private UILabelLocalized m_ownedEnhanceGuarantee;

	private int m_enhanceFixedIncreaseLevel;

	private UILabelLocalized m_enhanceFixedName;

	private UILabelLocalized m_enhanceFixedLv;

	private GameObject m_enhanceFixedLvMinus;

	private GameObject m_enhanceFixedLvPlus;

	private GameObject m_ticketPassiveBtn;

	private UILabelLocalized m_ticketPassive;

	private UILabelLocalized m_ownedTicketPassive;

	private GameObject m_autoSelectBtn;

	private PassiveChangeContainer m_passiveChange;

	private GameObject m_pieceItemDim;

	private UILabelLocalized m_pieceItemName;

	private UILabelLocalized m_pieceItemNum;

	private ItemUI m_pieceItemUI;

	private UILabelLocalized m_excludeWeaponPrice;

	private UILabelLocalized m_excludeArmorPrice;

	private UILabelLocalized m_excludeKitPrice;

	private GameObject m_excludeWeapon;

	private GameObject m_excludeArmor;

	private GameObject m_excludeKit;

	private UILabelLocalized m_craftBtnLabel;

	private UILabelLocalized m_pieceBtnLabel;

	private UILabelLocalized m_sellBtnLabel;

	private UILabelLocalized m_sellTicketValueLabel;

	private GameObject m_btnTreasurePopup;

	private GameObject m_btnTreasurePassiveList;

	private UILabelLocalized m_silver;

	private UILabelLocalized m_gold;

	private Transform m_TreasureChangeTab;

	private Transform m_TreasureSellTab;

	private bool m_init;

	private bool initedComposeUi;

	private bool initedCraftUi;

	private bool initedChangeUi;

	private bool initedEnhanceUi;

	private bool initedPassiveChangeUi;

	private bool initedPieceUi;

	private List<int> m_obtainableTiers;

	private TreasureChangeDetailType changeDetailType
	{
		get
		{
			return TreasureChangeDetailType.Change;
		}
		set
		{
		}
	}

	private TreasureCraftType craftType
	{
		set
		{
		}
	}

	public Transform rightTrans
	{
		get
		{
			return null;
		}
	}

	public Transform leftTrans
	{
		get
		{
			return null;
		}
	}

	public Transform buttonsTrans
	{
		get
		{
			return null;
		}
	}

	private GameObject craftPanel
	{
		get
		{
			return null;
		}
	}

	private GameObject composePanel
	{
		get
		{
			return null;
		}
	}

	private GameObject changePanel
	{
		get
		{
			return null;
		}
	}

	private GameObject piecePanel
	{
		get
		{
			return null;
		}
	}

	private GameObject enhancePanel
	{
		get
		{
			return null;
		}
	}

	private GameObject passiveChangePanel
	{
		get
		{
			return null;
		}
	}

	private GameObject treasureCreatePanel
	{
		get
		{
			return null;
		}
	}

	public ulong TreasureSrlForEnhance
	{
		get
		{
			return 0uL;
		}
	}

	public ulong TreasureSrlForPassive
	{
		get
		{
			return 0uL;
		}
	}

	public int TreasurePassiveIndex
	{
		get
		{
			return 0;
		}
	}

	public event Action onCloseFilterPanel
	{
		add
		{
		}
		remove
		{
		}
	}

	public static void Show(TreasureCraftType craftType = TreasureCraftType.Craft)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void InitUI()
	{
	}

	private void InitComposeUi()
	{
	}

	private void InitCraftUi()
	{
	}

	private void InitChangeUi()
	{
	}

	private void UpdateChangeDetailUI()
	{
	}

	private void UpdateChangeTabUI()
	{
	}

	private void UpdateSellTabUI()
	{
	}

	private void InitEnhanceUi()
	{
	}

	private void OnUpdatedItem()
	{
	}

	private void InitPassiveChangeUi()
	{
	}

	private void InitTreasureCreateUi()
	{
	}

	private void SetChangeRatioPanelActive(int tier, bool active)
	{
	}

	private Transform GetChangeRatioPanel(int tier)
	{
		return null;
	}

	private void InitPieceUi()
	{
	}

	private void UpdateTreasureMix()
	{
	}

	private void RefreshUI()
	{
	}

	private void OnChangeButtonsTabChanged(CaocaoButton button)
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	public void SelectTab(TreasureCraftType type)
	{
	}

	private void PrepareItemsEquipInfo()
	{
	}

	public void Refresh()
	{
	}

	private void UpdateUI(TreasureCraftType craftType)
	{
	}

	private void UpdateLeftUI()
	{
	}

	private void UpdateGridItems(bool showEquippedInfo)
	{
	}

	private void UpdateRightUI()
	{
	}

	private void UpdateRightPanel()
	{
	}

	private void UpdateCraftUI()
	{
	}

	private void UpdateComposeUI()
	{
	}

	private void UpdateComposeSlots()
	{
	}

	private void UpdateChangeUI()
	{
	}

	private void UpdateEnhanceUI()
	{
	}

	private void UpdatePassiveChangeUI()
	{
	}

	private void UpdatePieceUI()
	{
	}

	private void UpdateTreasureCreateUI()
	{
	}

	private void SetRatio(Transform panel, string name, int ratio, int ratioSum)
	{
	}

	private void SetItems(Transform panel, string name, int rewardId, int rewardType)
	{
	}

	private void OnClickChangeResultItem(ItemUI itemUI)
	{
	}

	private void UpdateTierLabel()
	{
	}

	private void UpdateCountLabel()
	{
	}

	private void SetFreeCraftBtn(string format, int free_count)
	{
	}

	private void SetGoodsCraftBtn(string format, int silver, int gold)
	{
	}

	private void SetSellBtn(string format, int count)
	{
	}

	private void UpdateCost()
	{
	}

	private void UpdateExcludeSetting()
	{
	}

	private void RecoverListFromFilter()
	{
	}

	public void OnClickFilter()
	{
	}

	private void OnClickConfirmFilterPanel(TreasureListFilter filter)
	{
	}

	public void OnCancelFilterPanel()
	{
	}

	private void ShowFilter()
	{
	}

	private void ToggleFilter()
	{
	}

	private string GetFilterBtnKey()
	{
		return null;
	}

	private string GetFilterKey()
	{
		return null;
	}

	private void UpdateFilterInfo()
	{
	}

	private void SetFilterBtnInfo()
	{
	}

	private bool IsCheckedFilter(UserItem userItem)
	{
		return false;
	}

	private void ApplyFilter()
	{
	}

	private void UpdateFilterBtn()
	{
	}

	private void SelectIngredient(TreasureBookCellData selectedCellData)
	{
	}

	private void SelectMultipleIngredientForCompose(List<UserItem> autoSelectedItems)
	{
	}

	private void SelectIngredientForCompose(UserItem userItem)
	{
	}

	private void SelectMultipleIngredientForSell(List<UserItem> autoSelectedItems)
	{
	}

	private void SelectMultipleIngredientForChange(List<UserItem> autoSelectedItems)
	{
	}

	private void SelectIngredientForSell(UserItem userItem)
	{
	}

	private void SelectIngredientForChange(UserItem userItem)
	{
	}

	private void SelectIngredientForEnhance(UserItem userItem)
	{
	}

	private void SelectIngredientForPassiveChange(UserItem userItem)
	{
	}

	private void ProbabilityInformationClicked()
	{
	}

	private void EnhancePassiveClicked(int index)
	{
	}

	private void SelectEnhancePassive(int index)
	{
	}

	public void ShowItemTierList()
	{
	}

	private void SelectItemTier(int tier)
	{
	}

	public void ShowCraftCountList()
	{
	}

	private void SelectCraftCount(int count)
	{
	}

	public void ShowObtainableTreasure()
	{
	}

	private void OnClickMaterial(ItemUI itemUI)
	{
	}

	private void OnClickSelectedTreasure(ItemUI itemUI)
	{
	}

	private void Deselect(ItemUI itemUI)
	{
	}

	public void ShowTreasureBook()
	{
	}

	public void AutoSelect()
	{
	}

	public void ShowTreasurePassiveList()
	{
	}

	public void ExcludeWeapon()
	{
	}

	public void ExcludeArmor()
	{
	}

	public void ExcludeKit()
	{
	}

	private void ToggleExcludeSetting(EnumData.ItemSlotType slotType)
	{
	}

	private void ResetExcludeSetting()
	{
	}

	public void CraftTreasure()
	{
	}

	private void Craft()
	{
	}

	private void CheckCraftTreasureCost()
	{
	}

	private void CheckCraftTreasure()
	{
	}

	private void CraftMatItem(int itemId, int amount, int cost)
	{
	}

	private void CraftTreasures()
	{
	}

	private void Compose()
	{
	}

	[DebuggerHidden]
	private IEnumerator ProcessCompose()
	{
		return null;
	}

	private void TreasureSell()
	{
	}

	[DebuggerHidden]
	private IEnumerator ProcessTreasureSell()
	{
		return null;
	}

	private void Change()
	{
	}

	[DebuggerHidden]
	private IEnumerator ProcessChange()
	{
		return null;
	}

	private void Piece()
	{
	}

	private void Enhance(bool byTicket = false)
	{
	}

	public void EnhanceByTicket()
	{
	}

	private void EnhanceKeeperUseButtonClicked()
	{
	}

	private void EnhanceGuaranteeUseButtonClicked()
	{
	}

	private void EnhanceFixedMinusClicked()
	{
	}

	private void EnhanceFixedPlusClicked()
	{
	}

	private void UpdateEnhanceKeeperSetting(bool useKeeper)
	{
	}

	private void UpdateEnhanceGuaranteeSetting(bool useGuarantee)
	{
	}

	private void UpdateEnhanceFixed()
	{
	}

	[DebuggerHidden]
	private IEnumerator ProcessEnhance(bool byTicket)
	{
		return null;
	}

	private HttpRequest.HttpRequestContext CreateEnhanceContext(UserItem userItem, bool byTicket, bool useKeeper, bool useGuarantee)
	{
		return null;
	}

	private void PassiveChange()
	{
	}

	[DebuggerHidden]
	private IEnumerator ProcessPassiveChange()
	{
		return null;
	}

	private HttpRequest.HttpRequestContext CreatePassiveChangeRequest(UserItem userItem, int requestCount)
	{
		return null;
	}

	public void ShowCreCompEventEx()
	{
	}

	private void SetStateForTreasurePassiveListButton(bool enable)
	{
	}

	private int CalculateComposeCount()
	{
		return 0;
	}

	private void CalculateSellReward(out int itemId, out int amount)
	{
		itemId = default(int);
		amount = default(int);
	}

	private void CalculateChangeCost(out int silver, out int gold)
	{
		silver = default(int);
		gold = default(int);
	}

	public List<UserItem> FindAutoSelectTarget(int itemLimit)
	{
		return null;
	}

	private bool IsSelectedItem(ulong itemSrl)
	{
		return false;
	}

	private void UpdateSelectedSrlMap(ulong itemSrl, bool selected)
	{
	}

	private void SelectCellData(ulong itemSrl, bool selected)
	{
	}

	private void UpdateCellData(ulong itemSrl)
	{
	}

	private void UpdateGridPosition(ulong itemSrl)
	{
	}

	private TreasureBookCellData FindCellData(ulong itemSrl)
	{
		return null;
	}

	private void ShowTabjoyPlacement(TreasureCraftType tabType)
	{
	}

	protected override void OnDestroy()
	{
	}
}

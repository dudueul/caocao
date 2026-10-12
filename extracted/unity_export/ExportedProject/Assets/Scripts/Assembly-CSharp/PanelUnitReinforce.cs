using System.Collections.Generic;
using Eppy;
using UnityEngine;

public class PanelUnitReinforce : SelfRegister2UIComponentsMonobehaviour
{
	private UserUnit m_userUnit;

	private bool m_isMaxLevel;

	private Transform m_unitNameObj;

	private ReinforceLev m_currReinforceLev;

	private ReinforceLev m_nextReinforceLev;

	private UILabelLocalized m_currCombatPower;

	private UILabelLocalized m_nextCombatPower;

	private GameObject m_nextReinforceObj;

	private GameObject m_maxReinforceObj;

	private Transform m_unitLookObj;

	private PrefabInstance m_troopStatInst;

	private ItemUI[] m_beforeItemUI;

	private ItemUI[] m_afterItemUI;

	private GameObject[] m_afterDim;

	private GameObject[] m_afterCompose;

	private GameObject[] m_afterSearch;

	private GameObject[] m_arrow1;

	private GameObject[] m_arrow2;

	private GameObject[] m_upgradeBtn;

	private GameObject[] m_complete;

	private UILabelLocalized[] m_notice;

	private GameObject m_unitSprite;

	private GameObject m_reinforceBtn;

	private GameObject m_reinforceBtnLabel;

	private GameObject m_reinforceBtnIcon;

	private GameObject m_reinforceBtnLabelCenter;

	private UILabelLocalized m_reinforceCost;

	private bool m_isProcessing;

	private bool m_onlyUpgrade;

	private List<Tuple<int, int, EnumData.ItemSlotType>> m_needCraftItemList;

	private List<Tuple<EnumData.ItemSlotType, ItemUI>> m_upgradeUnitItemList;

	public UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool isProcessing
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	protected override void Awake()
	{
	}

	public void ShowPanel(bool show, UserUnit userUnit)
	{
	}

	public void ClosePane()
	{
	}

	private void InitUI()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnItemUpdated()
	{
	}

	public void UpdateUI()
	{
	}

	private void UpdateUnitName()
	{
	}

	private void UpdateUnitLook(UserUnit nextUnit)
	{
	}

	private void SetUnitItems()
	{
	}

	private void SetItemLevelColor(UserItem userItem, ItemUI itemUI)
	{
	}

	private void UpdateUpgrade(EnumData.ItemSlotType slot, UILabelLocalized notice, GameObject arrow1, GameObject arrow2, GameObject upgradeBtn, GameObject complete, out bool canEquip)
	{
		canEquip = default(bool);
	}

	private void SetNextLevel(ItemUI itemUI, GameObject dim, GameObject compose, GameObject search, EnumData.ItemSlotType slot, bool canEquip)
	{
	}

	private void SetUnitSprite()
	{
	}

	private void ClickCurrentItem(ItemUI itemUI)
	{
	}

	private void ClickNextItem(ItemUI itemUI)
	{
	}

	public void UpgradeWeapon()
	{
	}

	public void UpgradeArmor()
	{
	}

	public void UpgradeKit()
	{
	}

	private void UpgradeUnitItem(EnumData.ItemSlotType slot, ItemUI targetItemUI)
	{
	}

	public void AutoReinforceUnit()
	{
	}

	private void ShowReinforceUnitPopup()
	{
	}

	private void ReinforceUnit()
	{
	}

	private void CheckAllItemCanUpgrade()
	{
	}

	private void ShowAutoItemMixPopup(Dictionary<int, int> stuffs, int cost)
	{
	}

	private void ExecuteAutoUnitReinforce()
	{
	}

	private void CraftItem(int itemId, int cost, EnumData.ItemSlotType slot)
	{
	}

	public void ShowTooltip()
	{
	}
}

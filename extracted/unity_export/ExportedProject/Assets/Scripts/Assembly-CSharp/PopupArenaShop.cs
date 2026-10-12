using UnityEngine;

public class PopupArenaShop : CaocaoUIModalPopup
{
	private class PickedPresetData
	{
		public int p;

		public int u;

		public PickedPresetData(int _p, int _u)
		{
		}
	}

	private class PickedBonusData
	{
		public int id;

		public PickedBonusData(int _id)
		{
		}
	}

	private UserArena userArena;

	private UserArenaLevel userArenaLevel;

	private ArenaLevel pdArenaLevel;

	private bool selectedUnit;

	private int selectedIdx;

	private int presetCnt;

	private int bonusCnt;

	private UILabel m_labelCoin;

	private UITable m_tableGrid;

	private UIGrid m_gridUnit;

	private UIGrid m_gridBonus;

	private GameObject m_objPresetInfo;

	private GameObject m_objBonusInfo;

	private GameObject m_objDimInfo;

	private UILabelLocalized m_labelDimInfo;

	private UILabelLocalized m_labelRefresh;

	private UILabelLocalized m_labelPresetName;

	private UITexture m_texUnitFace;

	private UILabelLocalized m_labelUnitCost;

	private PrefabInstance m_instUnitSynergy;

	private UILabelLocalized m_labelUnitName;

	private UILabelLocalized m_labelUnitJob;

	private UIGrid m_gridUnitTreasures;

	private UIGrid m_gridUnitPassives;

	private UILabelLocalized m_labelUnitPassiveExt;

	private UILabel m_labelPresetCost;

	private UILabelLocalized m_labelBonusName;

	private GameObject m_objBonusPassive;

	private GameObject m_objBonusTreasure;

	private PrefabInstance m_instBonusPassive;

	private UILabelLocalized m_labelBonusPassiveDesc;

	private UIGrid m_gridBonusPassiveType;

	private PrefabInstance m_instBonusTreasure;

	private UIGrid m_gridBonusTreasureType;

	private Transform m_objBonusTreasurePassives;

	private UILabel m_labelBonusCost;

	private UILabel labelCoin
	{
		get
		{
			return null;
		}
	}

	private UITable tableGrid
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridUnit
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridBonus
	{
		get
		{
			return null;
		}
	}

	private GameObject objPresetInfo
	{
		get
		{
			return null;
		}
	}

	private GameObject objBonusInfo
	{
		get
		{
			return null;
		}
	}

	private GameObject objDimInfo
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelDimInfo
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelRefresh
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelPresetName
	{
		get
		{
			return null;
		}
	}

	private UITexture texUnitFace
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelUnitCost
	{
		get
		{
			return null;
		}
	}

	private PrefabInstance instUnitSynergy
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelUnitName
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelUnitJob
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridUnitTreasures
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridUnitPassives
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelUnitPassiveExt
	{
		get
		{
			return null;
		}
	}

	private UILabel labelPresetCost
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelBonusName
	{
		get
		{
			return null;
		}
	}

	private GameObject objBonusPassive
	{
		get
		{
			return null;
		}
	}

	private GameObject objBonusTreasure
	{
		get
		{
			return null;
		}
	}

	private PrefabInstance instBonusPassive
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelBonusPassiveDesc
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridBonusPassiveType
	{
		get
		{
			return null;
		}
	}

	private PrefabInstance instBonusTreasure
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridBonusTreasureType
	{
		get
		{
			return null;
		}
	}

	private Transform objBonusTreasurePassives
	{
		get
		{
			return null;
		}
	}

	private UILabel labelBonusCost
	{
		get
		{
			return null;
		}
	}

	public static void Show()
	{
	}

	private bool RequestShopItems()
	{
		return false;
	}

	private bool SendOpenShop(bool refresh)
	{
		return false;
	}

	private void UpdateCoinLabel()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUnitItemUI(Transform item, UserArenaShopItem shopItem, int unitLevel, int idx)
	{
	}

	private void OnSelectItem(bool selUnit, int idx)
	{
	}

	private void UpdateBonusItemUI(Transform item, UserArenaShopItem shopItem, int idx)
	{
	}

	private void UpdateRightUI()
	{
	}

	private void UpdatePresetInfoUI(UserArenaShopItem shopItem)
	{
	}

	private void UpdateBonusInfoUI(UserArenaShopItem shopItem)
	{
	}

	private void UpdateTreasureInfoUI(int itemId, bool isLock)
	{
	}

	private void UpdatePassiveInfoUI(int arenaPassiveId, bool isLock)
	{
	}

	public void OnRefreshButton()
	{
	}

	public void OnPromotionButton()
	{
	}

	public void OnUnitInfoButton()
	{
	}

	private void ShowLackCoinAlertPopup()
	{
	}

	public void OnPurchasePresetButton()
	{
	}

	public void OnPurchaseBonusButton()
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

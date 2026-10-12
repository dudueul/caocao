using UnityEngine;

public class PopupArenaUnitsInfo : CaocaoUIModalPopup
{
	private enum UnitSortType
	{
		None = 0,
		Join = 1,
		Deck = 2,
		Preset = 3,
		Synergy = 4
	}

	private UserArena userArena;

	private bool showPromotion;

	private ArenaUnitInfoListItem selectedItem;

	private UnitSortType m_unitSortType;

	private UILabel m_labelCoin;

	private UIGrid m_grid;

	private GameObject m_btnPromotion;

	private UILabel m_labelPromotionCost;

	private GameObject m_objDimPromotion;

	private GameObject m_unitSortTabObj;

	private GameObject m_unitSortTabListObj;

	private UILabelLocalized m_unitSortTabLabel;

	private UIButton m_unitSortTabGridItem_Join;

	private UIButton m_unitSortTabGridItem_Deck;

	private UIButton m_unitSortTabGridItem_Preset;

	private UIButton m_unitSortTabGridItem_Synergy;

	private UnitSortType unitSortType
	{
		get
		{
			return UnitSortType.None;
		}
		set
		{
		}
	}

	private UILabel labelCoin
	{
		get
		{
			return null;
		}
	}

	private UIGrid grid
	{
		get
		{
			return null;
		}
	}

	private GameObject btnPromotion
	{
		get
		{
			return null;
		}
	}

	private UILabel labelPromotionCost
	{
		get
		{
			return null;
		}
	}

	private GameObject objDimPromotion
	{
		get
		{
			return null;
		}
	}

	private GameObject unitSortTabObj
	{
		get
		{
			return null;
		}
	}

	private GameObject unitSortTabListObj
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized unitSortTabLabel
	{
		get
		{
			return null;
		}
	}

	private UIButton unitSortTabGridItem_Join
	{
		get
		{
			return null;
		}
	}

	private UIButton unitSortTabGridItem_Deck
	{
		get
		{
			return null;
		}
	}

	private UIButton unitSortTabGridItem_Preset
	{
		get
		{
			return null;
		}
	}

	private UIButton unitSortTabGridItem_Synergy
	{
		get
		{
			return null;
		}
	}

	public static void Show(bool showPromotion)
	{
	}

	private void UpdateCoinLabel()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateGridUI()
	{
	}

	private void OnSelectItem(int idx)
	{
	}

	public void ShowUnitSortTabist()
	{
	}

	public void AddUnitSortTabGridEvent()
	{
	}

	private void SelectUnitSortType(UnitSortType tabType)
	{
	}

	private void UpdateStorySeriesSortLabel()
	{
	}

	private string GetStringStorySeriesType(UnitSortType tabType)
	{
		return null;
	}

	public void OnTreasureListButton()
	{
	}

	public void OnPassiveListButton()
	{
	}

	public void OnPromotionButton()
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

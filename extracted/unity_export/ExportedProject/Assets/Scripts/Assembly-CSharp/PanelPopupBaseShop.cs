using UnityEngine;

public class PanelPopupBaseShop : CaocaoUIModalPopup
{
	public enum TabMenu
	{
		GOLD = 0,
		PACKAGE = 1,
		SEASON_TICKET = 2,
		GOODS = 3,
		RECOMMEND = 4,
		SPECIAL = 5,
		LIMIT = 6,
		TREASURE_BOX = 7,
		RUNE_BOX = 8,
		PROMOTE_BOX = 9,
		LINKED_GOODS = 10
	}

	protected static int tokenItemId;

	protected TabMenu m_currentMenu;

	protected bool m_gridItemInit;

	protected bool m_changeTab;

	private bool m_showMainBody;

	private UIButton m_btnClose;

	private UIReuseGrid m_longGrid;

	private UIReuseScrollView m_longScrollView;

	private Transform m_scrollBar;

	private Transform m_tabBtns;

	private CaocaoTabButtons m_tabButtons;

	private Transform m_infoTransf;

	private UIButton m_btnVip;

	private UIButton m_btnHistory;

	private UIButton m_btnSpcCommTransAct;

	private UIButton m_btnSettlementAct;

	private UIButton m_btnTreasureProbability;

	private UIButton m_btnChargeItemChanceInfo;

	public TabMenu currentMenu
	{
		get
		{
			return TabMenu.GOLD;
		}
		set
		{
		}
	}

	protected bool showMainBody
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	protected UIButton btnClose
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	protected UIReuseGrid longGrid
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	protected UIReuseScrollView longScrollView
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	protected Transform scrollBar
	{
		get
		{
			return null;
		}
	}

	protected Transform tabBtns
	{
		get
		{
			return null;
		}
	}

	protected CaocaoTabButtons tabButtons
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private Transform infoTransf
	{
		get
		{
			return null;
		}
	}

	protected UIButton btnVip
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	protected UIButton btnHistory
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	protected UIButton btnSpcCommTransAct
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	protected UIButton btnSettlementAct
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	protected UIButton btnTreasureProbability
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	protected UIButton btnChargeItemChanceInfo
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public override void OnFocusOut()
	{
	}

	public override void OnFocusOn()
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

	protected void OnCloseBtnClicked()
	{
	}

	public void OnPanelClose()
	{
	}

	protected void OnVipBtnClicked()
	{
	}

	protected void OnHistoryBtnClicked()
	{
	}

	protected void OnSpcCommActBtnClicked()
	{
	}

	protected void OnSettlementActBtnClicked()
	{
	}

	protected void OnTreasureProbabilityBtnClicked()
	{
	}

	protected void OnChargeItemChanceInfoBtnClicked()
	{
	}

	protected void UpdateVipButton()
	{
	}

	protected void GridClearItem()
	{
	}

	protected void GridReposition()
	{
	}

	protected void UpdateVipBtnNewTag()
	{
	}
}

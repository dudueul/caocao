using System;
using System.Collections.Generic;
using UnityEngine;

public class PanelPopupRuneShop : CaocaoUIModalPopup
{
	public enum TabMenu
	{
		DECOMPOSE = 0,
		COMPOSE = 1
	}

	private const int m_BlueDragonBoxId = 13040001;

	private const int m_VermilionBirdBoxId = 13040002;

	private const int m_WhiteTigerBoxId = 13040003;

	private const int m_BlackTortoiseBoxId = 13040004;

	private TabMenu m_currentMenu;

	private UIReuseGrid m_grid;

	private UIReuseScrollView m_scrollView;

	private Transform m_scrollBar;

	private UIButton m_btnClose;

	private CaocaoTabButtons m_tabButtons;

	private Transform m_infoDecompose;

	private UILabelLocalized m_labelAmount;

	private UIInput m_inputSelNum;

	private UILabelLocalized m_labelMaxNum;

	private UISlider m_slider;

	private UIButton m_btnPlus;

	private UIButton m_btnMinus;

	private UISprite m_spriteCost;

	private UILabelLocalized m_labelCost;

	private TooltipContentsGenerator m_runeTooltipInPanel;

	private Transform m_infoCompose;

	private RuneListItem.CellData selectedCellData;

	private UserRuneItem selectedRuneItem;

	private int selectedNum;

	private List<RuneListItem.CellData> cellDatas;

	private RuneListFilter m_filter;

	private bool m_turnOnFilter;

	private bool m_turnOnFilterBackup;

	private Action onCloseFilterPanel__BackingField;

	public TabMenu currentMenu
	{
		get
		{
			return TabMenu.DECOMPOSE;
		}
		set
		{
		}
	}

	private UIReuseGrid grid
	{
		get
		{
			return null;
		}
	}

	private UIReuseScrollView scrollView
	{
		get
		{
			return null;
		}
	}

	private Transform scrollBar
	{
		get
		{
			return null;
		}
	}

	private UIButton btnClose
	{
		get
		{
			return null;
		}
	}

	private CaocaoTabButtons tabButtons
	{
		get
		{
			return null;
		}
	}

	private Transform infoDecompose
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelAmount
	{
		get
		{
			return null;
		}
	}

	private UIInput inputSelNum
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelMaxNum
	{
		get
		{
			return null;
		}
	}

	private UISlider slider
	{
		get
		{
			return null;
		}
	}

	private UIButton btnPlus
	{
		get
		{
			return null;
		}
	}

	private UIButton btnMinus
	{
		get
		{
			return null;
		}
	}

	private UISprite spriteCost
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelCost
	{
		get
		{
			return null;
		}
	}

	private TooltipContentsGenerator runeTooltipInPanel
	{
		get
		{
			return null;
		}
	}

	public Transform InfoCompose
	{
		get
		{
			return null;
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

	protected void OnEnable()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
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

	private void UpdateBtnNewTag()
	{
	}

	private void UpdateTab()
	{
	}

	private void UpdateUI()
	{
	}

	private void GridReposition()
	{
	}

	private void UpdateDecomposeGrid()
	{
	}

	private void OnSelectRune(IReuseCellData cellData)
	{
	}

	private void OnSelectRune(UserRuneItem runeItem)
	{
	}

	public void OnBtnDecomposition()
	{
	}

	private void CheckAbledDecomposition()
	{
	}

	public void OnBtnPlus()
	{
	}

	public void OnBtnPlusImpl(int count)
	{
	}

	public void OnBtnMinus()
	{
	}

	public void OnBtnMinusImpl(int count)
	{
	}

	private void ChangeSelectNum(int num)
	{
	}

	public void OnSlider()
	{
	}

	public void OnNumberInput()
	{
	}

	private void CalcDecompositionResult()
	{
	}

	private void OnUpdateUserRuneItems()
	{
	}

	private void DestroyGridItems()
	{
	}

	public void OnPanelClose()
	{
	}

	private void OnCloseBtnClicked()
	{
	}

	private void OnDecomposeBtnClicked()
	{
	}

	private void OnComposeBtnClicked()
	{
	}

	public void OnChargeItemChanceInfoBtnClicked()
	{
	}

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnDisable()
	{
	}

	public void OnTabClicked(CaocaoButton button)
	{
	}

	public static void Show(TabMenu tab)
	{
	}

	public static void RefreshDecompose()
	{
	}

	private string GetTabName()
	{
		return null;
	}

	private void SetRuneBox(Transform tf, bool enable, string desc)
	{
	}

	public void OnFilterPress()
	{
	}

	public void OnBtnDecompositionAll()
	{
	}

	public void SetFilterOff()
	{
	}

	private void RecoverFilterStatus()
	{
	}

	private void OnApplyFilter(RuneListFilter filter)
	{
	}

	private void OnDecompositionAll(RuneListFilter filter)
	{
	}

	private void RefreshPanel()
	{
	}

	public void OnFilterCancel()
	{
	}

	private void ShowFilter()
	{
	}

	private void ShowDecompositionAll()
	{
	}

	private void ToggleFilter()
	{
	}

	private void GetFilter()
	{
	}

	private bool IsCheckedFilter(UserRuneItem userRune)
	{
		return false;
	}

	private void SetFilterBtn()
	{
	}

	private void ApplyFilter()
	{
	}

	private void RecoverListFromFilter()
	{
	}
}

using System;
using System.Collections.Generic;
using TooltipController;

public class PopupRuneEquip : CaocaoUIModalPopup
{
	public UIReuseGrid m_reuseGrid;

	public UILabelLocalized m_amountLabel;

	public UIButton m_filterBtn;

	public TooltipContentsGenerator m_runeTooltipInPanel;

	private EquipRunePanelTooltip m_innerTooltip;

	public UIButton m_equipRune;

	private RuneListItem.CellData m_selectedData;

	private RuneItemSlot m_runeItemSlot;

	private RuneListFilter m_filter;

	private bool m_turnOnFilter;

	private bool m_turnOnFilterBackup;

	public static PopupRuneEquip instance;

	private List<RuneListItem.CellData> originCellDatas;

	private List<RuneListItem.CellData> cellDatas;

	public RuneListItem.CellData selectedData
	{
		get
		{
			return null;
		}
		set
		{
		}
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

	public static void Show(UserUnit userUnit, int runeSlotNum, Action callback = null)
	{
	}

	public static void Show(UserUnit userUnit, int runeSlotIndex, int runeSlotNum, Action callback = null)
	{
	}

	private void InitRuneList()
	{
	}

	private void UpdateGridItems()
	{
	}

	private void UpdateSelectGridItem()
	{
	}

	public void UpdateUI()
	{
	}

	private void UpdateAmountLabel()
	{
	}

	private void UpdateTooltipInPanel()
	{
	}

	private void SortRuneList()
	{
	}

	private void ClearData()
	{
	}

	private void OnSelectItem(RuneListItem.CellData selectedData)
	{
	}

	private void OnDoubleClickItem(RuneListItem.CellData selectedData)
	{
	}

	private void OnDisable()
	{
	}

	public void OnFilterPress()
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

	public void OnFilterCancel()
	{
	}

	private void ShowFilter()
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

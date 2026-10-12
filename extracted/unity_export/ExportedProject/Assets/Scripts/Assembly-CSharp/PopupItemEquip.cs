using System;
using System.Collections.Generic;

public class PopupItemEquip : PopupItemInfo
{
	public static PopupItemEquip instance;

	private TreasureListItem.CellData m_equippedItemData;

	private TreasureListItem.CellData m_selectedItemData;

	public UILabelLocalized m_equipBtnLabel;

	public UIReuseGrid m_itemGrid;

	private List<TreasureListItem.CellData> m_origItemDataList;

	private List<TreasureListItem.CellData> m_itemDataList;

	private int m_showingTierStart;

	private int m_showingTierEnd;

	private static IUserDataBase sortUserData;

	private static Comparison<TreasureListItem.CellData> SortItemEquip;

	private static Comparison<TreasureListItem.CellData> SortItemQuickSlot;

	public TreasureListItem.CellData equippedItemData
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public TreasureListItem.CellData selectedItemData
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static void ShowPopup(UserUnit userUnit, EnumData.ItemSlotType slotType, bool hideBottomArea = false, int quickSlotDeckId = 0)
	{
	}

	protected override void Awake()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	protected override void InitUI()
	{
	}

	private void InitItems()
	{
	}

	protected override void UpdateUI()
	{
	}

	private void UpdateGridItems()
	{
	}

	private void UpdateSelectGridItem()
	{
	}

	private void UpdateSelectedItem()
	{
	}

	private void ClearDatas()
	{
	}

	private void OnSelectItem(TreasureListItem.CellData selectedData)
	{
	}

	private void OnDoubleClickItem(TreasureListItem.CellData selectedData)
	{
	}

	private void ShowFilterList(bool bShow)
	{
	}

	private void SetFilterLabel(string label)
	{
	}

	public void OnClickFilter()
	{
	}

	public void OnClickFilterListAll()
	{
	}

	public void OnClickFilterListScenario()
	{
	}

	public void OnClickFilterListStrategy()
	{
	}

	public void EquipSelectedItem()
	{
	}

	public void EquipItemToQuickSlot(UserUnit userUnit, ulong itemSrl)
	{
	}

	public override void TakeOffSelectedItem()
	{
	}

	private void SortItemDatas()
	{
	}

	private static int ItemEquipQS(UserUnit userUnit, ulong userItemSrl)
	{
		return 0;
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
}

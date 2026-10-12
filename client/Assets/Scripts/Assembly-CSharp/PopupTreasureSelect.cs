using System;
using System.Collections.Generic;
using UnityEngine;

public class PopupTreasureSelect : CaocaoUIModalPopup
{
	private delegate List<int> GetItemList(EnumData.ItemSlotType subIdx);

	private const string ITEM = "Item_";

	protected TreasureBookCellData m_selectedCellData;

	public CaocaoTabButtons tabButtons;

	public CaocaoButton tabButtonSource;

	[HideInInspector]
	public UIButton btnSelect;

	public UILabelLocalized labelTitle;

	private int m_boxType;

	private List<EnumData.TreasureType> m_treasureType;

	private List<EnumData.ItemSlotType> m_include;

	private EnumData.ItemType m_itemType;

	private bool m_enableGetItem;

	private EnumData.ItemSlotType currentTab;

	private List<CaocaoButton> listButton;

	private GetItemList getItemList;

	private bool showTabButtons;

	private Transform m_itemInfo;

	private UIReuseScrollView m_scrollView;

	private UIReuseGrid m_grid;

	public Action<int> onBtnSelectItem;

	public Action<bool> onBtnSelectUniqueItem;

	public Transform itemInfo
	{
		get
		{
			return null;
		}
	}

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public static PopupTreasureSelect Show(int itemId, int tier, int boxType, Action<int> onSelectItem = null)
	{
		return null;
	}

	public static PopupTreasureSelect Show(int tier, int boxType, Action<int> onSelectItem = null)
	{
		return null;
	}

	public static PopupTreasureSelect Show(EnumData.ItemType itemType, int boxType, Action<bool> onSelectUniqueItem = null)
	{
		return null;
	}

	public static PopupTreasureSelect Show(List<EnumData.ItemSlotType> include, bool showTabButtons, Action<int> onSelectItem = null)
	{
		return null;
	}

	public List<EnumData.ItemSlotType> GetTabList()
	{
		return null;
	}

	public void InitTabButtons()
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

	protected override void ClosePanelImpl()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	protected void UpdateGrid(EnumData.ItemSlotType subIdx)
	{
	}

	private List<int> GetObtainableTreasureItems(EnumData.ItemSlotType subIdx)
	{
		return null;
	}

	private void _GetObtainableTreasureItems(ItemRatio itemRatio, EnumData.ItemSlotType subIdx, ref List<int> targets)
	{
	}

	private List<int> GetObtainableItems(EnumData.ItemSlotType subIdx)
	{
		return null;
	}

	public void UpdateDetailUI()
	{
	}

	private void OnBtnSelectItem()
	{
	}

	private void OnBtnSelectUniqueItem()
	{
	}

	private void OnItemSelected(TreasureBookCellData selectedCellData)
	{
	}
}

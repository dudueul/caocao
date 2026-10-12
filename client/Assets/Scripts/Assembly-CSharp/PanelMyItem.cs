using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PanelMyItem : SelfRegister2UIComponentsMonobehaviour
{
	private enum ItemDetail
	{
		NONE = 0,
		SIMPLE = 1,
		LONG = 2
	}

	private Color COLOR_DARKGRAY;

	public PrefabInstance m_profileInst;

	public PrefabInstance m_unitstatInst;

	public PrefabInstance m_troopstatInst;

	public PrefabInstance m_equipInst;

	private EquipUI m_equipUI;

	public CaocaoRadioButton m_btnAll;

	public CaocaoRadioButton m_btnWeapon;

	public CaocaoRadioButton m_btnArmor;

	public CaocaoRadioButton m_btnKit;

	public CaocaoRadioButton m_btnConsumable;

	public UILabelLocalized m_PanelTitle;

	public UILabelLocalized m_BtnWearLabel;

	public PanelItemDetail m_ItemSimpleDetail;

	public PanelItemDetail m_ItemLongDetail;

	public UILabelLocalized m_EquipAvailable;

	public UIButton m_BtnAutoWear;

	public UIButton m_btnCancel;

	public UIScrollView m_itemScrollView;

	public UIScrollView m_unitScrollView;

	public UIGrid m_itemGrid;

	public UIGrid m_unitGrid;

	private EnumData.ItemSlotType currentTab;

	private ItemUI m_focusedItem;

	private UnitNameListItem m_focusedUnit;

	private ItemDetail m_itemDetail;

	private EquipUI equipUI
	{
		get
		{
			return null;
		}
	}

	private ItemUI focusedItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private UnitNameListItem focusedUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private ulong focusedUnitSrl
	{
		get
		{
			return 0uL;
		}
	}

	private void Start()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void InitTabBtn(CaocaoRadioButton tabBtn, EnumData.ItemSlotType itemSlot, float activeX, float inactiveX)
	{
	}

	public void ShowPanel()
	{
	}

	public void ClosePanel()
	{
	}

	private void Refresh()
	{
	}

	private void RemoveAllItems()
	{
	}

	private void RemoveAllUnits()
	{
	}

	private void UpdateInventory(EnumData.ItemSlotType slotType)
	{
	}

	private void UpdateBtnLabel()
	{
	}

	private void UpdateUnitList()
	{
	}

	private void UpdateUnitInfo()
	{
	}

	private List<string> GetEquipableItemString(int jobID)
	{
		return null;
	}

	private void CollectItemTypeDesc(int itemType, List<string> typeDescs)
	{
	}

	public void SelectItem(ItemUI itemUI)
	{
	}

	private bool isEquippedItemFocused()
	{
		return false;
	}

	public void ChangeItemDetail()
	{
	}

	private void UpdateItemDetail()
	{
	}

	private ItemUI GetFirstInventoryItem()
	{
		return null;
	}

	private void SelectUnit(UnitNameListItem unitNameUI)
	{
	}

	public void EquipFocusedItem()
	{
	}

	private void EquipItem(ItemUI itemUI)
	{
	}

	private void ChangeEquipable(ulong itemSrl)
	{
	}

	public void AutoEquipItem()
	{
	}

	private void ProcessToChangeEquipable(ulong unitSrl, int slotType, ulong itemSrl)
	{
	}

	private void OnEquipItem(JsonData response)
	{
	}

	private void OnTakeOffItem(JsonData response)
	{
	}

	private ItemUI GetItemUIBySrl(ulong itemSrl)
	{
		return null;
	}
}

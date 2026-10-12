using System;
using System.Collections.Generic;
using UnityEngine;

public class FilterItemType : MonoBehaviour
{
	private UIButton m_btnSort;

	private UILabelLocalized m_sortLabel;

	private PanelItemListSort m_itemListSort;

	private EnumData.ItemFilterType[] m_filterTypes;

	private EnumData.ItemFilterType m_filterType;

	public Action<EnumData.ItemFilterType> OnFilterTypeChanged;

	private Dictionary<EnumData.ItemFilterType, List<EnumData.ItemSlotType>> filterType2SlotType;

	public UIButton btnSort
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized sortLabel
	{
		get
		{
			return null;
		}
	}

	public PanelItemListSort itemListSort
	{
		get
		{
			return null;
		}
	}

	public EnumData.ItemFilterType filterType
	{
		get
		{
			return EnumData.ItemFilterType.NONE;
		}
		private set
		{
		}
	}

	public EnumData.ItemFilterType[] filterTypes
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<EnumData.ItemSlotType> curFilterSlotTypes
	{
		get
		{
			return null;
		}
	}

	private void SetCurrentFilterLabel(EnumData.ItemFilterType itemType)
	{
	}

	private void Start()
	{
	}

	public void InitFileterType()
	{
	}

	public void ClickSortTypeBtn()
	{
	}

	public void ClearFilterTypes()
	{
	}

	private void MakeFilterSlotTypeDictionary(EnumData.ItemFilterType itemFilterType)
	{
	}

	public void CloseSortOrderPanel()
	{
	}

	private void FilterItems(EnumData.ItemFilterType sortType)
	{
	}

	public void Refresh()
	{
	}
}

using System;
using System.Collections.Generic;
using UnityEngine;

public class PanelPopupTreasureListFilter : CaocaoUIModalPopup
{
	private Dictionary<string, List<GameObject>> m_linkedBtn;

	public TreasureListFilter m_filter;

	private Action<TreasureListFilter> onClickConfirm__BackingField;

	private Action onCancel__BackingField;

	public EnumData.TreasureFilterMode m_mode;

	private const int ISALL = -1;

	public event Action<TreasureListFilter> onClickConfirm
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action onCancel
	{
		add
		{
		}
		remove
		{
		}
	}

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void UpdateUI()
	{
	}

	private void SetGroups()
	{
	}

	private void SetLockGroup(Transform parent)
	{
	}

	private void SetEnhanceGroup(Transform parent)
	{
	}

	private void SetSlotGroup(Transform parent)
	{
	}

	private void SetTypeGroup(Transform parent)
	{
	}

	private void SetPassvieEnhanceableGroup(Transform parent)
	{
	}

	private void AddButton(Transform parent, EnumData.TreasureFilterType filterType, int data, bool isBtnAll, string label, bool enable = true)
	{
	}

	private void ClearTable(UITable table)
	{
	}

	private void ClearGrid(UIGrid grid)
	{
	}

	private void SetFilterInfo()
	{
	}

	public static string GetFilterKey(EnumData.TreasureFilterMode mode)
	{
		return null;
	}

	private void GetFilterInfo()
	{
	}

	private List<int> GetFilterTypeData(EnumData.TreasureFilterType filterType)
	{
		return null;
	}

	private void VaildCheck()
	{
	}

	private bool IsSelected(EnumData.TreasureFilterType filterType, int data)
	{
		return false;
	}

	private void ApplyFilter(EnumData.TreasureFilterType filterType, int data, bool isSelected)
	{
	}

	private void ApplySelected(EnumData.TreasureFilterType filterType, int data, bool isSelected, List<int> list)
	{
	}

	private void ApplyAllCateroty(EnumData.TreasureFilterType filterType, bool isAllSelected, List<int> list)
	{
	}

	private void UpdateButton(EnumData.TreasureFilterType filterType, List<int> list)
	{
	}

	private void AddCategoryObject(string key, GameObject obj)
	{
	}

	private bool IsMaxCategory(EnumData.TreasureFilterType filterType, List<int> list)
	{
		return false;
	}

	private void OnClickButton(EnumData.TreasureFilterType filterType, int data, bool isSelected)
	{
	}

	public void OnClickConfirm()
	{
	}

	public void OnClickCancel()
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
}

using System;
using System.Collections.Generic;
using UnityEngine;

public class PanelPopupRuneListFilter : CaocaoUIModalPopup
{
	private RuneListFilter m_filter;

	private Dictionary<string, int> m_maxCount;

	private Dictionary<string, List<GameObject>> m_linkedBtn;

	private Action<RuneListFilter> onClickConfirm__BackingField;

	private Action onCancel__BackingField;

	public EnumData.RuneFilterMode mode;

	private const int ISALL = -1;

	public event Action<RuneListFilter> onClickConfirm
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

	private void SetGroups(Transform parent)
	{
	}

	private void SetTierGroup(Transform parent)
	{
	}

	private void SetRaidGroup(Transform parent)
	{
	}

	private void SetClassGroup(Transform parent)
	{
	}

	private void SetPassiveGroup(Transform parent)
	{
	}

	private void SetCraftGroup(Transform parent)
	{
	}

	private void SetButtons()
	{
	}

	private void AddButton(Transform parent, EnumData.RuneFilterType filterType, int data, bool isBtnAll, string label)
	{
	}

	private void ClearTable(UITable table)
	{
	}

	private void ClearGrid(UIGrid grid)
	{
	}

	private bool IsSelected(EnumData.RuneFilterType filterType, int data)
	{
		return false;
	}

	private void ApplyFilter(EnumData.RuneFilterType filterType, int data, bool isSelected)
	{
	}

	private void ApplySelected(EnumData.RuneFilterType filterType, int data, bool isSelected, List<int> list)
	{
	}

	private void ApplyAllCateroty(EnumData.RuneFilterType filterType, bool isAllSelected, List<int> list)
	{
	}

	private void UpdateButton(EnumData.RuneFilterType filterType, List<int> list)
	{
	}

	private void AddCategoryInfo(EnumData.RuneFilterType filterType, int data, GameObject obj)
	{
	}

	private void AddCategoryCount(string key)
	{
	}

	private void AddCategoryObject(string key, GameObject obj)
	{
	}

	private bool IsMaxCategory(EnumData.RuneFilterType filterType, List<int> list)
	{
		return false;
	}

	private void GetFilterInfo()
	{
	}

	private List<int> GetFilterTypeData(EnumData.RuneFilterType filterType)
	{
		return null;
	}

	private void VaildCheck()
	{
	}

	private void SetFilterInfo()
	{
	}

	private string GetFilterKey()
	{
		return null;
	}

	private string GetTierLabel(int data)
	{
		return null;
	}

	private string GetRaidLabel(int type)
	{
		return null;
	}

	private void OnClickButton(EnumData.RuneFilterType filterType, int data, bool isSelected)
	{
	}

	public void OnClickConfirm()
	{
	}

	public void OnClickCancel()
	{
	}

	public void OnClickDecomposition()
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

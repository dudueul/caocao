using System;
using System.Collections.Generic;
using UnityEngine;

public class PanelPopupEventListFilter : CaocaoUIModalPopup
{
	private EventListFilter m_filter;

	private Dictionary<string, int> m_maxCount;

	private Dictionary<string, List<GameObject>> m_linkedBtn;

	private int m_btnYPos;

	private readonly Vector2 startPos;

	private readonly Vector2 interval;

	private readonly int MaxHorizonCount;

	private Action<EventListFilter> onClickConfirm__BackingField;

	private Action onCancel__BackingField;

	public event Action<EventListFilter> onClickConfirm
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

	private void UpdateUI()
	{
	}

	private void SetEnemyLevelBtns()
	{
	}

	private void SetRewardBtns()
	{
	}

	private void AddButton(Transform parent, EnumData.EventFilterType filterType, int data, bool isBtnAll, string label, int posX, int posY, int maxLineCount = 0)
	{
	}

	private bool IsSelected(EnumData.EventFilterType filterType, int data)
	{
		return false;
	}

	private void ApplyFilter(EnumData.EventFilterType filterType, int data, bool isSelected)
	{
	}

	private List<int> GetFilterTypeData(EnumData.EventFilterType filterType)
	{
		return null;
	}

	private void ApplyAllCateroty(EnumData.EventFilterType filterType, bool isAllSelected, List<int> list)
	{
	}

	private void ApplySelected(EnumData.EventFilterType filterType, int data, bool isSelected, List<int> list)
	{
	}

	private bool IsMaxCategory(EnumData.EventFilterType filterType, List<int> list)
	{
		return false;
	}

	private void AddCategoryInfo(EnumData.EventFilterType filterType, int data, GameObject obj)
	{
	}

	private void AddCategoryCount(string key)
	{
	}

	private void AddCategoryObject(string key, GameObject obj)
	{
	}

	private void UpdateButton(EnumData.EventFilterType filterType, List<int> list)
	{
	}

	private void GetFilterInfo()
	{
	}

	private void VaildCheck()
	{
	}

	private void SetFilterInfo()
	{
	}

	private void OnClickButton(EnumData.EventFilterType filterType, int data, bool isSelected)
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

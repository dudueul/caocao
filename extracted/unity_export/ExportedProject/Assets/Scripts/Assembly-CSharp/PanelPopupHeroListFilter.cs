using System;
using System.Collections.Generic;
using UnityEngine;

public class PanelPopupHeroListFilter : CaocaoUIModalPopup
{
	private HeroListFilter m_filter;

	private Dictionary<string, int> m_maxCount;

	private Dictionary<string, List<GameObject>> m_linkedBtn;

	private readonly Vector2 startPos;

	private readonly Vector2 interval;

	private readonly int groupStartPosY;

	private const int MaxHorizonCount = 6;

	private int m_currentHeight;

	private Action<HeroListFilter> onClickConfirm__BackingField;

	private Action onCancel__BackingField;

	public EnumData.FilterMode mode;

	public event Action<HeroListFilter> onClickConfirm
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

	private void UpdateUI(EnumData.FilterMode mode)
	{
	}

	private void SetButtons(Transform parent, EnumData.FilterMode mode)
	{
	}

	private void SetUnitStateBtns(Transform parent)
	{
	}

	private void SetEntryBtns(Transform parent)
	{
	}

	private void SetGenderBtns(Transform parent)
	{
	}

	private void SetRelationBtns(Transform parent)
	{
	}

	private void SetAlarmBtns(Transform parent)
	{
	}

	private void SetGradeBtns(Transform parent)
	{
	}

	private void SetArrayBtns(Transform parent, EnumData.DeckFilterType filterType, string key, int total)
	{
	}

	private void SetJobTypeBtns(Transform parent)
	{
	}

	private void SetTreeBtns(Transform parent)
	{
	}

	private void AddButton(Transform parent, EnumData.DeckFilterType filterType, int data, bool isBtnAll, string label, int posX, int posY)
	{
	}

	private bool IsSelected(EnumData.DeckFilterType filterType, int data)
	{
		return false;
	}

	private void ApplyFilter(EnumData.DeckFilterType filterType, int data, bool isSelected)
	{
	}

	private void ApplySelected(EnumData.DeckFilterType filterType, int data, bool isSelected, List<int> list)
	{
	}

	private void ApplyAllCateroty(EnumData.DeckFilterType filterType, bool isAllSelected, List<int> list)
	{
	}

	private void UpdateButton(EnumData.DeckFilterType filterType, List<int> list)
	{
	}

	private void AddCategoryInfo(EnumData.DeckFilterType filterType, int data, GameObject obj)
	{
	}

	private void AddCategoryCount(string key)
	{
	}

	private void AddCategoryObject(string key, GameObject obj)
	{
	}

	private bool IsMaxCategory(EnumData.DeckFilterType filterType, List<int> list)
	{
		return false;
	}

	private void GetFilterInfo()
	{
	}

	private List<int> GetFilterTypeData(EnumData.DeckFilterType filterType)
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

	private void OnClickButton(EnumData.DeckFilterType filterType, int data, bool isSelected)
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

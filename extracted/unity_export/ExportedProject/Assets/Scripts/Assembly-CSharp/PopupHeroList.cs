using System.Collections.Generic;
using UnityEngine;

public class PopupHeroList : CaocaoUIModalPopup
{
	public enum UnitInfoCategory
	{
		Basic = 0,
		Troops = 1,
		General = 2,
		Item = 3
	}

	private UserUnit m_userUnit;

	public Dictionary<UnitInfoCategory, EnumData.SortType[]> m_sortTypeByCategory;

	public Transform m_unitListScrollView;

	public Transform[] m_sortingBtn;

	private static UnitInfoCategory m_unitInfoCategory;

	private HeroListFilter m_filter;

	private bool m_turnOnFilter;

	public UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private GameObject m_unitInfoCategoryPopup
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_unitInfoCategoryLabel
	{
		get
		{
			return null;
		}
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

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	public void ShowPanel(UserUnit userUnit)
	{
	}

	private void InitCategory()
	{
	}

	private void UpdateUI()
	{
	}

	private void SelectUnit(UserUnit userUnit)
	{
	}

	private void RefreshHeroInfoUI(string uiName, UserUnit userUnit)
	{
	}

	private void InitSortingBtns()
	{
	}

	private void ToggleSortingBtn(Transform tr)
	{
	}

	public void ShowUnitInfoCategory()
	{
	}

	public void SetCatetory(UnitInfoCategory category)
	{
	}

	private void UpdateCategory()
	{
	}

	private string GetCategoryString(UnitInfoCategory category)
	{
		return null;
	}

	public void ShowFilterPopup()
	{
	}

	private void ShowFilter()
	{
	}

	private void OnApplyFilter(HeroListFilter filter)
	{
	}

	public void OnFilterCancel()
	{
	}

	private void ToggleFilter()
	{
	}

	private void GetFilter()
	{
	}

	private void SetFilterBtn()
	{
	}

	public void ApplyFilter()
	{
	}

	private void RecoverHeroListFromFilter()
	{
	}

	private List<UserUnit> SortUnits(List<UserUnit> userUnits)
	{
		return null;
	}

	private bool IsCheckedFilter(UserUnit userUnit)
	{
		return false;
	}

	private bool IsDefenseDeck(int unitId)
	{
		return false;
	}
}

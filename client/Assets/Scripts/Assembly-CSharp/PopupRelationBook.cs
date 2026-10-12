using System.Collections.Generic;
using UnityEngine;

public class PopupRelationBook : CaocaoUIModalPopup
{
	private enum FilterType
	{
		All = 0,
		DontHave = 1,
		Doing = 2,
		CompleteAble = 3,
		Complete = 4,
		Num = 5
	}

	private const int RelationItemID = 3100207;

	private FilterType m_filter;

	private string searchUnitName;

	private FilterType prevFilter;

	private bool initiatedFilterGrid;

	private List<RelationList> filteredRelations;

	private int scrollIndex;

	private UILabelLocalized m_filterLabel;

	private Transform m_listSort;

	private UIGrid m_filterGrid;

	private UISprite m_filterGridBg;

	private GameObject m_searchUnitBtn;

	private GameObject m_cancelSearchUnitBtn;

	private UILabelLocalized m_stringItemLabel;

	private UIReuseGrid m_grid;

	private FilterType filter
	{
		get
		{
			return FilterType.All;
		}
		set
		{
		}
	}

	private UILabelLocalized filterLabel
	{
		get
		{
			return null;
		}
	}

	private Transform listSort
	{
		get
		{
			return null;
		}
	}

	private UIGrid filterGrid
	{
		get
		{
			return null;
		}
	}

	private UISprite filterGridBg
	{
		get
		{
			return null;
		}
	}

	private GameObject searchUnitBtn
	{
		get
		{
			return null;
		}
	}

	private GameObject cancelSearchUnitBtn
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized stringItemLabel
	{
		get
		{
			return null;
		}
	}

	private UIReuseGrid grid
	{
		get
		{
			return null;
		}
	}

	private void InvalidateFilter()
	{
	}

	public static void Show(int relationId = 0)
	{
	}

	private void Init()
	{
	}

	public void OnOpenFilterButton()
	{
	}

	public void OnFilterListItemButton()
	{
	}

	public void OnSearchUnitButton()
	{
	}

	private void OpenSearchUnitPopup()
	{
	}

	public void OnCancelSearchUnitButton()
	{
	}

	private void CancelSearchUnit()
	{
	}

	private void UpdateStringItemLabel()
	{
	}

	private void OnDeserializeStringItem()
	{
	}

	private void UpdateUI()
	{
	}

	private bool UpdateFilteredRelationList()
	{
		return false;
	}

	private void UpdateFilterLabel()
	{
	}

	private string GetFilterString(FilterType filterType)
	{
		return null;
	}

	private void ScrollToRelation(int relationId)
	{
	}

	private void Update()
	{
	}

	protected override void OnDestroy()
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}

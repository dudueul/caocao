using System.Collections.Generic;
using UnityEngine;

public class PopupTreasureCreatePassiveList : CaocaoUIModalPopup
{
	private enum EnhanceType
	{
		None = 0,
		Attack = 1,
		Skill = 2,
		Hybrid = 3
	}

	private EnhanceType filterType;

	private EnhanceType[] enhanceTypeFlag;

	private Dictionary<int, CraftTreasurePassive> dicCraftPassive;

	private UILabelLocalized m_labelSortType;

	private GameObject m_filterList;

	private UISprite m_bgFilter;

	private UIGrid m_gridFilter;

	private UIReuseGrid m_grid;

	private UILabelLocalized labelSortType
	{
		get
		{
			return null;
		}
	}

	private GameObject filterList
	{
		get
		{
			return null;
		}
	}

	private UISprite bgFilter
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridFilter
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

	public static void Show(UserItem selectedItem)
	{
	}

	private void InitUI(UserItem selectedItem)
	{
	}

	private void UpdateUI()
	{
	}

	public void OnFilterButton()
	{
	}

	private string GetEnhanceTypeText(EnhanceType enhanceType)
	{
		return null;
	}

	protected override void OnDestroy()
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

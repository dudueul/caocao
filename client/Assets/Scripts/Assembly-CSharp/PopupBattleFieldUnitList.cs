using System;
using System.Collections.Generic;
using UnityEngine;

public class PopupBattleFieldUnitList : MonoBehaviour
{
	[SerializeField]
	private UIReuseScrollView m_reuseScrollView;

	[SerializeField]
	private UIReuseGrid m_reuseGrid;

	[SerializeField]
	private UILabelLocalized m_sortName;

	[SerializeField]
	private Transform m_transBtnFilter;

	[SerializeField]
	private CaocaoTabButtons m_tabButton;

	[SerializeField]
	private UILabelLocalized m_btnDefenseText;

	[SerializeField]
	private UIButton m_btnDefense;

	[SerializeField]
	private UILabelLocalized m_unitNumText;

	[SerializeField]
	private UILabelLocalized m_defenseDeckPower;

	private Action<bool> m_closeCallBack;

	private Action<ulong, HeroListItem> m_listItemCallBack;

	private int m_sortTileId;

	private string m_curTabBtn;

	private int m_prevUnitId;

	public Action<bool> closeCallBack
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Action<ulong, HeroListItem> listItemCallBack
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void Awake()
	{
	}

	public void InitUI()
	{
	}

	public void UpdateCellData()
	{
	}

	public void PopupActive(bool active)
	{
	}

	private void UpdateUnitGrid()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateBtnUI(int unitId)
	{
	}

	private void UpdateTabUI()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	private List<UserUnit> SortUnits(List<UserUnit> userUnits)
	{
		return null;
	}

	public void OnClickHeroListItem(ulong srl, HeroListItem item, bool scrollToUnit = false, int idx = -1)
	{
	}

	public void OnClose()
	{
	}

	public void OnClickDefense()
	{
	}

	public void OnClickBtnAutoAssignCancel()
	{
	}

	public void OnClickBtnAutoAssign()
	{
	}

	public void OnClickQuickDefense()
	{
	}
}

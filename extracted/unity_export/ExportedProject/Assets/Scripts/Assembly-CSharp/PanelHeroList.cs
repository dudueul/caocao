using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class PanelHeroList : SelfRegister2UIComponentsMonobehaviour
{
	public UIButton m_btnSortType;

	public UIButton m_btnDescend;

	public UIButton m_btnCancel;

	private UILabelLocalized m_labelSortType;

	private UILabelLocalized m_labelDescend;

	private EnumData.SortType m_curSortType;

	private bool m_isDescend;

	private void Start()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void ShowDescendText()
	{
	}

	public void ShowSortTypePanel()
	{
	}

	private void SortOrder(Enum sortType)
	{
	}

	public void UpdateUI()
	{
	}

	public void Close()
	{
	}

	private void RemoveUnitList()
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateGridCoroutine()
	{
		return null;
	}

	private List<UserUnit> GetOrderedUnits(UserUnits userUnits)
	{
		return null;
	}
}

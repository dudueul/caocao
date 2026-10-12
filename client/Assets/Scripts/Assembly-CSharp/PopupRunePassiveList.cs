using System;
using UnityEngine;

public class PopupRunePassiveList : CaocaoUIModalPopup
{
	private UserUnit unit;

	private int runeSlotIndex;

	private Action callback;

	private EnumData.RaidType curTab;

	[SerializeField]
	private UIReuseGrid m_grid;

	[SerializeField]
	private UIReuseScrollView m_scrollView;

	[SerializeField]
	private PanelUnitRune m_curUnitRune;

	[SerializeField]
	private CaocaoTabButtons m_tabButton;

	public static void Show(UserUnit unit, int runeSlotIndex, Action callback)
	{
	}

	private void OnTabChanged(CaocaoButton btn)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdatePassiveListUI()
	{
	}

	private void ResetGridScroll()
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

	protected override void OnDestroy()
	{
	}
}

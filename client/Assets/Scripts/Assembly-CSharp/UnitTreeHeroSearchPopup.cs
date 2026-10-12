using System;
using System.Collections.Generic;
using UnityEngine;

public class UnitTreeHeroSearchPopup : CaocaoUIModalPopup
{
	public enum Mode
	{
		Search = 0,
		Overlap = 1
	}

	public UIInput inputUnitName;

	public UIGrid grid;

	private Action onYes;

	private Action onCancel;

	private bool isCancel;

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

	public static UnitTreeHeroSearchPopup Show(Mode mode, Action onYes, Action onCancel)
	{
		return null;
	}

	public void SetGrid(List<GameObject> lstItem)
	{
	}

	public void OnYes()
	{
	}

	public void OnCancel()
	{
	}
}

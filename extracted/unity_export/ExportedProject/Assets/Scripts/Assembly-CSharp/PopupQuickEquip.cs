using System;
using LitJson;
using UnityEngine;

public class PopupQuickEquip : CaocaoUIModalPopup
{
	public GameObject gridSlots;

	public UILabelLocalized labelJob;

	private UserUnit userUnit;

	private Action updateCallback;

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

	public static void Show(UserUnit userUnit, Action updateCallback)
	{
	}

	private void UpdateUI()
	{
	}

	private void OnSelectNGUI(GameObject go, bool selected)
	{
	}

	private void OnEquipQuickSlotItem(JsonData response)
	{
	}

	private void OnUpdatedQuickSlotUI()
	{
	}

	protected override void OnDestroy()
	{
	}
}

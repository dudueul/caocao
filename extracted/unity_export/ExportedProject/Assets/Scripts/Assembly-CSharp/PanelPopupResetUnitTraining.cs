using System;
using LitJson;

public class PanelPopupResetUnitTraining : PanelPopup2Buttons
{
	private Action m_callback;

	private int m_unitId;

	public static void Show(UserUnit userUnit, Action callback)
	{
	}

	protected override void OnSetContext()
	{
	}

	private PopupState OnOkClicked()
	{
		return PopupState.KeepOpen;
	}

	private PopupState OnCancelClicked()
	{
		return PopupState.KeepOpen;
	}

	private void ShowConfirmPopup()
	{
	}

	private void OnResetUnitTraining(JsonData response)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}
}

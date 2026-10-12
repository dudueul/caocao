using System;
using System.Text;
using LitJson;

public class PanelPopupResetClassicScenario : PanelPopup2Buttons
{
	private Action m_callback;

	private StringBuilder ms_stringBuilder;

	private void OnResetClassicScenario(JsonData response)
	{
	}

	public static void Show(Action callback)
	{
	}

	protected override void OnSetContext()
	{
	}

	private PopupState OnYesPressed()
	{
		return PopupState.KeepOpen;
	}

	private PopupState OnCancelPressed()
	{
		return PopupState.KeepOpen;
	}

	private void ShowCantConsumePopup()
	{
	}
}

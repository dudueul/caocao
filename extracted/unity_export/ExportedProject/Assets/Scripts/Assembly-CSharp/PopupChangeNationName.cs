using System;
using LitJson;

public class PopupChangeNationName : PanelPopup2Buttons
{
	public int m_needGold;

	private bool m_wait;

	private UIInput m_input;

	private bool m_isTutorial;

	public Action m_closeCallback;

	public override void OnTappingOutSide()
	{
	}

	protected override void OnSetContext()
	{
	}

	public void Apply(string nationName, int needGold, bool isTutorial, Action closeCallback = null)
	{
	}

	private void ChangeNationName()
	{
	}

	private void OnRequestChangeNationName(JsonData response)
	{
	}

	private bool IsValidName(string str)
	{
		return false;
	}

	private string FilterWithUnicodeRange(string str)
	{
		return null;
	}
}

using System;

public class PanelPopup1BtnConfirm : PanelPopup2Buttons
{
	private string m_title;

	private string m_confirm;

	private string m_desc;

	private object[] m_params;

	protected override void OnSetContext()
	{
	}

	public void Apply(string title, string desc, params object[] param)
	{
	}

	public void Apply(string title, string confirm, string desc, params object[] param)
	{
	}

	public static void ShowPopup(string title, string desc, Action confirmCallback, IDepthManagerblePopup parentPopup = null, bool ignoreTappingOutside = false)
	{
	}

	public static void ShowUrgentPopup(string title, Action confirmCallback, string desc, params object[] param)
	{
	}

	public static PanelPopup1BtnConfirm ShowPopup(string title, string confirm, string desc, Action confirmCallback, IDepthManagerblePopup parentPopup = null, bool ignoreTappingOutside = false)
	{
		return null;
	}
}

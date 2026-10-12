using System;

public class PanelPopupCenterText : CaocaoUIModalPopup
{
	public Action OnUpdateOnce;

	public Action OnClose;

	private bool noHide;

	protected override void Awake()
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

	protected override void OnDestroy()
	{
	}

	public static void Show(string msgFormat, float duration, EnumData.PanelPopupCenterTextColor panelPopupCenterTextColor, params object[] args)
	{
	}

	public static void ShowInputIgnored(string msgFormat, float duration, EnumData.PanelPopupCenterTextColor panelPopupCenterTextColor, params object[] args)
	{
	}

	public static void ShowCenterMiddle(string msgFormat, float duration, EnumData.PanelPopupCenterTextColor panelPopupCenterTextColor, params object[] args)
	{
	}

	public static PanelPopupCenterText ShowAndNoHide(string msgFormat, EnumData.PanelPopupCenterTextColor panelPopupCenterTextColor, params object[] args)
	{
		return null;
	}

	public void UpdateUI(string msgFormat, float duration, EnumData.PanelPopupCenterTextColor panelPopupCenterTextColor, params object[] args)
	{
	}

	private void Update()
	{
	}
}

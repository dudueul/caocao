using System;

public class PopupLikeAbility : CaocaoUIModalPopup
{
	public UITexture m_faceTex;

	public UILabelLocalized m_dialog;

	public UILabelLocalized m_relationTitle;

	public UISlider m_slider;

	public UISprite m_sliderBarImg;

	public static int m_beforeValue;

	private int m_afterValue;

	private Action OnAutoClose;

	private const float m_fadeTimeInterval = 0.1f;

	public static PopupLikeAbility CreateInstance()
	{
		return null;
	}

	public void ShowWindow(int saveFlagId, string dialog, string relationTitle, Action onAutoClose)
	{
	}

	public void MoveGauge()
	{
	}

	private void OnGaugeUpdate(float value)
	{
	}

	public void ForcedComplete()
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

	private void OnComplete()
	{
	}
}

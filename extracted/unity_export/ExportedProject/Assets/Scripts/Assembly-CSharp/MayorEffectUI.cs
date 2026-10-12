using System;

public class MayorEffectUI : CaocaoUIModalPopup
{
	private UserUnit m_userUnit;

	private UserTown m_userTown;

	private MayorEffect m_mayorEffect;

	private bool m_autoPopup;

	public Action<MayorEffectUI> OnClickHandler;

	public MayorEffect mayorEffect
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private UISprite m_icon
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_level
	{
		get
		{
			return null;
		}
	}

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

	public void SetMayorEffect(UserUnit userUnit, UserTown userTown, bool autoPopup = true)
	{
	}

	private void UpdateUI()
	{
	}

	public void SetUnknown()
	{
	}

	public void OnClick()
	{
	}
}

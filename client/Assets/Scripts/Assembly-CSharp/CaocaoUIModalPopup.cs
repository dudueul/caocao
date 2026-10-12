public abstract class CaocaoUIModalPopup : PanelSelfRegister2UIComponentsMonobehaviour, IDepthManagerblePopup
{
	private bool m_hasVisibleBg;

	private bool m_hasBg;

	private DepthManager.Grade m_depthGrade;

	protected bool m_destoryWhenClose;

	public bool HasVisibleBg
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool HasBg
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool IgnoreTappingOutSide { get; set; }

	public bool PrevUseInputState { get; set; }

	public DepthManager.Grade DepthGrade
	{
		get
		{
			return (DepthManager.Grade)0;
		}
		set
		{
		}
	}

	public IDepthManagerblePopup parent { get; set; }

	public abstract void OnFocusOn();

	public abstract void OnFocusOut();

	public abstract void OnTappingOutSide();

	protected abstract void ShowPanelImpl();

	public new virtual void ShowPanel()
	{
	}

	public void ShowNoBgPanel()
	{
	}

	public void ShowInvisibleBgPanel()
	{
	}

	public void Embed()
	{
	}

	protected abstract void ClosePanelImpl();

	public new virtual void ClosePanel()
	{
	}
}

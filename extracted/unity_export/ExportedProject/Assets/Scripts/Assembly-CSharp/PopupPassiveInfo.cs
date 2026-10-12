public class PopupPassiveInfo : CaocaoUIModalPopup
{
	private UserUnit m_userUnit;

	private Passive m_passive;

	private int m_needLv;

	private int m_needJobLv;

	private int m_passiveValue;

	private bool m_isAcquired;

	private bool m_canChange;

	private PassiveTermsListItem m_termsListItem;

	private ItemDescListItem m_descListItem;

	private UISprite m_icon
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_name
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_type
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_acquired
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_btnLabel
	{
		get
		{
			return null;
		}
	}

	private UIScrollView m_scrollView
	{
		get
		{
			return null;
		}
	}

	protected override void Awake()
	{
	}

	public static void Show(UserUnit userUnit, int needLv, int needJobLv, Passive passive, int passiveValue, bool isAcquired, bool canChange)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	private void InitUI()
	{
	}

	private void UpdateUI()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void ChangePassive()
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
}

public class PopupUnitHistory : CaocaoUIModalPopup
{
	private UserUnit m_userUnit;

	public PrefabInstance m_unitProfileInst;

	public UILabelLocalized m_unitDescrUI;

	public UISprite m_troopMarkUI;

	public PrefabInstance m_jobStyleInst;

	public UILabelLocalized m_jobDescrUI;

	public PrefabInstance m_jobLevelInst;

	public UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	protected override void Awake()
	{
	}

	public static void Show(UserUnit userUnit)
	{
	}

	public void ShowPanel(UserUnit userUnit)
	{
	}

	private void UpdateUI()
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
}

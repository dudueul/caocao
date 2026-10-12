public class PopupBattleMapStart : CaocaoUIModalPopup
{
	private int relationid;

	private string scenarioName;

	private string battleMapImg;

	private string desc;

	private int battleMapId;

	private UILabelLocalized m_titleLabel;

	private UILabelLocalized m_descLabel;

	private UI2DSprite m_mapImg;

	private UILabelLocalized m_winCondition;

	private UILabelLocalized m_loseCondition;

	private UILabelLocalized titleLabel
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized descLabel
	{
		get
		{
			return null;
		}
	}

	private UI2DSprite mapImg
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized winCondition
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized loseCondition
	{
		get
		{
			return null;
		}
	}

	public static void Show(int relationid, string scenarioName, string battleMapImg, string desc, int battleMapId)
	{
	}

	private void UpdateUI()
	{
	}

	public void OnDeckButton()
	{
	}

	private void OpenBattleDeck()
	{
	}

	protected override void OnDestroy()
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
}

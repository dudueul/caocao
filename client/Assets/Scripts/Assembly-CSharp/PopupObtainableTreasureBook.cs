using TreasureBook;

public class PopupObtainableTreasureBook : CaocaoUIModalPopup
{
	private ObtainableBookMode m_mode;

	public UIButton m_btnAll;

	public UIButton m_btnWeapon;

	public UIButton m_btnArmor;

	public UIButton m_btnKit;

	public TooltipContentsGenerator m_itemInfo;

	public UIReuseScrollView m_scrollView;

	public UIReuseGrid m_grid;

	protected override void Awake()
	{
	}

	public static void Show(ObtainableBookMode mode)
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

	protected override void ClosePanelImpl()
	{
	}

	protected override void ShowPanelImpl()
	{
	}
}

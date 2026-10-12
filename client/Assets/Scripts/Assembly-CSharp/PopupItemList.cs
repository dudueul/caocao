public class PopupItemList : CaocaoUIModalPopup
{
	private enum Tab
	{
		Recovery = 0,
		Skill = 1,
		Special = 2,
		All = 3
	}

	public UIReuseScrollView scrollView;

	public UIReuseGrid grid;

	public CaocaoTabButtons tabButton;

	private UserItem m_selectedItem;

	private int m_selectedIndex;

	private Tab currentTab;

	private static PopupItemList instance;

	public UserUnit userUnit { get; private set; }

	public UnitData unitData { get; private set; }

	public static void Show()
	{
	}

	public static void Show(UserUnit userUnit)
	{
	}

	public static void Show(UnitData unitData)
	{
	}

	public static void Close()
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

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void ReOpen()
	{
	}

	private void OnItemClicked(ReuseItemList.ItemCellData item)
	{
	}

	private void OnItemDoubleClicked(ReuseItemList.ItemCellData item)
	{
	}

	public void UseItem()
	{
	}

	private void UseItemSkill(EnumData.Skills skillId)
	{
	}

	private void RemoveList()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	private void UpdateUI()
	{
	}

	private void AddGridItem(UserItem userItem, int index, ref bool focused)
	{
	}

	private void FailToUseItemSkill(string msg, params object[] args)
	{
	}
}

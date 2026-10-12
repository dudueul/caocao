public class PopupUnitTooltip : CaocaoUIModalPopup
{
	private UnitUI m_unitUI;

	private UILabelLocalized m_name;

	private UILabelLocalized m_jobType;

	private UILabelLocalized m_grade;

	private UnitDescListItem m_descListItem;

	private UnitStatListItem m_statListItem;

	private UnitSubRewardListItem m_subRewardListItem;

	private UIScrollView m_scrollView;

	public RewardInfo rewardInfo { get; set; }

	protected override void Awake()
	{
	}

	public static void ShowPopup(RewardInfo rewardInfo, int depth)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void OnDisable()
	{
	}

	private void InitUI()
	{
	}

	private void UpdateUI()
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

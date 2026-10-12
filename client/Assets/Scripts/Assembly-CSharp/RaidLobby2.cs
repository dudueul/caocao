using LitJson;
using UnityEngine;

public class RaidLobby2 : CaocaoUIPanel
{
	protected enum TabType
	{
		None = 0,
		BlueDragon = 1,
		VermilionBird = 2,
		WhiteTiger = 3,
		BlackTortoise = 4,
		Awaken = 5,
		Box = 6
	}

	private TabType m_curTabType;

	[SerializeField]
	private CaocaoTabButtons m_Tab;

	[SerializeField]
	private UILabelLocalized m_traceAmountLabel;

	[SerializeField]
	private RaidStuffUI m_steleUI;

	[SerializeField]
	private RaidStuffUI m_soulUI;

	[SerializeField]
	private RaidStuffUI m_traceUI;

	[SerializeField]
	private GameObject m_awakenNewtag;

	[SerializeField]
	private GameObject m_normalImageObj;

	[SerializeField]
	private UITexture m_normalImageTexture;

	[SerializeField]
	private GameObject m_normalBGObj;

	[SerializeField]
	private GameObject m_awakenImageObj;

	[SerializeField]
	private GameObject m_normalList;

	[SerializeField]
	private GameObject m_AwakenList;

	[SerializeField]
	private GameObject m_resummonObj;

	[SerializeField]
	private UIButton m_btnResummon;

	[SerializeField]
	private UILabelLocalized m_resummonPriceLabel;

	[SerializeField]
	private UILabelLocalized m_resummonDescLabel;

	[SerializeField]
	private UIButton m_btnAchievement;

	[SerializeField]
	private UIButton m_btnTreasure;

	[SerializeField]
	private UIButton m_btnBossInfo;

	[SerializeField]
	private UISprite m_sprBossInfo;

	[SerializeField]
	private GameObject m_goodsExMark;

	[SerializeField]
	private GameObject m_normalExMark;

	[SerializeField]
	private GameObject m_awakenExMark;

	[SerializeField]
	private RaidNormalListItem[] m_normalListItems;

	[SerializeField]
	private RaidAwakenListItem[] m_awakenListItems;

	[SerializeField]
	private GameObject m_awakenGaugeObj;

	[SerializeField]
	private UISlider m_awakenGaugeSlider;

	[SerializeField]
	private UILabelLocalized m_awakenGaugeLabel;

	[SerializeField]
	private GameObject m_btnReserveSetup;

	[SerializeField]
	private GameObject m_btnReserveInfo;

	[SerializeField]
	private RaidAwakenReserveInfoPopup m_reserveInfoObj;

	private UIButton m_raidArtificeButton;

	private bool m_initialized;

	private int m_initialIndex;

	private bool m_showWithAwaken;

	private static RaidLobby2 self;

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void UpdateTimer()
	{
	}

	private void UpdateUI()
	{
	}

	private void Initialize()
	{
	}

	private void InitializeObjects()
	{
	}

	private void InitializeTabButtons()
	{
	}

	private void onTabClicked(CaocaoButton button)
	{
	}

	private void OnArtificeInfoClicked()
	{
	}

	private void UpdateNewtag()
	{
	}

	private void UpdateTicketInfo()
	{
	}

	private void UpdateList()
	{
	}

	private void UpdateNormalImagePanel()
	{
	}

	private void UpdateResummonButton()
	{
	}

	private void UpdateBossInfoButtons()
	{
	}

	private void UpdateAwakenGauge()
	{
	}

	public void ResummonClick()
	{
	}

	public void UpdateOtherButtons()
	{
	}

	public void AchievementClicked()
	{
	}

	public void RaidTreasureClicked()
	{
	}

	public void RaidBossInfoClicked()
	{
	}

	public void RaidAwakenReserveSetupClicked()
	{
	}

	public void RaidAwakenReserveInfoClicked()
	{
	}

	public void RequestRaidInfos()
	{
	}

	public void RequestSummon(Raid raidData, int multiplier)
	{
	}

	public void RequestCancelSummon(Raid raidData)
	{
	}

	public void RequestRefreshSummon(Raid raidData)
	{
	}

	public void RequestRefreshEnter(Raid raidData, IRaidInfo raidInfo, int cost)
	{
	}

	private void OnResponseNetwork(JsonData response)
	{
	}

	private TabType GetOpenedTabType()
	{
		return TabType.None;
	}

	private string GetTabBtnName(TabType tabType)
	{
		return null;
	}

	protected override void OnDestroy()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public static void Show(bool showWithAwaken)
	{
	}

	public static void Show(EnumData.RaidType raidType)
	{
	}
}

using LitJson;
using UnityEngine;

public class PanelShamBattle : CaocaoUIModalPopup
{
	public enum ShamTab
	{
		Towns = 0,
		Rewards = 1
	}

	private ShamTab m_shamTab;

	public CaocaoRadioButton m_townsTabBtn;

	public CaocaoRadioButton m_rewardsTabBtn;

	public GameObject m_townsDesc;

	public GameObject m_rewardsDesc;

	public GameObject m_townCount;

	public UILabelLocalized m_townCountLabel;

	public UIGrid m_grid;

	public UIScrollView m_scollView;

	public UILabelLocalized m_remainedResetTime;

	public UILabelLocalized m_shamPoint;

	public UILabelLocalized m_resetTime;

	public UILabelLocalized m_winningRate;

	public UILabelLocalized m_win;

	public UILabelLocalized m_lose;

	public PopupGetShamReward m_rewardPopup;

	private ShamRewardListItem m_selectedRewardItem;

	public ShamRewardListItem selectedRewardItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static void Show()
	{
	}

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void InitTabBtn(CaocaoRadioButton tabBtn, ShamTab shamTab, float activeY, float inactiveY, int activeHeight, int inActiveHeight)
	{
	}

	private void UpdateUI()
	{
	}

	private void ClearDatas()
	{
	}

	private void UpdateTownsTab()
	{
	}

	private void UpdateRewardsTab()
	{
	}

	public void UpdateTownCountUI()
	{
	}

	private void UpdateCommonUI()
	{
	}

	private void Update()
	{
	}

	private void RequestShamInfos()
	{
	}

	private void OnRequestShamInfos(JsonData response)
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

using UnityEngine;

public class RaidAwakenListItem : MonoBehaviour
{
	private enum eCondition
	{
		None = 0,
		Opened = 1,
		NoGuild = 2,
		OpenedOther = 3
	}

	[SerializeField]
	private GameObject m_dimObj;

	[SerializeField]
	private UILabelLocalized m_nameLabel;

	[SerializeField]
	private GameObject m_stateObj;

	[SerializeField]
	private UILabelLocalized m_levelLabel;

	[SerializeField]
	private UILabelLocalized m_hpLabel;

	[SerializeField]
	private UISlider m_hpSlider;

	[SerializeField]
	private UITexture m_image;

	[SerializeField]
	private GameObject m_gaugeObj;

	[SerializeField]
	private UILabelLocalized m_gaugeTitleLabel;

	[SerializeField]
	private UISlider m_gaugeSlider;

	[SerializeField]
	private UILabelLocalized m_gaugeValueLabel;

	[SerializeField]
	private GameObject m_gaugeFxObj;

	[SerializeField]
	private GameObject m_descObj;

	[SerializeField]
	private UILabelLocalized m_descLabel;

	[SerializeField]
	private UIButton m_btnOpen;

	[SerializeField]
	private UILabelLocalized m_btnOpenLabel;

	[SerializeField]
	private UIButton m_btnEnter;

	[SerializeField]
	private UILabelLocalized m_btnEnterRemainLabel;

	[SerializeField]
	private UILabelLocalized m_btnEnterMaxLabel;

	[SerializeField]
	private UIButton m_btnEnterGold;

	[SerializeField]
	private UILabelLocalized m_btnEnterGoldCostLabel;

	[SerializeField]
	private UIButton m_btnScore;

	[SerializeField]
	private UILabelLocalized m_multiplierLabel;

	private PlayMakerFSM m_fsm;

	private eCondition m_condition;

	private RaidLobby2 m_parent;

	private int m_raidType;

	private Raid m_raidData;

	private UserRaidAwakenInfo m_info;

	public void Initialize(RaidLobby2 parent)
	{
	}

	public void UpdateUI(int raidType)
	{
	}

	public void UpdateTimer()
	{
	}

	private bool CheckExpired()
	{
		return false;
	}

	private void UpdateCondition()
	{
	}

	private void UpdateDim()
	{
	}

	private void UpdateState()
	{
	}

	private void UpdateGauge()
	{
	}

	private void UpdateExpire()
	{
	}

	private void UpdateImage()
	{
	}

	private void UpdateName()
	{
	}

	private void UpdateButtons()
	{
	}

	private void UpdateButtonOpen()
	{
	}

	private void UpdateButtonEnter()
	{
	}

	private void UpdateButtonEnterGold()
	{
	}

	private void UpdatebuttonScore()
	{
	}

	private void UpdateMultiplierLabel()
	{
	}

	public void OnBtnOpenClicked()
	{
	}

	public void OnBtnEnterClicked()
	{
	}

	private void ShowBattleDeck()
	{
	}

	public void OnBtnEnterGoldClicked()
	{
	}

	public void OnBtnScoreClicked()
	{
	}
}

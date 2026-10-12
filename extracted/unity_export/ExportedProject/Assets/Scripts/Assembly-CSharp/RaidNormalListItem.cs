using System;
using System.Collections.Generic;
using UnityEngine;

public class RaidNormalListItem : MonoBehaviour
{
	private enum eCondition
	{
		None = 0,
		NotEnoughDifficulty = 1,
		Opened = 2,
		OpenedOther = 3,
		Cleared = 4,
		ClearedOther = 5
	}

	[SerializeField]
	private UILabelLocalized m_nameLabel;

	[SerializeField]
	private GameObject m_stateObj;

	[SerializeField]
	private UILabelLocalized m_LevelLabel;

	[SerializeField]
	private UILabelLocalized m_hpLabel;

	[SerializeField]
	private UISlider m_hpSlider;

	[SerializeField]
	private GameObject m_descObj;

	[SerializeField]
	private UILabelLocalized m_descLabel;

	[SerializeField]
	private UIButton m_btnOpen;

	[SerializeField]
	private UILabelLocalized m_btnOpenCostLabel;

	[SerializeField]
	private UIButton m_btnEnter;

	[SerializeField]
	private UILabelLocalized m_btnEnterRemainLabel;

	[SerializeField]
	private UIButton m_btnEnterGold;

	[SerializeField]
	private UILabelLocalized m_btnEnterGoldCostLabel;

	[SerializeField]
	private UIButton m_btnGiveUp;

	[SerializeField]
	private UIButton m_btnAutoClear;

	[SerializeField]
	private UILabelLocalized m_btnAutoClearLabel;

	private RaidLobby2 m_parent;

	private Raid m_raidData;

	private UserRaidInfo m_info;

	private eCondition m_condition;

	private bool m_active;

	private Action m_updateUI;

	private List<RewardInfo> m_autoClearRewards;

	private bool m_availableAutoClear;

	public void Initialize(RaidLobby2 parent)
	{
	}

	public void UpdateUI(int raidType, int difficulty, bool active, Action updateUI)
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

	private void UpdateState()
	{
	}

	private void UpdateExpire()
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

	private void UpdateBUttonGiveUp()
	{
	}

	public void OnBtnOpenClicked()
	{
	}

	public void OnBtnEnterClicked()
	{
	}

	public void OnBtnAutoClearClicked()
	{
	}

	private void AnimateAutoClear(int fromValue, int toValue, float time = 1f)
	{
	}

	private void OnHPUpdate(float hp)
	{
	}

	private void OnHPCompleted(int hp)
	{
	}

	public void OnBtnEnterGoldClicked()
	{
	}

	public void OnBtnGiveUpClicked()
	{
	}
}

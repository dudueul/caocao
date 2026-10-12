using System;
using UnityEngine;

public class ShamRewardListItem : MonoBehaviour
{
	private RankingReward m_rankingReward;

	public UILabelLocalized m_rewardName;

	public UILabelLocalized m_pointLabel;

	public GameObject m_selected;

	private bool m_isSelected;

	public Action<ShamRewardListItem> OnClickHandler;

	public RankingReward rankingReward
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int point { get; private set; }

	public int rewardID { get; private set; }

	public int foodAmount { get; private set; }

	public bool isSelected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private void UpdateUI()
	{
	}
}

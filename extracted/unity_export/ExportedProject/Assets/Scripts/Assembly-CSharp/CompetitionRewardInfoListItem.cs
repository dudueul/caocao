using System.Collections.Generic;
using UnityEngine;

public class CompetitionRewardInfoListItem : MonoBehaviour
{
	[SerializeField]
	private GameObject m_rank1Go;

	[SerializeField]
	private GameObject m_rank2Go;

	[SerializeField]
	private GameObject m_tierGo;

	[SerializeField]
	private UILabelLocalized m_tierNameLabel;

	[SerializeField]
	private UITexture m_tierIconTexture;

	[SerializeField]
	private UILabelLocalized m_tierMinScoreLabel;

	[SerializeField]
	private GameObject m_rewardObj;

	[SerializeField]
	private Transform m_rewardGrid;

	[SerializeField]
	private GameObject m_fairRuleObj;

	[SerializeField]
	private Transform m_fairRuleGrid;

	[SerializeField]
	private UILabelLocalized[] m_fairRuleLabels;

	[SerializeField]
	private GameObject m_selectGo;

	public void SetRankingRewardInfo(RankingReward rankingReward, int myRank)
	{
	}

	public void SetTierRewardInfo(Tier tier, Tier myTier, int rank)
	{
	}

	public void UpdateReward(int rewardId, int type)
	{
	}

	private Dictionary<int, int> GetRewardTimeBoxes(int rewardId)
	{
		return null;
	}

	public void SetFairRule(Tier tier, bool isMyTier)
	{
	}
}

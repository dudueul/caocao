using UnityEngine;

public class LordGiftGridItem : MonoBehaviour
{
	private RankUpGiftReward m_rankupGiftData;

	[SerializeField]
	private UILabelLocalized m_titleLabel;

	[SerializeField]
	private UILabelLocalized m_rewardName;

	[SerializeField]
	private UISprite m_rewardGoodsIcon;

	[SerializeField]
	private GameObject m_receiveObj;

	[SerializeField]
	private GameObject m_rewardObj;

	public int rankupGiftId { get; private set; }

	public int rankupGiftIndex { get; private set; }

	public bool gotReward { get; private set; }

	public int lordRank { get; private set; }

	public int rewardId { get; private set; }

	public int rewardType { get; private set; }

	public bool didPurchase { get; private set; }

	public void SetInfo(RankUpGiftReward rankupGift, int rankupGiftIndex, bool gotReward, bool didPurchase)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateRewardUI(RewardInfo rewardInfo)
	{
	}

	public void GetReward()
	{
	}

	public void SetGotReward()
	{
	}
}

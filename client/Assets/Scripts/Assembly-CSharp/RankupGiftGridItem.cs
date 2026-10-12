using UnityEngine;

public class RankupGiftGridItem : MonoBehaviour
{
	private RankUpGiftReward m_rankupGiftData;

	public int rankupGiftId { get; private set; }

	public int rankupGiftIndex { get; private set; }

	public bool gotReward { get; private set; }

	public int lordRank { get; private set; }

	public int rewardId { get; private set; }

	public int rewardType { get; private set; }

	public int gold { get; private set; }

	public bool didPurchase { get; private set; }

	public void SetInfo(RankUpGiftReward rankupGift, int rankupGiftIndex, bool gotReward, bool didPurchase)
	{
	}

	private void UpdateUI()
	{
	}

	public void GetReward()
	{
	}

	public void SetGotReward()
	{
	}
}

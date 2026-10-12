using System;

[Serializable]
public class __TreasureChange
{
	public int id;

	public int tier;

	public int changeCostType;

	public int changeCost;

	public int tierPlusPer;

	public int tierKeepPer;

	public int tierKeepMinusPer;

	public int tierMinusPer;

	public int itemPer;

	public int itemPer2;

	public int minusRewardType;

	public int minusReward;

	public int itemRewardType;

	public int itemReward;

	public int itemRewardType2;

	public int itemReward2;

	public void CopyTo(TreasureChange dest)
	{
	}

	public void Reset()
	{
	}
}

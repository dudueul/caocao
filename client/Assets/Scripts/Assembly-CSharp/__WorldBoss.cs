using System;
using System.Collections.Generic;

[Serializable]
public class __WorldBoss
{
	public string name;

	public int id;

	public int bossUnitID;

	public int seasonId;

	public string bossHP;

	public int minReqTime;

	public int maxReqTime;

	public int waitingTime;

	public int coolTime;

	public int minActiveTime;

	public int activeTime;

	public int totalUncoverCount;

	public int personUncoverCount;

	public List<int> uncoverItemID1;

	public List<int> uncoverItemCount1;

	public List<int> uncoverFixedReward;

	public List<int> uncoverRndReward;

	public int battleMapID;

	public int reward1;

	public int rewardGoods;

	public int earlyClearRewardRate;

	public List<int> fixedBossPassive;

	public List<int> rndBossPassiveGroup;

	public int successRewardId;

	public string desc;

	public void CopyTo(WorldBoss dest)
	{
	}

	public void Reset()
	{
	}
}

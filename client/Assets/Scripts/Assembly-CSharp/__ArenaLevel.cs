using System;
using System.Collections.Generic;

[Serializable]
public class __ArenaLevel
{
	public string name;

	public int id;

	public int level;

	public int isLast;

	public int nextId;

	public int isBoss;

	public int unitLevel;

	public int rewardClear;

	public int rewardPerfect;

	public List<int> rewardMission;

	public int refreshType;

	public int refreshCost;

	public int refreshLimit;

	public int shopGroup;

	public int shopRate;

	public int shopCountMin;

	public int shopCountMax;

	public int shopUnitMin;

	public int shopUnitMax;

	public int shopRefreshCost;

	public int shopRefreshLimit;

	public int getCoinMin;

	public int getCoinMax;

	public void CopyTo(ArenaLevel dest)
	{
	}

	public void Reset()
	{
	}
}

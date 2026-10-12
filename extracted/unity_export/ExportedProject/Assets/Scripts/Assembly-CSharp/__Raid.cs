using System;
using System.Collections.Generic;

[Serializable]
public class __Raid
{
	public string name;

	public int id;

	public int raidType;

	public int useType;

	public int difficulty;

	public int entRank;

	public int unitCount;

	public int dayEnt;

	public int entCost;

	public int expireTime;

	public List<int> minActiveTime;

	public int unitId;

	public int unitLv;

	public int uiLv;

	public int battleMap;

	public int type0;

	public int reward0;

	public int steleType;

	public int steleReward;

	public int steleBonus;

	public int soulType;

	public int soulReward;

	public int soulBonus;

	public int entCount;

	public int recallType;

	public int recallCost;

	public int recallCount;

	public int Rage;

	public int initFlag;

	public int ticketCount;

	public void CopyTo(Raid dest)
	{
	}

	public void Reset()
	{
	}
}

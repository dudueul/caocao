using System;
using System.Collections.Generic;

[Serializable]
public class __GuildWeeklyMission
{
	public string name;

	public int id;

	public int isUsed;

	public int enableClassic;

	public int country;

	public int checkType;

	public List<int> checkVal;

	public List<int> checkCompare;

	public int countVal;

	public int maxCount;

	public int soloRewardType;

	public int soloReward;

	public int masterRewardType;

	public int masterReward;

	public int adminRewardType;

	public int adminReward;

	public int guildRewardType;

	public int guildReward;

	public int slot;

	public int order;

	public string desc;

	public string summary;

	public void CopyTo(GuildWeeklyMission dest)
	{
	}

	public void Reset()
	{
	}
}

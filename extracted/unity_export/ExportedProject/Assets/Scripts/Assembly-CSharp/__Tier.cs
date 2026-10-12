using System;
using System.Collections.Generic;

[Serializable]
public class __Tier
{
	public string name;

	public int id;

	public int type;

	public string icon;

	public int tier;

	public int minPoint;

	public int maxPoint;

	public int needBattleCount;

	public int initPoint;

	public int appearNPC;

	public int leagueId;

	public int npcSettingId;

	public int type0;

	public int reward0;

	public string startDate;

	public string endDate;

	public int type1;

	public int reward1;

	public List<int> resultTimeBox;

	public int defeatReward;

	public void CopyTo(Tier dest)
	{
	}

	public void Reset()
	{
	}
}

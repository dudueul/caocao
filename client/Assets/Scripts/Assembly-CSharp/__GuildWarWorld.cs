using System;
using System.Collections.Generic;

[Serializable]
public class __GuildWarWorld
{
	public string name;

	public int id;

	public int country;

	public int order;

	public int isFixedReward;

	public int combatStartTime;

	public int combatEndTime;

	public List<int> castleAddPoint;

	public List<int> battleAddPoint;

	public int reward;

	public void CopyTo(GuildWarWorld dest)
	{
	}

	public void Reset()
	{
	}
}

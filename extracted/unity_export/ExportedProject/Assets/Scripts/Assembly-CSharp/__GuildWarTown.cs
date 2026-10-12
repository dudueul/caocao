using System;
using System.Collections.Generic;

[Serializable]
public class __GuildWarTown
{
	public string name;

	public int id;

	public int grade;

	public int rewardRankPoint;

	public List<int> attackerAmount;

	public List<int> defenderAmount;

	public string mapScript;

	public List<int> mainTileType;

	public List<int> linkedTown;

	public int townRewardId;

	public int ownerCheck;

	public void CopyTo(GuildWarTown dest)
	{
	}

	public void Reset()
	{
	}
}

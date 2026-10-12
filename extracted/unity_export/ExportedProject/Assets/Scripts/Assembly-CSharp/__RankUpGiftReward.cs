using System;
using System.Collections.Generic;

[Serializable]
public class __RankUpGiftReward
{
	public int id;

	public int rankupgiftid;

	public int country;

	public List<int> lordRank;

	public List<int> rewardType;

	public List<int> reward;

	public List<string> atlas;

	public List<string> texture;

	public void CopyTo(RankUpGiftReward dest)
	{
	}

	public void Reset()
	{
	}
}

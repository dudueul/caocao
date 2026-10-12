using System;
using System.Collections.Generic;

[Serializable]
public class __SeasonReward
{
	public int id;

	public int seasonId;

	public int treasureType;

	public int slot;

	public int itemtier;

	public int replaceitemId;

	public int replaceitemCount;

	public List<int> itemId;

	public void CopyTo(SeasonReward dest)
	{
	}

	public void Reset()
	{
	}
}

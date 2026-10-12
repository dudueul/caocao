using System;
using System.Collections.Generic;

[Serializable]
public class __GuildSiegeObjectByLV
{
	public int id;

	public int objectType;

	public int targetUnit;

	public int unitLv;

	public int addStr;

	public int addCha;

	public int addWis;

	public int addDex;

	public int addLuck;

	public List<int> passiveList;

	public void CopyTo(GuildSiegeObjectByLV dest)
	{
	}

	public void Reset()
	{
	}
}

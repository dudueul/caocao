using System;
using System.Collections.Generic;

[Serializable]
public class __GuildSiegeTownFacilityByLV
{
	public int id;

	public int facilityLv;

	public int needGuildLv;

	public int expNeeded;

	public int expSum;

	public List<int> tower;

	public List<int> obstacle;

	public List<int> spawner;

	public List<int> spawnerSet;

	public int spcObject;

	public int spcSpawnerSet;

	public int commander;

	public List<int> battleRule;

	public List<int> defenderNPC;

	public void CopyTo(GuildSiegeTownFacilityByLV dest)
	{
	}

	public void Reset()
	{
	}
}

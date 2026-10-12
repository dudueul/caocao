using System;
using System.Collections.Generic;

[Serializable]
public class __GuildSiegeCommander
{
	public int id;

	public List<int> target;

	public List<int> passiveId;

	public List<int> passiveVal;

	public string desc;

	public void CopyTo(GuildSiegeCommander dest)
	{
	}

	public void Reset()
	{
	}
}

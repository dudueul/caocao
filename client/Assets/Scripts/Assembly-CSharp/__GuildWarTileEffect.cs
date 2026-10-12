using System;
using System.Collections.Generic;

[Serializable]
public class __GuildWarTileEffect
{
	public string name;

	public int id;

	public string tileEffectPrefab;

	public List<int> guildWarPassiveList;

	public int tileEffectDmgType;

	public int tileEffectDmgValue;

	public int tileEffectMinDmgRate;

	public int expireTime;

	public int isIgnited;

	public void CopyTo(GuildWarTileEffect dest)
	{
	}

	public void Reset()
	{
	}
}

using System;

[Serializable]
public class __Guild
{
	public int id;

	public int level;

	public int needExp;

	public int expSum;

	public int maxUser;

	public int maxGuest;

	public int guildShopGrade;

	public int supportGrade;

	public string bgTexture;

	public int canGuildWar;

	public int canGuildRaid;

	public void CopyTo(Guild dest)
	{
	}

	public void Reset()
	{
	}
}

using System;

[Serializable]
public class __GuildWarCombatMessage
{
	public int id;

	public string name;

	public int faceId;

	public string allyDesc;

	public string globalDesc;

	public int coolTime;

	public void CopyTo(GuildWarCombatMessage dest)
	{
	}

	public void Reset()
	{
	}
}

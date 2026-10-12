using System;
using System.Collections.Generic;

[Serializable]
public class __GuildWarPlace
{
	public string name;

	public int id;

	public int placeType;

	public int spawnPoint;

	public int linkedTownPlace;

	public int changeOwn;

	public List<int> buliding;

	public List<int> ratio;

	public void CopyTo(GuildWarPlace dest)
	{
	}

	public void Reset()
	{
	}
}

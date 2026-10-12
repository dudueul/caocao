using System;
using System.Collections.Generic;

[Serializable]
public class __GuildWarInteractiveFunction
{
	public int id;

	public int corpsExp;

	public int contributionPoint;

	public List<int> actionId;

	public List<int> isFire;

	public List<int> isWater;

	public List<int> isEarth;

	public List<int> isWind;

	public void CopyTo(GuildWarInteractiveFunction dest)
	{
	}

	public void Reset()
	{
	}
}

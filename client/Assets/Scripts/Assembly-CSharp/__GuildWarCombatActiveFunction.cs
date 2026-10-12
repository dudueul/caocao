using System;
using System.Collections.Generic;

[Serializable]
public class __GuildWarCombatActiveFunction
{
	public string name;

	public int id;

	public int functionCategory;

	public List<int> actionId;

	public int targetRange;

	public int targetType;

	public int effectArea;

	public int isEscort;

	public int isFire;

	public int isWater;

	public int isEarth;

	public int isWind;

	public int isRush;

	public int isWaterRush;

	public int corpsExp;

	public int contributionPoint;

	public int actionPoint;

	public int coolTime;

	public int functionTypeId;

	public string atlas;

	public string icon;

	public string desc;

	public void CopyTo(GuildWarCombatActiveFunction dest)
	{
	}

	public void Reset()
	{
	}
}

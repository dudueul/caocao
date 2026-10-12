using System;
using System.Collections.Generic;

[Serializable]
public class __GuildWarTileType
{
	public string name;

	public int id;

	public int canFire;

	public int canWater;

	public int canEarth;

	public int canWind;

	public int canSetSiegeWeapon;

	public int canRush;

	public int canWaterRush;

	public List<int> tileEffect;

	public int isWater;

	public int isIgnited;

	public int waterDefense;

	public int mountainDefense;

	public int desertDefense;

	public void CopyTo(GuildWarTileType dest)
	{
	}

	public void Reset()
	{
	}
}

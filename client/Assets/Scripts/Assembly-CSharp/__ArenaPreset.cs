using System;
using System.Collections.Generic;

[Serializable]
public class __ArenaPreset
{
	public int id;

	public string presetName;

	public int group;

	public int getUnit;

	public int ai;

	public int treasureTierMin;

	public int treasureTierMax;

	public List<int> weapon;

	public List<int> armor;

	public List<int> kit;

	public int passiveTierMin;

	public int passiveTierMax;

	public List<int> defaultPassive;

	public int commonPassiveCount;

	public List<int> commonPassive;

	public int jobPassiveCount;

	public List<int> jobPassive;

	public int autoPosition;

	public int costMin;

	public int costMax;

	public int shopRate;

	public int nextPreset;

	public int upgradeCost;

	public int rate;

	public void CopyTo(ArenaPreset dest)
	{
	}

	public void Reset()
	{
	}
}

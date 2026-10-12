using System;
using System.Collections.Generic;

[Serializable]
public class __ArenaScenario
{
	public string name;

	public int id;

	public int order;

	public int deckSetting;

	public int isLock;

	public int price;

	public int face;

	public List<int> unit;

	public List<int> preset;

	public string country;

	public string desc;

	public void CopyTo(ArenaScenario dest)
	{
	}

	public void Reset()
	{
	}
}

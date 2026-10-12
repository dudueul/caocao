using System;
using System.Collections.Generic;

[Serializable]
public class __UnitRank
{
	public string name;

	public int id;

	public int tier;

	public string icon;

	public int type;

	public int minPoint;

	public int rankValue;

	public int rankPercent;

	public List<int> rankUpItem;

	public List<int> rankUpItemCount;

	public void CopyTo(UnitRank dest)
	{
	}

	public void Reset()
	{
	}
}

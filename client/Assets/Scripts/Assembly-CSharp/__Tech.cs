using System;
using System.Collections.Generic;

[Serializable]
public class __Tech
{
	public string name;

	public int id;

	public int country;

	public int Tab;

	public string icon;

	public int group;

	public int positionX;

	public int positionY;

	public int previous;

	public int maxLv;

	public List<int> cType;

	public List<int> cVal;

	public List<int> needRank;

	public List<int> val;

	public int descType;

	public string effectDesc;

	public void CopyTo(Tech dest)
	{
	}

	public void Reset()
	{
	}
}

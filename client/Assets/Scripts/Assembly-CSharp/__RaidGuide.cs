using System;
using System.Collections.Generic;

[Serializable]
public class __RaidGuide
{
	public int id;

	public int raidType;

	public int unitId;

	public List<int> icon;

	public List<string> description;

	public void CopyTo(RaidGuide dest)
	{
	}

	public void Reset()
	{
	}
}

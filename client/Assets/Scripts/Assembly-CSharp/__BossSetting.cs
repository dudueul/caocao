using System;
using System.Collections.Generic;

[Serializable]
public class __BossSetting
{
	public int id;

	public int type;

	public int unitid;

	public List<int> weapon;

	public List<int> armor;

	public List<int> kit;

	public void CopyTo(BossSetting dest)
	{
	}

	public void Reset()
	{
	}
}

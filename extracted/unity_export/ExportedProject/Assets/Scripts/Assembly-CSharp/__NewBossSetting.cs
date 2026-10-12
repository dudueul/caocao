using System;
using System.Collections.Generic;

[Serializable]
public class __NewBossSetting
{
	public int id;

	public int type;

	public int Decid;

	public int jobtype;

	public List<int> weapon;

	public List<int> armor;

	public List<int> kit;

	public void CopyTo(NewBossSetting dest)
	{
	}

	public void Reset()
	{
	}
}

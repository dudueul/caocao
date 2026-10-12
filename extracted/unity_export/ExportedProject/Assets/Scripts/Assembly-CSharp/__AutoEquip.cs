using System;
using System.Collections.Generic;

[Serializable]
public class __AutoEquip
{
	public string name;

	public int id;

	public int equipType;

	public List<int> Lv;

	public List<int> equip;

	public void CopyTo(AutoEquip dest)
	{
	}

	public void Reset()
	{
	}
}

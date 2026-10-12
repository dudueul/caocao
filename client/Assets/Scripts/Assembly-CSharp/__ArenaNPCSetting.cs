using System;
using System.Collections.Generic;

[Serializable]
public class __ArenaNPCSetting
{
	public int id;

	public List<int> unit;

	public int ai;

	public int tileIndex;

	public int order;

	public int weapon;

	public int armor;

	public int kit;

	public List<int> passive;

	public string desc;

	public void CopyTo(ArenaNPCSetting dest)
	{
	}

	public void Reset()
	{
	}
}

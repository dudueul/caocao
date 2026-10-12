using System;
using System.Collections.Generic;

[Serializable]
public class __MayorEffect
{
	public string name;

	public int id;

	public int type;

	public int effectType;

	public int effectRange;

	public string icon;

	public string desc;

	public List<int> level;

	public List<int> effectVal;

	public void CopyTo(MayorEffect dest)
	{
	}

	public void Reset()
	{
	}
}

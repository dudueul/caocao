using System;
using System.Collections.Generic;

[Serializable]
public class __LordEffect
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

	public List<int> effectTime;

	public List<int> coolTime;

	public List<int> silver;

	public void CopyTo(LordEffect dest)
	{
	}

	public void Reset()
	{
	}
}

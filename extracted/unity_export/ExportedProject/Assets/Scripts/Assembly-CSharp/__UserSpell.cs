using System;
using System.Collections.Generic;

[Serializable]
public class __UserSpell
{
	public string name;

	public int id;

	public int cost;

	public List<int> effectTarget;

	public List<int> targetAmount;

	public int effectFunc;

	public int effectValue;

	public string icon;

	public string desc;

	public void CopyTo(UserSpell dest)
	{
	}

	public void Reset()
	{
	}
}

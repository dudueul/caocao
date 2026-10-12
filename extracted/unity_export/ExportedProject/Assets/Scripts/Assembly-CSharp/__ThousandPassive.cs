using System;
using System.Collections.Generic;

[Serializable]
public class __ThousandPassive
{
	public string name;

	public int id;

	public int PassiveId;

	public int value;

	public int Type;

	public int Projectile;

	public int Magical;

	public int Melee;

	public int getType;

	public string desc;

	public List<int> exception;

	public List<int> jobType;

	public void CopyTo(ThousandPassive dest)
	{
	}

	public void Reset()
	{
	}
}

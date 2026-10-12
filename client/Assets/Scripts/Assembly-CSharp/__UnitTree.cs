using System;
using System.Collections.Generic;

[Serializable]
public class __UnitTree
{
	public string name;

	public int id;

	public int country;

	public int unlock;

	public int state;

	public List<int> flag;

	public List<int> value;

	public List<string> desc;

	public int silver;

	public int gold;

	public int honor;

	public string ticketIcon;

	public void CopyTo(UnitTree dest)
	{
	}

	public void Reset()
	{
	}
}

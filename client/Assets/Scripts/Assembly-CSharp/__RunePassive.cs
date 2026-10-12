using System;
using System.Collections.Generic;

[Serializable]
public class __RunePassive
{
	public string name;

	public int id;

	public int passive;

	public List<int> val;

	public List<int> rate;

	public string prefix;

	public int typeCode;

	public int country;

	public int minTier;

	public void CopyTo(RunePassive dest)
	{
	}

	public void Reset()
	{
	}
}

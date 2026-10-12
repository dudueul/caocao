using System;
using System.Collections.Generic;

[Serializable]
public class __ArenaPassiveList
{
	public int id;

	public int passive;

	public int value;

	public int triggerRateValue;

	public List<int> conditionVal;

	public void CopyTo(ArenaPassiveList dest)
	{
	}

	public void Reset()
	{
	}
}

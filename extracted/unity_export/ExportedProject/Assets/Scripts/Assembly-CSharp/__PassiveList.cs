using System;
using System.Collections.Generic;

[Serializable]
public class __PassiveList
{
	public string name;

	public int id;

	public int passive;

	public int val;

	public int triggerRateValue;

	public List<int> conditionVal;

	public int jobLv;

	public int lv;

	public int upgrade;

	public int silver;

	public int gold;

	public int point;

	public void CopyTo(PassiveList dest)
	{
	}

	public void Reset()
	{
	}
}

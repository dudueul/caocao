using System;
using System.Collections.Generic;

[Serializable]
public class __BattleRule
{
	public int id;

	public int ruleTarget;

	public List<int> passiveId;

	public List<int> passiveVal;

	public int isDisplay;

	public string desc;

	public void CopyTo(BattleRule dest)
	{
	}

	public void Reset()
	{
	}
}

using System;
using System.Collections.Generic;

[Serializable]
public class __RuneSet
{
	public int id;

	public int unlock;

	public List<int> type;

	public List<int> mainStat;

	public int country;

	public int minTier;

	public int passiveListId;

	public string filterName;

	public void CopyTo(RuneSet dest)
	{
	}

	public void Reset()
	{
	}
}

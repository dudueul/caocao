using System;
using System.Collections.Generic;

[Serializable]
public class __RankingReward
{
	public string name;

	public int id;

	public int rankingsID;

	public int rankRangeType;

	public int leagueId;

	public int minVal;

	public int maxVal;

	public int type0;

	public int reward0;

	public List<int> resultTimeBox;

	public string startDate;

	public string endDate;

	public void CopyTo(RankingReward dest)
	{
	}

	public void Reset()
	{
	}
}

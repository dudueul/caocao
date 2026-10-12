using System;
using System.Collections.Generic;
using LitJson;

public class PvpUnitStatistics
{
	public int warId;

	private Dictionary<EnumData.PvpUnitStatisticsType, List<PvpUnitStatistic>> statistics;

	public DateTime expDateTime;

	// C# has no syntax for parameterized property 'Item'.
	public List<PvpUnitStatistic> get_Item(EnumData.PvpUnitStatisticsType type)
	{
		return null;
	}

	public void Deserialize(Dictionary<EnumData.PvpUnitStatisticsType, JsonData> jsonDatas, int war_id)
	{
	}
}

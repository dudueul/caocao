using System;
using System.Collections.Generic;

[Serializable]
public class __WorldBossSeason
{
	public int id;

	public List<int> WorldBossID;

	public string monthKey;

	public string startTime;

	public int offfTime;

	public int endTime;

	public int reduceCnt;

	public int reduceTime;

	public int maxPoint;

	public int useFlag;

	public int country;

	public void CopyTo(WorldBossSeason dest)
	{
	}

	public void Reset()
	{
	}
}

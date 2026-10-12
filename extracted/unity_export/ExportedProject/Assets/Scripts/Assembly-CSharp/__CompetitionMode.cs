using System;
using System.Collections.Generic;

[Serializable]
public class __CompetitionMode
{
	public string name;

	public int id;

	public int countUnit;

	public int countHoldUnit;

	public int countBanUnit;

	public int countQueueUnit;

	public List<int> battleMap;

	public int ranking;

	public string description;

	public void CopyTo(CompetitionMode dest)
	{
	}

	public void Reset()
	{
	}
}

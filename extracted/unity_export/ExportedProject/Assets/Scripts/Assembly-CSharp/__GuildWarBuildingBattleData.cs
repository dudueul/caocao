using System;
using System.Collections.Generic;

[Serializable]
public class __GuildWarBuildingBattleData
{
	public int id;

	public int hP;

	public int atk;

	public int def;

	public int inte;

	public int agi;

	public int mor;

	public int baseDmg;

	public int sight;

	public List<int> passiveListId;

	public int autoFunctionId;

	public int autoFunctionCoolTime;

	public int autoMaxRange;

	public int counterFunctionId;

	public int counterMaxRange;

	public void CopyTo(GuildWarBuildingBattleData dest)
	{
	}

	public void Reset()
	{
	}
}

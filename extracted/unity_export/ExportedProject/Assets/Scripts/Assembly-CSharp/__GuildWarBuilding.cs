using System;
using System.Collections.Generic;

[Serializable]
public class __GuildWarBuilding
{
	public int id;

	public string name;

	public int type;

	public int facilityType;

	public string buildingType;

	public int rewardRankPoint;

	public int corpsSpawnDistance;

	public int buildingBattleData;

	public int repairTime;

	public int occupyPassiveTime;

	public List<int> occupyPassive;

	public int faceHeroId;

	public int faceGradeType;

	public List<string> prefab;

	public string desc;

	public void CopyTo(GuildWarBuilding dest)
	{
	}

	public void Reset()
	{
	}
}

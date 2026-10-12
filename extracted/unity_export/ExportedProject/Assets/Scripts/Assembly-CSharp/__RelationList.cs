using System;
using System.Collections.Generic;

[Serializable]
public class __RelationList
{
	public string name;

	public int id;

	public int order;

	public int tier;

	public int needStringCount;

	public List<int> unitId;

	public int unitLevel;

	public List<int> mission;

	public int missionStoryBook;

	public List<int> baseStatType;

	public List<int> baseStatVal;

	public List<int> isBuff;

	public List<int> passiveType;

	public List<int> passiveUnitCount;

	public List<int> passive;

	public string scenarioName;

	public string scenarioImage;

	public string eventScript;

	public int battleMapId;

	public string battleImage;

	public string country;

	public string desc;

	public void CopyTo(RelationList dest)
	{
	}

	public void Reset()
	{
	}
}

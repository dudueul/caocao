using System;

[Serializable]
public class __BattleMap
{
	public string name;

	public int id;

	public string mapTitle;

	public int mapType;

	public int turn;

	public int lvCorrect;

	public int minLv;

	public int maxLv;

	public int lvCorrectH;

	public int minLvH;

	public int maxLvH;

	public int lvClassicMy;

	public int lvCorrectC;

	public int maxUnits;

	public string eventScript;

	public string prefab;

	public int mapLoader;

	public int weather;

	public int ignition;

	public string BGM;

	public int kingdoms;

	public int year;

	public int month;

	public int mainTile;

	public void CopyTo(BattleMap dest)
	{
	}

	public void Reset()
	{
	}
}

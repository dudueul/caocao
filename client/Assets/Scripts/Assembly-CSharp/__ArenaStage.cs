using System;
using System.Collections.Generic;

[Serializable]
public class __ArenaStage
{
	public int id;

	public int theme;

	public string name;

	public int battleMap;

	public List<int> tile;

	public string eventScript;

	public string battleMapImage;

	public int countUnit;

	public int turn;

	public int npcFormation;

	public int enemyFace;

	public int weather;

	public List<int> displayWeather;

	public List<int> globalPassiveType;

	public List<int> globalPassive;

	public List<int> mission;

	public List<int> presetType;

	public List<int> bonusGroup;

	public List<int> getPresetGroupMin;

	public List<int> getPresetGroupMax;

	public string desc;

	public void CopyTo(ArenaStage dest)
	{
	}

	public void Reset()
	{
	}
}

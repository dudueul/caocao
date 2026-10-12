using System;
using System.Collections.Generic;

[Serializable]
public class __Passive
{
	public string name;

	public int id;

	public int inheritId;

	public int triggerType;

	public int triggerTileValue;

	public int type;

	public string atlas;

	public string icon;

	public string iconDeco;

	public int groupingCriterion;

	public string postFixEffect;

	public string applyEffect;

	public int waitApplyEffect;

	public string effect;

	public int showIconInGame;

	public int aiTarget;

	public int displayType;

	public int aiBonusType;

	public int atkVal;

	public int tier;

	public int silver;

	public int gold;

	public int mainBaseStat;

	public int divisorVal;

	public string shortestDesc;

	public string desc;

	public int accumulate;

	public List<int> passiveId;

	public int triggerWeatherValue;

	public void CopyTo(Passive dest)
	{
	}

	public void Reset()
	{
	}
}

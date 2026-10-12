using System;
using System.Collections.Generic;

[Serializable]
public class __Condition
{
	public string name;

	public int id;

	public int conditionType;

	public int buffType;

	public int isStack;

	public int showIconInGame;

	public int minEffect;

	public int cancelType;

	public int cancelCondition;

	public int prevCondition;

	public int auraPassive;

	public string icon;

	public string color;

	public string prefab;

	public int triggerMarker;

	public string marker;

	public int viewType;

	public List<string> markerName;

	public List<int> viewLoc;

	public string castSE;

	public string clearSE;

	public string desc;

	public List<int> stackVal;

	public void CopyTo(Condition dest)
	{
	}

	public void Reset()
	{
	}
}

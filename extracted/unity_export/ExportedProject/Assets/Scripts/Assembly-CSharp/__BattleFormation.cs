using System;
using System.Collections.Generic;

[Serializable]
public class __BattleFormation
{
	public string name;

	public int id;

	public int width;

	public int height;

	public int frontLine;

	public int middleLine;

	public int rearLine;

	public int front;

	public int middle;

	public int rear;

	public List<int> formationIdx;

	public int goodType;

	public int cost;

	public List<int> effectType;

	public List<int> effectTarget;

	public List<int> effectAttribute;

	public List<int> effectValue;

	public void CopyTo(BattleFormation dest)
	{
	}

	public void Reset()
	{
	}
}

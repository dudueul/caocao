using System;
using System.Collections.Generic;

[Serializable]
public class __BattleFormationFunc
{
	public int id;

	public int lv;

	public int formationId;

	public List<int> goodType;

	public List<int> cost;

	public int techId;

	public int needLv;

	public List<int> effectType;

	public List<int> effectVal;

	public void CopyTo(BattleFormationFunc dest)
	{
	}

	public void Reset()
	{
	}
}

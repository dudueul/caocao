using System;
using System.Collections.Generic;

[Serializable]
public class __BattleField
{
	public string name;

	public int id;

	public int defUni;

	public string prefab;

	public int isFlip;

	public int state;

	public int cloud;

	public int position_X;

	public int position_Y;

	public int battleFieldDifficulty;

	public int seizeTurn;

	public int tacticCounts;

	public int battleMap;

	public List<int> tileId;

	public int battleTime;

	public int baseOccupation;

	public int maxOccupation;

	public int productionType0;

	public int productionValue0;

	public int productionType1;

	public int productionValue1;

	public string npcName;

	public int npcRank;

	public int npcUnitLv;

	public int type0;

	public int reward0;

	public void CopyTo(BattleField dest)
	{
	}

	public void Reset()
	{
	}
}

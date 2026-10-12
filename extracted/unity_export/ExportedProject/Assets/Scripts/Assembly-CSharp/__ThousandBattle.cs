using System;
using System.Collections.Generic;

[Serializable]
public class __ThousandBattle
{
	public int id;

	public int tileId;

	public int stage;

	public int battleMap;

	public string miniMap;

	public string gatePrefab;

	public string bgTexture;

	public int needUpgrade;

	public int type;

	public int bossCount;

	public List<int> bossId;

	public int eliteCount;

	public List<int> eliteId;

	public int Type1;

	public int reward1;

	public List<int> SoldierId;

	public int boss5;

	public int boss4;

	public int boss3;

	public int boss2;

	public int boss1;

	public int elite5;

	public int elite4;

	public int elite3;

	public int elite2;

	public int elite1;

	public int goodsType;

	public int price;

	public int minUnits;

	public int passSection;

	public int passPrice;

	public void CopyTo(ThousandBattle dest)
	{
	}

	public void Reset()
	{
	}
}

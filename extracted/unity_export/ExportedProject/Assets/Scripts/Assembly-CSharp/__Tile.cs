using System;

[Serializable]
public class __Tile
{
	public string name;

	public int id;

	public string color;

	public int canFire;

	public int canIce;

	public int canStone;

	public int canWind;

	public int hp;

	public int mp;

	public int clearBuff;

	public int clearDebuff;

	public int isIgnited;

	public int ignitionRate;

	public string setCondition;

	public string effectRate;

	public int mask;

	public int waterDefense;

	public int mountainDefense;

	public int desertDefense;

	public int waterSpecialization;

	public int mountainSpecialization;

	public int mountainHelp;

	public int desertHelp;

	public int castleHelp;

	public int snowHelp;

	public int riverHelp;

	public int alwaysShadowEnabled;

	public int tileScoreForAI;

	public string SE;

	public int trail;

	public int duelBGM;

	public int duelMap;

	public string tileIcon;

	public void CopyTo(Tile dest)
	{
	}

	public void Reset()
	{
	}
}

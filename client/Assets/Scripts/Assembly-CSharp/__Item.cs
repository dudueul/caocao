using System;
using System.Collections.Generic;

[Serializable]
public class __Item
{
	public string name;

	public int id;

	public int tier;

	public int slot;

	public int itemType;

	public int boxType;

	public int price;

	public int gold;

	public int tresureType;

	public int enhanceType;

	public int unitLv;

	public int itemEffect;

	public int effectValue;

	public int maxValue;

	public int typeValue;

	public int payType;

	public List<int> passive;

	public List<int> passiveVal;

	public int skillId;

	public int skillVal;

	public int unitId;

	public string atlas;

	public string icon;

	public int atk;

	public int atkUp;

	public int def;

	public int defUp;

	public int inte;

	public int inteUp;

	public int agi;

	public int agiUp;

	public int mor;

	public int morUp;

	public int hp;

	public int hpUp;

	public int mp;

	public int mpUp;

	public int maxLv;

	public int targetType;

	public int targetArea;

	public int effectArea;

	public int canUseThousandBattle;

	public int canUseWorldBoss;

	public int skinId;

	public int stickerId;

	public int jobType;

	public int skillBook;

	public int country;

	public string desc;

	public void CopyTo(Item dest)
	{
	}

	public void Reset()
	{
	}
}

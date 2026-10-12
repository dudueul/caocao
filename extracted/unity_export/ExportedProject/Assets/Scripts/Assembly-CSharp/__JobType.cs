using System;
using System.Collections.Generic;

[Serializable]
public class __JobType
{
	public string name;

	public int id;

	public string battlePrefab;

	public int maxAtk;

	public int maxDef;

	public int maxInt;

	public int maxAgi;

	public int maxMor;

	public int maxHp;

	public int maxMp;

	public int dmgToObj;

	public int resourceType;

	public int atkLv;

	public int defLv;

	public int intLv;

	public int agiLv;

	public int morLv;

	public int hpLv;

	public int mpLv;

	public int deckType;

	public List<int> job;

	public List<int> lv;

	public int autoWeaponId;

	public int autoArmorId;

	public int autoAccId;

	public int fShadow;

	public int lShadow;

	public int trailType;

	public int fxOffset;

	public int tileInfo;

	public int npcCheck;

	public int supporter;

	public void CopyTo(JobType dest)
	{
	}

	public void Reset()
	{
	}
}

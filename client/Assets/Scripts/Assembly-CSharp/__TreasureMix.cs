using System;
using System.Collections.Generic;

[Serializable]
public class __TreasureMix
{
	public int id;

	public int mixType;

	public int tierMin;

	public int tierMax;

	public int day;

	public int weaponPer;

	public int armorPer;

	public int accPer;

	public int tierPer1;

	public int tierPer2;

	public int tierPer3;

	public int tierPer4;

	public int tierPer5;

	public int tierPer6;

	public int tierPer7;

	public int doublePer;

	public string perDescription;

	public int eventRibbon;

	public int setGold;

	public int mixSilver;

	public int retryUse;

	public int retryGoldTier1;

	public int retryGoldTier2;

	public int retryGoldTier3;

	public int retryGoldTier4;

	public int retryGoldTier5;

	public int retryGoldTier6;

	public int retryGoldTier7;

	public List<int> mixId;

	public List<int> cnt;

	public void CopyTo(TreasureMix dest)
	{
	}

	public void Reset()
	{
	}
}

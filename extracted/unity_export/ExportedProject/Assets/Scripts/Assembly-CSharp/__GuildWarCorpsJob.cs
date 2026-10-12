using System;
using System.Collections.Generic;

[Serializable]
public class __GuildWarCorpsJob
{
	public string name;

	public int id;

	public string corpsPrefab1;

	public string corpsPrefab2;

	public string corpsPrefab3;

	public string shipPrefab;

	public string emblem;

	public int corpsJobType;

	public int canCreate;

	public int isShowList;

	public int orderNumber;

	public int defType;

	public int corpsJobRank;

	public int maxCorpsLevel;

	public int upgradeJob;

	public int maxCorpsHp;

	public int baseDmg;

	public int baseAtk;

	public int baseDef;

	public int baseInte;

	public int baseAgi;

	public int baseMor;

	public int corpsAtkRate;

	public int corpsDefRate;

	public int corpsInteRate;

	public int corpsAgiRate;

	public int corpsMorRate;

	public int corpsAtkRatePlus;

	public int corpsDefRatePlus;

	public int corpsInteRatePlus;

	public int corpsAgiRatePlus;

	public int corpsMorRatePlus;

	public int corpsMaxActionPoint;

	public int actionPointRecoveryTime;

	public int actionPointRecoveryAmount;

	public int globalCoolTime;

	public int moveCoolTime;

	public int maxCorpsWoundedRate;

	public int sight;

	public int move;

	public List<int> passiveListId;

	public int canDismiss;

	public int canMove;

	public int canMountSiegeWeapon;

	public int canDismountSiegeWeapon;

	public int counterFunctionId;

	public int counterMaxRange;

	public int basicAttackFunctionId;

	public List<int> functionId;

	public string desc;

	public void CopyTo(GuildWarCorpsJob dest)
	{
	}

	public void Reset()
	{
	}
}

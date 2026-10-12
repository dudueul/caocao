using System;
using System.Collections.Generic;

[Serializable]
public class __Job
{
	public string name;

	public int id;

	public int move;

	public int targetArea;

	public int effectArea;

	public int isProjectile;

	public int isCatapult;

	public int hpPlus;

	public int mpPlus;

	public int skillFold;

	public int projectileFold;

	public int catapultFold;

	public List<int> passive;

	public List<int> passiveVal;

	public int aiPriority;

	public int aiRole;

	public int aiType;

	public int frontAdv;

	public int middleAdv;

	public int rearAdv;

	public int hasTurn;

	public int fireResist;

	public List<int> itemType;

	public int canPincers;

	public int style;

	public string moveSE;

	public int selectSE;

	public int eMoveSE;

	public int moveSpeed;

	public int atkDelay;

	public string desc;

	public void CopyTo(Job dest)
	{
	}

	public void Reset()
	{
	}
}

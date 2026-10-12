using System;

[Serializable]
public class __LevelExp
{
	public int id;

	public int userNeedEXP;

	public int userExpSum;

	public int soldierMaxLv;

	public int unitNeedEXP;

	public int unitExpSum;

	public int unitExpHonor;

	public int weaponNeedEXP;

	public int weaponExpSum;

	public int weaponExpHonor;

	public int armorNeedEXP;

	public int armorExpSum;

	public int armorExpHonor;

	public int foodLooting;

	public int silverLooting;

	public int npcCastleLV;

	public int cureByLV;

	public int lordChangeFood;

	public int mayorChangeFood;

	public int maxCost;

	public int upgradeCount;

	public int maxUnitLevel;

	public int undoActionFood;

	public int autoEquipDesiredLv;

	public int needEnergy;

	public int retreatEnergy;

	public void CopyTo(LevelExp dest)
	{
	}

	public void Reset()
	{
	}
}

using System;

[Serializable]
public class __GuildWarCombatAction
{
	public int id;

	public string attackCheckPrefab;

	public string attackEffectPrefab;

	public int attackEffectType;

	public string damagedEffectPrefab;

	public int damagedEffectType;

	public string damagedEffectPrefabOnWater;

	public int damagedEffectTypeOnWater;

	public int actionType;

	public int elementalType;

	public int damageType;

	public int isProjectile;

	public int canDouble;

	public int effectArea;

	public int isTargetCorps;

	public int isTargetBuilding;

	public int isTargetTile;

	public int effectAreaTarget;

	public int skillPower;

	public int minSkillPowerRate;

	public int providePassiveListId;

	public int tileEffectRate;

	public int provideTileEffect;

	public int canCounterAttack;

	public int damagedContributionPoint;

	public void CopyTo(GuildWarCombatAction dest)
	{
	}

	public void Reset()
	{
	}
}

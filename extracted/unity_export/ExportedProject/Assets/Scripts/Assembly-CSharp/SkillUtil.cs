using System.Collections.Generic;
using Caocao;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class SkillUtil
{
	private static List<EnumData.Skills> multiSkillIds;

	private static List<UnitData> targetShuffleList;

	public static int GetHealAmount(EnumData.Skills skillID, UnitData castUnitObj, UnitData targetUnitData, bool isSimulate)
	{
		return 0;
	}

	public static int GetLongestSkillRangeType(UnitData unitData)
	{
		return 0;
	}

	public static int GetLongestSkillRangeType(List<ObscuredInt> ownSkills)
	{
		return 0;
	}

	public static int GetExpandPassiveSkillRangeType(int expandValue)
	{
		return 0;
	}

	public static int GetSkillEffectRangeType(EnumData.Skills skillID)
	{
		return 0;
	}

	public static bool CanFireSkillOnTile(Tile tile, UserUnit unit)
	{
		return false;
	}

	public static bool CanIceSkillOnTile(Tile tile, UserUnit unit)
	{
		return false;
	}

	public static bool CanStoneSkillOnTile(Tile tile, UserUnit unit)
	{
		return false;
	}

	public static bool CanWindSkillOnTile(Tile tile, UserUnit unit)
	{
		return false;
	}

	public static bool IsAvailableSkillTarget(UnitData triggerUnitData, UnitData targetUnitData, int skillID)
	{
		return false;
	}

	public static bool IsAvailableHealTarget(UnitData targetUnitData, EnumData.UnitResType resType, Skill skillData)
	{
		return false;
	}

	public static bool IsAvailableConditionHealTarget(UnitData targetUnitData, Skill skillData)
	{
		return false;
	}

	public static bool IsAvailableSkillTargetBaseUnit(EnumData.Skills skillId)
	{
		return false;
	}

	public static bool CheckCastleSkill(UnitData targetUnitObj, int skillId)
	{
		return false;
	}

	public static bool CanUseSkillOnTile(EnumData.Skills skillID, int tileID, UserUnit unit)
	{
		return false;
	}

	public static bool CanUseSkillOnWeather(EnumData.Skills skillID, Caocao.Weather weatherId)
	{
		return false;
	}

	public static Vector2 GetAtkSkillPowerRange()
	{
		return default(Vector2);
	}

	public static bool CanUseSkillMode(int skillId)
	{
		return false;
	}

	public static bool IsRetreatHitTheSkill(EnumData.Skills skillId)
	{
		return false;
	}

	public static EnumData.Skills GetPreparationSkillId(EnumData.Skills readySkillId)
	{
		return EnumData.Skills.NONE;
	}

	public static EnumData.Skills GetReadySkillId(EnumData.Skills skillID)
	{
		return EnumData.Skills.NONE;
	}

	public static EnumData.Conditions GetReadyConditionId(EnumData.Skills skillID)
	{
		return EnumData.Conditions.NONE;
	}

	public static List<EnumData.Skills> CheckMultiSkill(EnumData.Skills skillId)
	{
		return null;
	}

	public static List<UnitData> GetTargetDatasByMetastasis(EnumData.Skills skillId, UnitData triggerUnitData, UnitData baseTargetUnitData, Caocao.UnitGroup oppositeGroup, bool isSimulate)
	{
		return null;
	}

	private static UnitData GetBaseTargetDataByMetastasis(List<UnitData> ignoreList, EnumData.Skills skillId, Caocao.UnitGroup oppositeGroup, UnitData triggerUnitData, UnitData baseTargetUnitData, UnitData chainUnitData, bool isSimulate)
	{
		return null;
	}

	private static int GetMaxTargetByMetastasis(EnumData.Skills skillId)
	{
		return 0;
	}

	public static bool ImmediatelyShotSkill(EnumData.Skills skillId)
	{
		return false;
	}

	public static bool IsHealSkill(EnumData.Skills skillId)
	{
		return false;
	}

	public static bool IsAttackSkill(EnumData.Skills skillId)
	{
		return false;
	}

	public static bool IsElementalSkill(EnumData.Skills skillId)
	{
		return false;
	}

	public static bool IsElementalSkillType(EnumData.SkillTypes skillType)
	{
		return false;
	}

	public static bool IsEnvoySkill(EnumData.Skills skillId)
	{
		return false;
	}

	public static bool IgnoreConditionDefSkill(EnumData.Skills skillId)
	{
		return false;
	}

	public static int GetSkillIterAtkCount(EnumData.Skills skillId)
	{
		return 0;
	}

	public static bool IsSkillStartFromCaster(EnumData.Skills skillId)
	{
		return false;
	}

	public static bool CanUseSkillOnScenario(int skillId, int scenarioId)
	{
		return false;
	}
}

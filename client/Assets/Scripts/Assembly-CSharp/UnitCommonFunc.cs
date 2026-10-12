using System;
using System.Collections.Generic;
using Caocao;
using UnityEngine;

public static class UnitCommonFunc
{
	private static List<IntVector2> ms_zocGridPositions;

	private static Dictionary<IntVector2, UnitData> ms_grid2AliveUnitDataCache;

	private static Dictionary<IntVector2, List<UnitData>> ms_grid2DeadUnitDataCache;

	private static Dictionary<UserUnit, Dictionary<Type, SpActionBase>> ms_unitData2SPCache;

	private static readonly string[] ms_grades;

	private static float ms_unchangedValue;

	public static int currentAttackCount;

	private static List<SpActionApplicableBase> postApplySpActions;

	public static UnitDirection getUnitDirection(Vector2 befPos, Vector2 curPos)
	{
		return UnitDirection.back;
	}

	public static UnitMetaData.RangeShape TrimMapOutSideArea(UnitMetaData.RangeShape moveArea, IntVector2 unitGridPos)
	{
		return null;
	}

	public static List<UnitData> Get9UnitOnZOC(UnitData baseUnitData, bool isIncludeMyself, Caocao.UnitGroup? groupFilter = null)
	{
		return null;
	}

	public static List<UnitData> Get4UnitOnZOC(UnitData baseUnitData, IntVector2 unitGridPos)
	{
		return null;
	}

	public static List<UnitData> GetX4UnitOnZOC(UnitData baseUnitData, IntVector2 unitGridPos)
	{
		return null;
	}

	public static void SetColliderAllUnit(bool flag)
	{
	}

	public static UnitData GetGridUnitData(IntVector2 gridPos, UnitSide unitSide)
	{
		return null;
	}

	public static UnitData GetGridUnitData(IntVector2 gridPos, Caocao.UnitGroup unitGroup)
	{
		return null;
	}

	public static bool ExistsUnitData(IntVector2 gridPos, Caocao.UnitGroup unitGroup)
	{
		return false;
	}

	public static bool ExistsUnitData(List<IntVector2> gridPosList, Caocao.UnitGroup unitGroup)
	{
		return false;
	}

	public static bool CheckUnitGroup(UnitData unitData, Caocao.UnitGroup groupFilter)
	{
		return false;
	}

	public static bool CheckUnitGroup(UnitSide unitSide, Caocao.UnitGroup groupFilter)
	{
		return false;
	}

	public static bool CheckUnitSide(UnitData unitData, UnitSide sideFilter)
	{
		return false;
	}

	public static bool IsOurUnitSide(UnitData unitData)
	{
		return false;
	}

	public static UnitData GetUnitDataByWorldPos(Vector2 worldPos)
	{
		return null;
	}

	public static GameObject GetObjectByGridPos(IntVector2 gridPos, bool getParentData = false, bool includeDaed = false)
	{
		return null;
	}

	public static void CacheBuildPos2UnitData()
	{
	}

	public static void DeletePos2UnitDataCache()
	{
	}

	public static void CacheSpAction<T>(UserUnit unitData, SpActionBase spBase) where T : SpActionBase
	{
	}

	public static bool GetCachedSpAction<T>(UserUnit unitData, out SpActionBase spBase) where T : SpActionBase
	{
		spBase = null;
		return false;
	}

	public static UnitData GetUnitDataByGridPos(IntVector2 gridPos, bool getParentData = false, bool includeDead = false)
	{
		return null;
	}

	public static List<UnitData> GetUnitDataByGridPos(List<IntVector2> gridPositions, bool getParentData = false, bool includeDead = false)
	{
		return null;
	}

	public static int CountUnitOn9ZOC(UnitData baseUnitData, Caocao.UnitGroup? groupFilter = null)
	{
		return 0;
	}

	public static int GetOwnUnitCountWithJobType(UnitData baseUnitData, bool excludeBaseUnit, params int[] jobTypes)
	{
		return 0;
	}

	public static bool IsUnitMoveableGrid(IntVector2 pos, int jobTypeId)
	{
		return false;
	}

	public static Caocao.UnitGroup GetOppositGroup(UnitSide uniSide)
	{
		return Caocao.UnitGroup.OWNALLY;
	}

	public static List<UnitSide> GetOppositSides(UnitSide uniSide)
	{
		return null;
	}

	public static Caocao.UnitGroup GetOppositGroup(Caocao.UnitGroup unitGroup)
	{
		return Caocao.UnitGroup.OWNALLY;
	}

	public static Caocao.UnitGroup GetUnitGroup(UnitSide uniSide)
	{
		return Caocao.UnitGroup.OWNALLY;
	}

	public static float GetUnitStatConstValue(int statValue)
	{
		return 0f;
	}

	public static int GetUnitStatGradeIdx(UserUnit userUnit, EnumData.StatType statType)
	{
		return 0;
	}

	public static string GetGrade(int idx)
	{
		return null;
	}

	public static float GetAttackSuccessRate(UnitData atkUnitData, UnitData defUnitData, EnumData.AttackType attackType, bool ignoreIndirectDefensePassive, UnitData baseTargetUnitData = null)
	{
		return 0f;
	}

	public static float GetUndoActionAttackSuccessRate(float attackSuccessRate, UnitSide attackUnitSide, EnumData.Skills skillId = EnumData.Skills.NONE)
	{
		return 0f;
	}

	private static bool GetAtkSuccessRateByCondition(UnitData atkUnitData, UnitData defUnitData, out float attackSuccessRate)
	{
		attackSuccessRate = default(float);
		return false;
	}

	private static bool GetAtkSuccessRateByEvent(UnitData atkUnitData, UnitData defUnitData, out float attackSuccessRate)
	{
		attackSuccessRate = default(float);
		return false;
	}

	private static bool GetAtkSuccessRateBySP(UnitData atkUnitData, UnitData defUnitData, int attackIdx, AttackProcess attackProcess, bool ignoreIndirectDefensePassive, EnumData.AttackType attType, out float attackSuccessRate)
	{
		attackSuccessRate = default(float);
		return false;
	}

	private static bool GetAtkSuccessRateByBaseTargetSP(UnitData atkUnitData, UnitData baseDefUnitDat, EnumData.AttackType attType, bool isTargetFirstMover, out float attackSuccessRate)
	{
		attackSuccessRate = default(float);
		return false;
	}

	public static float GetDoubleAttackRate(UnitData atkUnitData, UnitData defUnitData, EnumData.AttackType attType)
	{
		return 0f;
	}

	private static float GetBasicAtkSuccessRate(UnitData atkUnitData, UnitData defUnitData)
	{
		return 0f;
	}

	public static float GetCriticalSuccessRate(UnitData atkUnitData, UnitData defUnitData)
	{
		return 0f;
	}

	public static bool isSecondAttack(EnumData.AttackType attType, int attackCount)
	{
		return false;
	}

	public static int GetRawAttackDamage(UnitData atkUnitData, UnitData defUnitData, EnumData.AttackType attType, EnumData.AttackType startAttType, int attackCount, bool isBaseTarget, bool isCriticalAttack, bool isSimulate, bool ignoreCriticalDefensePassive, bool ignoreIndirectDefensePassive, bool isAddEffectGrids)
	{
		return 0;
	}

	public static void GenerateAttackDamageEffect(UnitData triggerUnitData, UnitData targetUnitData, ResultPerUnit resultPerUnit, Attack attackResult, int rawAttackDamage, bool isBaseTarget)
	{
	}

	public static void GenerateAttackDamagePostEffect(UnitData triggerUnitData, UnitData targetUnitData, ResultPerUnit result, EnumData.AttackType attType, int attackIdx, int attackCount, bool isBaseTarget, Attack attackResult, int rawAttackDamage, bool isAddEffectGrids)
	{
	}

	public static AttackProcess CaculateAttackProcess(UnitData triggerUnitData, UnitData baseTargetUnitData, EnumData.AttackType attType, EnumData.AttackType startAttType, bool isTargetFirstMover, bool isSimulate)
	{
		return null;
	}

	private static bool GetSkillSuccessRateByCondition(UnitData atkUnitData, UnitData defUnitData, EnumData.Skills usedSkillID, out float attackSuccessRate)
	{
		attackSuccessRate = default(float);
		return false;
	}

	private static bool GetSkillSuccessRateByEvent(UnitData atkUnitData, UnitData defUnitData, out float attackSuccessRate)
	{
		attackSuccessRate = default(float);
		return false;
	}

	private static bool GetSkillSuccessRateByAccuracyType(UnitData atkUnitData, UnitData defUnitData, EnumData.Skills usedSkillID, out float skillSuccessRate)
	{
		skillSuccessRate = default(float);
		return false;
	}

	private static bool GetSkillSuccessRateBySP(UnitData atkUnitData, UnitData defUnitData, EnumData.Skills usedSkillID, bool isMainTarget, out float skillSuccessRate, bool isSimulate)
	{
		skillSuccessRate = default(float);
		return false;
	}

	public static float GetDoubleSkillRate(UnitData atkUnitData, UnitData defUnitData, EnumData.Skills usedSkillID, bool isSimulate)
	{
		return 0f;
	}

	private static float GetBasicSkillSuccessRate(UnitData atkUnitData, UnitData defUnitData)
	{
		return 0f;
	}

	private static float AdjustBasicSkillSuccessRateByAccuracyType(UnitData atkUnitData, UnitData defUnitData, EnumData.Skills usedSkillID, float rawSkillSuccessRate, out bool need2AdjustmentByItem, out bool need2AdjustmentBySp, out bool need2AdjustmentByFireWall)
	{
		need2AdjustmentByItem = default(bool);
		need2AdjustmentBySp = default(bool);
		need2AdjustmentByFireWall = default(bool);
		return 0f;
	}

	private static float AdjustBasicSkillSuccessRateByItem(UnitData atkUnitData, UnitData defUnitData, float rawSkillSuccessRate)
	{
		return 0f;
	}

	private static float AdjustBasicSkillSuccessRateBySP(UnitData atkUnitData, UnitData defUnitData, EnumData.Skills usedSkillID, float rawSkillSuccessRate)
	{
		return 0f;
	}

	private static float AdjustBasicSkillSuccessRateByFlameWall(UnitData atkUnitData, UnitData defUnitData, EnumData.Skills usedSkillId, float rawSkillSuccessRate)
	{
		return 0f;
	}

	public static int GetRawSkillDamage(UnitData atkUnitData, UnitData defUnitData, EnumData.Skills usedSkillID, int skillCastCount, int atkIdx, bool isCriticalAttack, bool isSimulate)
	{
		return 0;
	}

	public static ResultPerUnit GenerateSkillDamageEffect(UnitData triggerUnitData, UnitData targetUnitData, SkillAttack skillAttack, int successRate, int rawDamage, bool isCriticalOccured, int atkIndex)
	{
		return null;
	}

	public static void GenerateSkillDamagePostEffect(UnitData triggerUnitData, UnitData targetUnitData, UnitData baseTargetUnitData, SkillAttack skillAttack, ResultPerUnit result, int rawDamage, int skillCastingCount, int atkIndex)
	{
	}

	public static SkillProcess CaculateSkillProcess(UnitData triggerUnitData, UnitData baseTargetUnitData, EnumData.Skills skillID)
	{
		return null;
	}

	public static List<UnitData> GetSkillTargets(UnitData triggerUnitData, UnitData baseTargetUnitData, EnumData.Skills skillID, bool isSimulate)
	{
		return null;
	}

	public static float GetSkillSuccessRate(UnitData triggerUnitData, UnitData targetUnitData, UnitData baseTargetUnitData, EnumData.Skills skillID, bool isSimulate)
	{
		return 0f;
	}

	public static int GetEPSkillRawDamage(UnitData atkUnitData, UnitData defUnitData, EnumData.Skills usedSkillID, int attackCount, bool isCriticalAttack, bool isSimulate)
	{
		return 0;
	}

	public static EffectUseItem CaculateItemEffect(UnitData castUnitObj, UnitData targetUnitData, int itemID)
	{
		return null;
	}

	public static int CaculateArmorExp(ResultPerUnit resultPerUnit)
	{
		return 0;
	}

	public static int CaculateUnitExp(ResultPerUnit result)
	{
		return 0;
	}

	public static int CaculateWeaponExp(ResultPerUnit result)
	{
		return 0;
	}

	public static int GetSpActionValue(GameObject unitObj, int spAction)
	{
		return 0;
	}

	public static int CaculateArmorExpBySkill(ResultPerUnit resultPerUnit)
	{
		return 0;
	}

	public static int CaculateUnitExpBySkill(SkillProcess skillProcess)
	{
		return 0;
	}

	public static int ProcessAtkDamageSimulate(UnitData atkUnitData, UnitData defUnitData)
	{
		return 0;
	}

	public static int ProcessArtDamageSimulate(UnitData atkUnitData, UnitData defUnitData, EnumData.Skills skillId)
	{
		return 0;
	}

	public static float GetAddAtkDamage(UnitData atkUnitData, UnitData defUnitData, float atkDmg)
	{
		return 0f;
	}

	private static float GetAddArtDamage(UnitData atkUnitData, UnitData defUnitData)
	{
		return 0f;
	}

	public static bool isPossibleCondition(int rate)
	{
		return false;
	}

	private static int GetEquipAddArtRate(UnitData unitData)
	{
		return 0;
	}

	public static int GetHealHpValueByItem(UnitData targetUnit)
	{
		return 0;
	}

	public static int GetHealHpValueByItem(UnitData targetData, int itemId)
	{
		return 0;
	}

	public static int GetHealMpValueByItem(UnitData targetUnit)
	{
		return 0;
	}

	public static int GetHealMpValueByItem(UnitData targetData, int itemId)
	{
		return 0;
	}

	public static int GetHealMPValueBySkill(UnitData atkUnit, UnitData targetUnit, int skillId)
	{
		return 0;
	}

	public static List<UnitData> GetAroundUnitsByGroup(UnitData baseUnitData, Caocao.UnitGroup groupFilter, bool isIncludeMyself = true)
	{
		return null;
	}

	public static List<GameObject> ListIntersection(List<GameObject> A, List<GameObject> B)
	{
		return null;
	}

	public static List<IntVector2> ListIntersection(List<IntVector2> A, List<IntVector2> B)
	{
		return null;
	}

	public static int GetUnitJobId(int unitId, int jobLevel)
	{
		return 0;
	}

	public static List<UnitData> GetComaUnits()
	{
		return null;
	}

	public static bool CanUnitParticipatePincer(UnitData baseUnit, UnitData pincerUnit, UnitData targetUnit)
	{
		return false;
	}

	public static IntVector2 GetClosestEnemyPos(this UnitData unitData)
	{
		return default(IntVector2);
	}

	public static string GetUnitGrade(int unitID)
	{
		return null;
	}

	public static bool IsHostile(UnitSide a, UnitSide b)
	{
		return false;
	}

	public static bool IsAroundAtTargetPos(IntVector2 targetPos, IntVector2 pos)
	{
		return false;
	}

	private static bool CheckImitationSkill(EnumData.Skills skillId)
	{
		return false;
	}

	private static bool CheckFlameWallAffectedBySkill(EnumData.Skills skillId, bool acceptRateDamage)
	{
		return false;
	}

	public static bool IsNonTargetUnit(UnitData unitData)
	{
		return false;
	}

	public static bool IsNoMoveUnit(UnitData unitData)
	{
		return false;
	}
}

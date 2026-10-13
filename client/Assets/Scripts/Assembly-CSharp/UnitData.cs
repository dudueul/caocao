using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using Caocao;
using UnityEngine;
using nAI;

public class UnitData : UnitDataForRenderer
{
	private UserUnit m_userUnit;

	public UnitController unitCtr;

	public UnitAnimation unitAni;

	private string m_nickName;

	private IntVector2 m_unitSize;

	private List<IntVector2> m_unitGridList;

	public Action<UnitData> conditionTriggerUnitDied;

	private bool m_isHiddenByEvent;

	public bool isExUnit;

	public bool isMapObjectUnit;

	private bool isStarted;

	public bool isRetreatUnit;

	public Dictionary<int, List<SpActionBase>> appliedPassives;

	public Dictionary<int, List<UnitData>> appliedAuraUnits;

	public Dictionary<UnitData, SpActionBase> appliedAuraPassive;

	public static readonly List<GameObject> appliedPassiveEffects;

	private List<EnumData.spAction> causeOfDamage;

	private List<UserSpellBase> appliedUserSpells;

	[HideInInspector]
	public SpawnInfo spawnInfo;

	private int m_deadUnitTurn;

	public Unit unitInfo
	{
		get
		{
			return null;
		}
	}

	public virtual UserUnit userUnit
	{
		get
		{
			return m_userUnit;
		}
		set
		{
		}
	}

	public virtual Ai unitAI { get; set; }

	public ulong uid
	{
		get
		{
			return 0uL;
		}
	}

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public override int unitId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public override int lookUnitId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public override int skinId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public override int jobType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public override int jobLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public override UnitSide unitSide
	{
		get
		{
			return UnitSide.OWN;
		}
		set
		{
		}
	}

	public EnumData.UnitResType resourceType
	{
		get
		{
			return EnumData.UnitResType.HP;
		}
	}

	public string nickName
	{
		get
		{
			return null;
		}
	}

	public uint unitIndex { get; set; }

	public int aiPriorityCorrect
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int levelCorrect
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int group
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int aiType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public UserUnit.UnitWeaknessInfo weakness
	{
		get
		{
			return null;
		}
	}

	public UserUnit.UnitRes res
	{
		get
		{
			return null;
		}
	}

	public UserUnit.UnitRes maxRes
	{
		get
		{
			return null;
		}
	}

	public float hpPercentage
	{
		get
		{
			return 0f;
		}
	}

	public float mpPercentage
	{
		get
		{
			return 0f;
		}
	}

	public float epPercentage
	{
		get
		{
			return 0f;
		}
	}

	public int atk
	{
		get
		{
			return 0;
		}
	}

	public int def
	{
		get
		{
			return 0;
		}
	}

	public int inte
	{
		get
		{
			return 0;
		}
	}

	public int agi
	{
		get
		{
			return 0;
		}
	}

	public int mor
	{
		get
		{
			return 0;
		}
	}

	public int move
	{
		get
		{
			return 0;
		}
	}

	public int exp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public float expPercentage
	{
		get
		{
			return 0f;
		}
	}

	public int consumptionCost
	{
		get
		{
			return 0;
		}
	}

	public int lev
	{
		get
		{
			return 0;
		}
	}

	public int str
	{
		get
		{
			return 0;
		}
	}

	public int cha
	{
		get
		{
			return 0;
		}
	}

	public int wis
	{
		get
		{
			return 0;
		}
	}

	public int dex
	{
		get
		{
			return 0;
		}
	}

	public int luck
	{
		get
		{
			return 0;
		}
	}

	public UserItem weaponItem
	{
		get
		{
			return null;
		}
	}

	public UserItem armorItem
	{
		get
		{
			return null;
		}
	}

	public UserItem kitItem
	{
		get
		{
			return null;
		}
	}

	public int weapon_exp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int armor_exp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public ulong weaponTre
	{
		get
		{
			return 0uL;
		}
	}

	public ulong armorTre
	{
		get
		{
			return 0uL;
		}
	}

	public ulong kitTre
	{
		get
		{
			return 0uL;
		}
	}

	public bool isAlive
	{
		get
		{
			return false;
		}
	}

	public bool isDead
	{
		get
		{
			return false;
		}
	}

	public bool isExhausted
	{
		get
		{
			return false;
		}
	}

	public int jobID
	{
		get
		{
			return 0;
		}
	}

	public int jobStyle
	{
		get
		{
			return 0;
		}
	}

	public bool isClone
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public override bool isCompleteAction
	{
		get
		{
			return false;
		}
	}

	public override bool isCompleteMove
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public override bool inProgressingFlow
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public IntVector2 unitSize
	{
		get
		{
			return default(IntVector2);
		}
		set
		{
		}
	}

	public List<IntVector2> unitGridList
	{
		get
		{
			return null;
		}
	}

	public TroopsGrade troopsGrade
	{
		get
		{
			return TroopsGrade.Common;
		}
		set
		{
		}
	}

	public bool isComa
	{
		get
		{
			return false;
		}
	}

	public bool isHiddenByEvent
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public int deadUnitTurn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool isNonAggressionUnit
	{
		get
		{
			return false;
		}
	}

	public int actionTurn { get; private set; }

	public int normalAttackRangeType
	{
		get
		{
			return 0;
		}
	}

	public int normalAttackEffectRangeType
	{
		get
		{
			return 0;
		}
	}

	public bool expendAttackRange
	{
		get
		{
			return false;
		}
	}

	public bool hasShortDistanceArchery
	{
		get
		{
			return false;
		}
	}

	public bool IsDead()
	{
		return false;
	}

	public bool IsAlive()
	{
		return false;
	}

	public override string ToString()
	{
		return null;
	}

	public override float GetResPercentage(EnumData.UnitResType resType)
	{
		return 0f;
	}

	public int GetResLoss(EnumData.UnitResType resType)
	{
		return 0;
	}

	protected override void Start()
	{
	}

	protected virtual void ProcessFirstAppearance()
	{
	}

	protected virtual void Register2TroopMan()
	{
	}

	public void SetUnitInfo()
	{
	}

	public bool IsSameUnit(UnitData target)
	{
		return false;
	}

	public bool IsEnemySide(UnitSide side)
	{
		return false;
	}

	public int GetSkillAttackRangeType(EnumData.Skills skillID)
	{
		return 0;
	}

	public int GetSkillEffectRangeType(EnumData.Skills skillID)
	{
		return 0;
	}

	public int GetSkillCost(EnumData.Skills skillID, out EnumData.UnitResType resType)
	{
		resType = default(EnumData.UnitResType);
		return 0;
	}

	public bool HasEnoughRes(EnumData.Skills skillID)
	{
		return false;
	}

	public bool HasEnoughRes(EnumData.Skills skillID, out EnumData.UnitResType resType)
	{
		resType = default(EnumData.UnitResType);
		return false;
	}

	public bool CanUseSkillOnTile(EnumData.Skills skillID, int tileID)
	{
		return false;
	}

	public bool CanUseSkillOnWeather(EnumData.Skills skillID, Caocao.Weather weatherId)
	{
		return false;
	}

	public int GetItemRangeType(int itemID)
	{
		return 0;
	}

	public int GetTileJobDefenese()
	{
		return 0;
	}

	public int GetTileId()
	{
		return 0;
	}

	public bool CanMoveOn(IntVector2 pos)
	{
		return false;
	}

	public bool CanMoveOn(List<IntVector2> directionalGrids)
	{
		return false;
	}

	public bool HasConditions(int conditionId)
	{
		return false;
	}

	public bool HasConditions(EnumData.Conditions conditionId)
	{
		return false;
	}

	public bool HasAuraCondition()
	{
		return false;
	}

	public bool HasAnyDebuffCondition()
	{
		return false;
	}

	public bool HasAnyConditionByType(EnumData.ConditionType conditionType)
	{
		return false;
	}

	public bool HasCancelDefensePassiveCondition()
	{
		return false;
	}

	public int GetDebuffConditionCount()
	{
		return 0;
	}

	public virtual void SetStat(bool isInit)
	{
	}

	public void UpdateItemStat(EnumData.ItemSlotType slotType)
	{
	}

	public UnitCondition AddUnitCondition(EnumData.Skills triggerSkillId, int conditionId, UnitData triggerUnitData, out UnitCondition added)
	{
		added = null;
		return null;
	}

	public void AddUnitCondition(UnitCondition condition)
	{
	}

	public void RemoveUnitCondition(int conditionId)
	{
	}

	public void RemoveUnitCondition(UnitCondition condition)
	{
	}

	public void UpdateAuraUnits(UnitCondition unitCondition, List<SpActionBase> auraPassives = null, bool addEffect = false)
	{
	}

	public void UpdateMovedAuraAura(bool addEffect)
	{
	}

	private List<UnitData> GetAuraTargets(UnitCondition unitCondition)
	{
		return null;
	}

	private List<IntVector2> GetAuraRange(UnitCondition unitCondition)
	{
		return null;
	}

	public List<IntVector2> GetAuraRange()
	{
		return null;
	}

	public void ConditionTriggerUnitDied(UnitData triggerUnit)
	{
	}

	private void RecallClone()
	{
	}

	public void InitCondition()
	{
	}

	public void SetUnitAiInfo(int ai, TargetInfo targetUnitInfo, IntVector2 targetPos, bool record, int eventCmdSrl = -1)
	{
	}

	public void SetUnitAiMovableArea(EnumData.aiMovableAreaType areaType, List<IntRect> coords, IntVector2 pivot, int attackArea, TargetInfo targetUnitInfo, bool record, int eventCmdSrl = -1)
	{
	}

	public void SetUnitAiMovableBonusScore(int score, EnumData.aiMovableAreaType areaType, List<IntRect> coords, IntVector2 pivot, int attackArea, TargetInfo targetUnitInfo)
	{
	}

	public void SetUnitAiTargetedScore(int score, bool record, int eventCmdSrl = -1)
	{
	}

	public void SetUnitConditionEffects(bool apply)
	{
	}

	public virtual void RemoveAllUnitCondition()
	{
	}

	public bool CheckEnvoySkill(EnumData.Skills skillID)
	{
		return false;
	}

	public bool CheckSongSkill(EnumData.Skills skillID)
	{
		return false;
	}

	public void OnCalculateResult<T>(bool isSimulate, params UnitData[] applyTargets) where T : SpActionBase
	{
	}

	public bool OnGetCalculatedBoolResult<T>(bool isSimulate, params UnitData[] applyTargets) where T : SpActionBase
	{
		return false;
	}

	public float OnGetCalculatedFloat<T>(float value) where T : SpActionBase
	{
		return 0f;
	}

	public float OnGetCalculatedFloat<T>(float value, bool isSimulate) where T : SpActionBase
	{
		return 0f;
	}

	public int OnGetCalculatedInt<T>(int value) where T : SpActionBase
	{
		return 0;
	}

	public int OnGetCalculatedInt<T>(int value, bool isSimulate) where T : SpActionBase
	{
		return 0;
	}

	public int OnGetCalculatedIntBy2Unit2Value<T>(UnitData trigger, UnitData target, Attack attack, int value, int value2, bool isSimulate) where T : SpActionBase
	{
		return 0;
	}

	public int OnGetCalculatedIntBy2Unit2Value<T>(UnitData trigger, UnitData target, SkillAttack attack, int value, int value2, bool isSimulate) where T : SpActionBase
	{
		return 0;
	}

	public void OnCalculateBy2Unit2Value<T>(UnitData trigger, UnitData target, Attack attack, int value, bool isSimulate) where T : SpActionBase
	{
	}

	public void OnCalculateBy2Unit2Value<T>(UnitData trigger, UnitData target, SkillAttack attack, int value, bool isSimulate) where T : SpActionBase
	{
	}

	public float OnGetCalculatedFloatByUnitData<T>(float value) where T : SpActionBase
	{
		return 0f;
	}

	public float OnGetCalculatedFloatBySkillID<T>(int skillID, float value) where T : SpActionBase
	{
		return 0f;
	}

	public float OnGetCalculatedFloatBySkillID<T>(int skillID, float value, bool isSimulate) where T : SpActionBase
	{
		return 0f;
	}

	public float OnGetCalculatedFloatByUnitData<T>(UnitData others, float value) where T : SpActionBase
	{
		return 0f;
	}

	public float OnGetCalculatedFloatByUnitData<T>(UnitData others, float value, bool isSimulate) where T : SpActionBase
	{
		return 0f;
	}

	public void OnCalculateResultPerUnit<T>(ResultPerUnit result, bool isSimulate) where T : SpActionBase
	{
	}

	public int Select<T>(int haveSpAction, int dontHaveSpAction) where T : SpActionBase
	{
		return 0;
	}

	public float Select<T>(float haveSpAction, float dontHaveSpAction) where T : SpActionBase
	{
		return 0f;
	}

	public void OnApplyPostEffect<T>(ResultPerUnit result) where T : SpActionBase
	{
	}

	public void OnApplyPostEffect<T>(ResultPerUnit result, Attack attack) where T : SpActionBase
	{
	}

	public void OnApplyPostEffect<T>(ResultPerUnit result, SkillAttack attack, float value) where T : SpActionBase
	{
	}

	public void OnApplyEffect<T>() where T : SpActionBase
	{
	}

	public void OnCheckTileEffect<T>() where T : SpActionBase
	{
	}

	private void ComputeGrid()
	{
	}

	public void SetCompleteAction(bool complete, EnumData.UnitActionChangeReason reason)
	{
	}

	public void OnEndTurn()
	{
	}

	public void UpdateActionTurn()
	{
	}

	public bool CanUseOwnedSkill(EnumData.Skills skillId, out string reason)
	{
		reason = null;
		return false;
	}

	public bool CanUseItemSkill(EnumData.Skills skillID, out string reason)
	{
		reason = null;
		return false;
	}

	private bool CanUseSkill(EnumData.Skills skillId, out string reason)
	{
		reason = null;
		return false;
	}

	private bool DoesSkillTargetExist(EnumData.Skills skillID)
	{
		return false;
	}

	public bool CanApplyPenetrationShot(int targetUnitCount, int targetIndex)
	{
		return false;
	}

	protected override void OnDestroy()
	{
	}

	public void ConsumeSpActionCount()
	{
	}

	public void SetAppliedPassive(SpActionBase passive)
	{
	}

	public void SetAppliedAuraUnit(int conditionId, UnitData targetUnit)
	{
	}

	public void RemoveAuraUnit(UnitCondition condition, UnitData targetUnit)
	{
	}

	public void RemoveCondAuraUnit(int conditionId)
	{
	}

	public void SetAppliedAuraPassive(UnitData triggerUnit, SpActionBase passive)
	{
	}

	public void RemoveAppliedAuraPassive(UnitData triggerUnit)
	{
	}

	[DebuggerHidden]
	public IEnumerator AnimatePassiveEffect(int attackIdx, params int[] triggerTypes)
	{
		return null;
	}

	public void ShowPassiveEffect(int attackIdx, params int[] triggerTypes)
	{
	}

	public string GetAppliedPassiveEffectPostfix()
	{
		return null;
	}

	public static void AddAppliedPassiveEffects(GameObject effectObj)
	{
	}

	public void RemoveAppliedPassiveEffectPostfix()
	{
	}

	public IEnumerable<T> GetAppliedPassives<T>() where T : SpActionBase
	{
		return null;
	}

	public void RemoveAppliedPassives<T>(int attackIdx) where T : SpActionBase
	{
	}

	public void RemoveAppliedPassivesWithTriggerType(int attackIdx, params int[] triggerTypes)
	{
	}

	public void ClearAppliedPassives()
	{
	}

	public void SetCauseOfDamage(EnumData.spAction spAction)
	{
	}

	public bool IsAffectedBySpecificPassive(EnumData.spAction spAction)
	{
		return false;
	}

	public bool IsAffectedBySpecificPassive(params EnumData.spAction[] args)
	{
		return false;
	}

	public void ClearCauseOfDamage()
	{
	}

	[DebuggerHidden]
	public static IEnumerator WaitAppliedPAssiveEffects()
	{
		return null;
	}

	public void ApplyUserSpell(UserSpellBase userSpell)
	{
	}

	public int GetApplySpellValue<T>()
	{
		return 0;
	}

	public void RemoveApplySpell<T>()
	{
	}

	public bool HasApplySpell<T>()
	{
		return false;
	}

	public T GetApplySpell<T>() where T : UserSpellBase
	{
		return null;
	}
}

using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using CodeStage.AntiCheat.ObscuredTypes;

public abstract class SpActionBase
{
	public enum Origin
	{
		None = -1,
		Unit0 = 0,
		Unit1 = 1,
		Unit2 = 2,
		Weapon = 3,
		Armor = 4,
		Kit = 5,
		WeaponTreasure = 6,
		ArmorTreasure = 7,
		KitTreasure = 8,
		Job = 9,
		Event = 10,
		Item = 11,
		Skill = 12,
		Cheat = 13,
		AnnihilationBattleFormation = 14,
		ThousandBattleBoss = 15,
		Aura = 16,
		Rune = 17,
		RuneSet = 18,
		Relation = 19,
		Arena = 20,
		Synergy = 21
	}

	public UserUnit userUnit;

	public Origin origin;

	public bool active;

	private ObscuredInt m_spActionValue;

	private Passive m_spActionData;

	protected UserUnit m_auraTriggerUnit;

	private ObscuredInt m_triggerRateValue;

	private EnumData.Conditions m_condition1;

	private EnumData.Conditions m_condition2;

	private int m_initTurnDuration;

	public int remainedTurn;

	private int m_countLimitation;

	public int remainedCount;

	public bool hasIntState;

	public UnitData unitData
	{
		get
		{
			return null;
		}
	}

	public abstract EnumData.spAction id { get; }

	public int spActionValue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int rawSpActionValue
	{
		get
		{
			return 0;
		}
	}

	public int accumulatedSpActionValue
	{
		get
		{
			return 0;
		}
	}

	public virtual Passive spActionData
	{
		get
		{
			return null;
		}
	}

	public EnumData.spAction childId { get; set; }

	public bool isOverrided
	{
		get
		{
			return false;
		}
	}

	public EnumData.spAction overridedId
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public int triggerRateValue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public EnumData.Conditions condition1
	{
		get
		{
			return EnumData.Conditions.NONE;
		}
		set
		{
		}
	}

	public EnumData.Conditions condition2
	{
		get
		{
			return EnumData.Conditions.NONE;
		}
		set
		{
		}
	}

	public int initTurnDuration
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int initCountLimitation
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public static SpActionBase Create(UserUnit userUnit, EnumData.spAction spActionId, float spActionValue, Origin origin)
	{
		return null;
	}

	public static SpActionBase CreateFromPassiveList(UserUnit userUnit, int passiveListId, Origin origin)
	{
		return null;
	}

	public static SpActionBase CreateFromArenaPassiveList(UserUnit userUnit, int passiveListId, Origin origin)
	{
		return null;
	}

	public static SpActionBase Create(UserUnit userUnit, int passiveId, int spActionValue, int triggerRateValue, int conditionVal1, int conditionVal2, Origin origin)
	{
		return null;
	}

	public void Activate()
	{
	}

	protected virtual void OnActiveOnce()
	{
	}

	protected virtual void OnActive()
	{
	}

	public void Deactivate()
	{
	}

	protected virtual void OnDeactive()
	{
	}

	public virtual void OnApplyAuraTriggerUnit(UserUnit triggerUnit)
	{
	}

	public int GetHighestSpActionValue<T>(bool isSimulate, params UnitData[] applyTarget) where T : SpActionBase
	{
		return 0;
	}

	public int GetTotalSpActionValue<T>(bool isSimulate, params UnitData[] applyTarget) where T : SpActionBase
	{
		return 0;
	}

	public bool GetCalculateBoolResults<T>(bool isSimulate, params UnitData[] applyTarget) where T : SpActionBase
	{
		return false;
	}

	public void CalculateResults<T>(bool isSimulate, params UnitData[] applyTarget) where T : SpActionBase
	{
	}

	public void OnEndTurn()
	{
	}

	public virtual void ResetIntState()
	{
	}

	public bool IsBlockSpActionFromTreasure()
	{
		return false;
	}

	public virtual void OnAddSkills(UserUnit userUnit)
	{
	}

	public virtual bool OnSuccessRateApplicable(float rateCorrection)
	{
		return false;
	}

	public virtual int OnGetStaticValue()
	{
		return 0;
	}

	public virtual float OnGetStaticFloat()
	{
		return 0f;
	}

	public virtual void OnCalculateResult(bool isSimulate, params UnitData[] applyTargets)
	{
	}

	public virtual bool OnGetCalculatedBoolResult(bool isSimulate, params UnitData[] applyTargets)
	{
		return false;
	}

	public virtual int OnGetCalculatedResult(UnitData unitData)
	{
		return 0;
	}

	public virtual int OnGetCalculatedInt(int value)
	{
		return 0;
	}

	public virtual int OnGetCalculatedInt(int value, bool isSimulate)
	{
		return 0;
	}

	public virtual float OnGetCalculatedFloat(float value)
	{
		return 0f;
	}

	public virtual float OnGetCalculatedFloat(float value, bool isSimulate)
	{
		return 0f;
	}

	public virtual float OnGetCalculatedFloatBySkillID(int skillID, float value)
	{
		return 0f;
	}

	public virtual float OnGetCalculatedFloatBySkillID(int skillID, float value, bool isSimulate)
	{
		return 0f;
	}

	public virtual float OnGetCalculatedFloatBySkillID(UnitData defUnitData, int skillID, float value, bool isSimulate)
	{
		return 0f;
	}

	public virtual float OnGetCalculatedFloatByUnitData(UnitData unitData, float value)
	{
		return 0f;
	}

	public virtual float OnGetCalculatedFloatByUnitData(UnitData unitData, float value, bool isSimulate)
	{
		return 0f;
	}

	public virtual void OnCalculateResultPerUnit(ResultPerUnit result, bool isSimulate)
	{
	}

	public virtual float GetSkillSuccessRate(UnitData atkUnitData, UnitData defUnitData, UnitData baseDefUnitata)
	{
		return 0f;
	}

	public virtual float GetAttackSuccessRate(UnitData atkUnitData, UnitData defUnitData, float value)
	{
		return 0f;
	}

	public virtual float GetAttackSuccessRate(UnitData atkUnitData, UnitData defUnitData, int attackIdx, AttackProcess attackProcess, float value)
	{
		return 0f;
	}

	public virtual void OnApplyPostEffect(ResultPerUnit result)
	{
	}

	public virtual void OnApplyPostEffect(ResultPerUnit result, Attack attack)
	{
	}

	public virtual void OnApplyPostEffect(ResultPerUnit result, SkillAttack attack, float value)
	{
	}

	public virtual void OnApplyEffect()
	{
	}

	public virtual void OnApplyTileEffect()
	{
	}

	public virtual void OnCalculateBy2Unit2Value(UnitData triggerUnitData, UnitData targetUnitData, Attack attackResult, int value, bool isSimulate)
	{
	}

	public virtual void OnCalculateBy2Unit2Value(UnitData triggerUnitData, UnitData targetUnitData, SkillAttack attackResult, int value, bool isSimulate)
	{
	}

	public virtual int OnGetCalculatedIntBy2Unit2Value(UnitData triggerUnitObj, UnitData targetUnitObj, Attack attackResult, int value, int value2, bool isSimulate)
	{
		return 0;
	}

	public virtual int OnGetCalculatedIntBy2Unit2Value(UnitData triggerUnitObj, UnitData targetUnitObj, SkillAttack attackResult, int value, int value2, bool isSimulate)
	{
		return 0;
	}

	public virtual List<EnumData.Skills> OnGetAddSkill()
	{
		return null;
	}

	[DebuggerHidden]
	public virtual IEnumerator OnDoCoroutine()
	{
		return null;
	}

	public static implicit operator bool(SpActionBase exists)
	{
		return false;
	}

	[DebuggerHidden]
	public virtual IEnumerator AnimatePassiveEffect(UnitData effectTargetUnit, int attackIdx, params int[] triggerTypes)
	{
		return null;
	}

	public void ShowPassiveEffect(UnitData effectTargetUnit = null, params int[] triggerTypes)
	{
	}

	public void ShowPassiveApplyEffect(UnitData effectTargetUnit = null, string addEffectName = null, params int[] triggerTypes)
	{
	}
}

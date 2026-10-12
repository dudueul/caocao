using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using BattleRecorder;

public sealed class EffectCondition : EffectBase
{
	private EnumData.Conditions m_condition;

	private UnitCondition m_removed;

	private UnitCondition m_added;

	private bool m_isSpeedyAnmation;

	private EnumData.Skills m_triggerSkillId;

	private bool m_ignored;

	private bool m_blocked;

	private bool m_updateSpActions;

	private int m_stackCount;

	private bool m_forceApply;

	public bool IsGuardWeaknessCondition
	{
		get
		{
			return false;
		}
	}

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	private EffectCondition(UnitObject triggerUnit, UnitObject targetUnit)
		: base(null, null)
	{
	}

	public EffectCondition(UnitData triggerUnit, UnitData targetUnit, EnumData.Conditions condition, EnumData.Skills triggerSkillId, bool reflected = false, bool isSpeedyAnmation = false, bool updateSpActions = false)
		: base(null, null)
	{
	}

	public EffectCondition(UnitData triggerUnit, UnitData targetUnit, EnumData.Conditions condition, EnumData.Skills triggerSkillId, int stackCount)
		: base(null, null)
	{
	}

	public void SetStackCount(int stackCount)
	{
	}

	private void CheckInvalidation(bool reflected, EnumData.Skills triggerSkillId)
	{
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	public static EffectCondition Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}

	private void ApplyCondition()
	{
	}

	private void RevertCondition()
	{
	}

	[DebuggerHidden]
	public override IEnumerator Animate()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator AnimateCondition()
	{
		return null;
	}

	public void ImmediatelyAnimateCondition()
	{
	}

	public static List<EffectBase> GetEffectCondition(UnitData trigger, UnitData target, EnumData.Conditions conditionId, EnumData.Skills skillId)
	{
		return null;
	}
}

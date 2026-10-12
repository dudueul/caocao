using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using BattleRecorder;

public sealed class EffectConditionSong : EffectBase
{
	private EnumData.Conditions m_condition;

	private UnitCondition m_removed;

	private UnitCondition m_added;

	private bool m_isSpeedyAnmation;

	private EnumData.Skills m_triggerSkillId;

	private List<SpActionBase> m_auraPassives;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	private EffectConditionSong(UnitObject triggerUnit, UnitObject targetUnit)
		: base(null, null)
	{
	}

	public EffectConditionSong(UnitData triggerUnit, UnitData targetUnit, EnumData.Conditions condition, EnumData.Skills triggerSkillId, bool isSpeedyAnmation = false)
		: base(null, null)
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

	public static EffectConditionSong Read(OptimizedBinaryReader r)
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

	private void ImmediatelyAnimateCondition()
	{
	}

	[DebuggerHidden]
	private IEnumerator AnimateCondition()
	{
		return null;
	}

	public static List<EffectBase> GetEffectCondition(UnitData trigger, UnitData target, EnumData.Conditions conditionId, EnumData.Skills skillId)
	{
		return null;
	}
}

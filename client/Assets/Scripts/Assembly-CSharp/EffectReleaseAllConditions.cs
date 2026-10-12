using System.Collections.Generic;
using BattleRecorder;

public sealed class EffectReleaseAllConditions : EffectConditionReleaseBase
{
	private readonly List<EnumData.Conditions> m_ignoredConditions;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectReleaseAllConditions(UnitObject triggerUnit, UnitObject targetUnit, bool buff = false, bool debuff = true, int amount = -1)
		: base(null, null)
	{
	}

	private int GetDecreaseStack(UnitCondition unitCondition, int amount)
	{
		return 0;
	}
}

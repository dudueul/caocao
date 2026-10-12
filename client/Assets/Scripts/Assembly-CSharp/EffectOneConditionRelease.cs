using BattleRecorder;

public sealed class EffectOneConditionRelease : EffectConditionReleaseBase
{
	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectOneConditionRelease(UnitObject triggerUnit, UnitObject targetUnit, EnumData.Conditions conditionID)
		: base(null, null)
	{
	}

	public EffectOneConditionRelease(UnitObject triggerUnit, UnitObject targetUnit, EnumData.Conditions conditionID, int amount)
		: base(null, null)
	{
	}
}

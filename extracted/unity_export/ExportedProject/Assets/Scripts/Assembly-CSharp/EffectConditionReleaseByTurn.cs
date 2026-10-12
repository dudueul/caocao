public sealed class EffectConditionReleaseByTurn : EffectConditionReleaseBase
{
	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectConditionReleaseByTurn(UnitData triggerUnit, UnitData targetUnit)
		: base(null, null)
	{
	}
}

public abstract class SkillDamageTypeBase : EffectBase
{
	protected abstract EnumData.damageType damageType { get; }

	protected SkillDamageTypeBase(UnitData castUnit, UnitData targetUnit)
		: base(null, null)
	{
	}

	public override string ToString()
	{
		return null;
	}
}

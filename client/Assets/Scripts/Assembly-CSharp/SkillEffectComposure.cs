public class SkillEffectComposure : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	private EnumData.Conditions conditionId
	{
		get
		{
			return EnumData.Conditions.NONE;
		}
	}

	public SkillEffectComposure(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}

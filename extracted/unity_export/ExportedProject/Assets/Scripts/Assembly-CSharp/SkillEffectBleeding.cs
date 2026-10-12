public class SkillEffectBleeding : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectBleeding(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit, EnumData.Conditions conditionId = EnumData.Conditions.BLEEDING)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}

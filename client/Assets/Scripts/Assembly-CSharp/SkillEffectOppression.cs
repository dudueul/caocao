public class SkillEffectOppression : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectOppression(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}

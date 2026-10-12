public class SkillEffectMoveUp : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectMoveUp(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit, EnumData.Conditions conditionId = EnumData.Conditions.MOVE_UP)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}

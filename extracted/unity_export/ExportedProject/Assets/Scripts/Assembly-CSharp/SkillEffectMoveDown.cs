public class SkillEffectMoveDown : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectMoveDown(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit, EnumData.Conditions conditionId = EnumData.Conditions.MOVE_DOWN)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}

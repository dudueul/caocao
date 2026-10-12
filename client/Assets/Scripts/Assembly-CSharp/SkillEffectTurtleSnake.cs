public class SkillEffectTurtleSnake : SkillEffectBase
{
	public const int adjust_MOVE_DOWN_value = 1;

	public const int adjust_MOVE_UP_value = 2;

	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectTurtleSnake(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit)
		: base(EnumData.Skills.NONE, null, null)
	{
	}
}

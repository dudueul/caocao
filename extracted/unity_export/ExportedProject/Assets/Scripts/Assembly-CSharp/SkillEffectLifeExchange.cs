public class SkillEffectLifeExchange : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectLifeExchange(AttackBase attack, EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit)
		: base(EnumData.Skills.NONE, null, null)
	{
	}

	private ShowDamage.DamageType GetDamageTypeByDeltaHP(int deltaHP)
	{
		return ShowDamage.DamageType.normal;
	}
}

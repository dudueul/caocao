public class SkillEffectArson : SkillEffectBase
{
	private int m_objectId;

	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectArson(EnumData.Skills skillID, UnitData castUnit, int effectAreaType)
		: base(EnumData.Skills.NONE, null, null)
	{
	}

	private void ApplyFireTile(int effectAreaType, float ignitionRate)
	{
	}
}

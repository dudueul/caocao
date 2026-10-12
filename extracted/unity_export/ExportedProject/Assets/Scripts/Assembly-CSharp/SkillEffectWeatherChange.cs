using System.Collections;
using Caocao;

public class SkillEffectWeatherChange : SkillEffectBase
{
	private readonly Caocao.Weather m_weather;

	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public SkillEffectWeatherChange(EnumData.Skills skillID, UnitData castUnit, UnitData targetUnit)
		: base(EnumData.Skills.NONE, null, null)
	{
	}

	private Caocao.Weather GetWeatherID(EnumData.Skills skillID)
	{
		return (Caocao.Weather)0;
	}

	public override IEnumerator Animate()
	{
		return null;
	}
}

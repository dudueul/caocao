using System.Collections;
using System.Diagnostics;

public class SkillDebuff19 : SkillBase
{
	public override EnumData.Skills skillID
	{
		get
		{
			return EnumData.Skills.NONE;
		}
	}

	public override EnumData.SkillTypes skillType
	{
		get
		{
			return EnumData.SkillTypes.ATK_FIRE;
		}
	}

	public SkillDebuff19(AttackBase attack, UnitData castUnit, UnitData targetUnit, int skillDamage, int successRate, bool isCritical)
		: base(null, null)
	{
	}

	[DebuggerHidden]
	public override IEnumerator ShowNormalMagicEffect()
	{
		return null;
	}

	[DebuggerHidden]
	public override IEnumerator ShowPostEffect()
	{
		return null;
	}
}

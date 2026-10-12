using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class SkillBirdFly : SkillBase
{
	private int m_objectId;

	private int effectAreaType;

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

	public SkillBirdFly(AttackBase attack, UnitData castUnit, UnitData targetUnit, int skillDamage, bool isCritical, int successRate)
		: base(null, null)
	{
	}

	private void ApplyFireTile()
	{
	}

	public static void CheckMultiSkill(List<EnumData.Skills> list)
	{
	}

	[DebuggerHidden]
	public static IEnumerator WarpEnd(UnitData unitData)
	{
		return null;
	}
}

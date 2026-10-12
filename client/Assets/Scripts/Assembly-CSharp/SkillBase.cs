using System.Collections;
using System.Diagnostics;

public abstract class SkillBase : EffectBase
{
	protected Skill m_skillData;

	protected bool m_isHitted;

	public abstract EnumData.Skills skillID { get; }

	public abstract EnumData.SkillTypes skillType { get; }

	public Skill skillData
	{
		get
		{
			return null;
		}
	}

	public bool isHitted
	{
		get
		{
			return false;
		}
	}

	public bool isMissed
	{
		get
		{
			return false;
		}
	}

	public EnumData.Skills realAdoptedSkillID
	{
		get
		{
			return EnumData.Skills.NONE;
		}
	}

	public SkillBase(UnitData castUnit, UnitData targetUnit)
		: base(null, null)
	{
	}

	public SkillBase(EnumData.Skills skillid, UnitData castUnit, UnitData targetUnit)
		: base(null, null)
	{
	}

	public override string ToString()
	{
		return null;
	}

	[DebuggerHidden]
	public virtual IEnumerator ShowSpecialMagicEffect()
	{
		return null;
	}

	[DebuggerHidden]
	public virtual IEnumerator ShowNormalMagicEffect()
	{
		return null;
	}
}

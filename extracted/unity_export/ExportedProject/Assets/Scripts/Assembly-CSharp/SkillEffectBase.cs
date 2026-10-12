using System.Collections;
using System.Diagnostics;
using BattleRecorder;

public abstract class SkillEffectBase : EffectBase
{
	protected EnumData.Skills m_skillID;

	protected EnumData.Skills m_realAdoptedSkillID;

	protected abstract EnumData.SkillEffect skillEffect { get; }

	public EnumData.Skills realAdoptedSkillID
	{
		get
		{
			return EnumData.Skills.NONE;
		}
	}

	protected SkillEffectBase(EnumData.Skills skillID, UnitObject castUnit, UnitObject targetUnit)
		: base(null, null)
	{
	}

	[DebuggerHidden]
	public override IEnumerator Animate()
	{
		return null;
	}

	public override string ToString()
	{
		return null;
	}
}

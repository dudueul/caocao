using System.Collections;
using System.Diagnostics;
using BattleRecorder;

public sealed class SkillEffectReturn : SkillEffectBase
{
	protected override EnumData.SkillEffect skillEffect
	{
		get
		{
			return EnumData.SkillEffect.HP_DOWN;
		}
	}

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public SkillEffectReturn(EnumData.Skills skillID, UnitObject castUnit, UnitObject targetUnit)
		: base(EnumData.Skills.NONE, null, null)
	{
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	[DebuggerHidden]
	public override IEnumerator Animate()
	{
		return null;
	}

	public static SkillEffectReturn Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

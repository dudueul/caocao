using System.Collections;
using System.Diagnostics;
using BattleRecorder;

public sealed class EffectFireChain : EffectHP, IWritable
{
	private int m_skillId;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectFireChain(AttackBase attack, UnitObject baseUnit, UnitObject targetUnit, int delta, int skillId)
		: base(null, null, 0)
	{
	}

	public override void _Apply()
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowMagicEffect()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowEffectFireChain(UnitData baseUnit, UnitData targetUnit)
	{
		return null;
	}

	[DebuggerHidden]
	public override IEnumerator Animate()
	{
		return null;
	}

	public override void _Revert()
	{
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	public new static EffectFireChain Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

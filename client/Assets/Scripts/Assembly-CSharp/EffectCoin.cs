using System.Collections;
using System.Diagnostics;
using BattleRecorder;

public sealed class EffectCoin : EffectInteger
{
	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectCoin(UnitObject triggerUnit, UnitObject targetUnit, int delta)
		: base(null, null, 0)
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

	public static EffectCoin Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

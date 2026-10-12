using System.Collections;
using System.Diagnostics;
using BattleRecorder;

public class EffectNoDamageHit : EffectBase
{
	private bool m_isCritical;

	private bool m_isArtificeAttack;

	private string m_effectPostfix;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	protected EffectNoDamageHit(UnitObject triggerUnit, UnitObject targetUnit)
		: base(null, null)
	{
	}

	public EffectNoDamageHit(UnitObject triggerUnit, UnitObject targetUnit, bool isCritical, bool isArtificeAttack)
		: base(null, null)
	{
	}

	public EffectNoDamageHit(UnitObject triggerUnit, UnitObject targetUnit, bool isCritical)
		: base(null, null)
	{
	}

	public EffectNoDamageHit(UnitObject triggerUnit, UnitObject targetUnit, bool isCritical, string effectPostfix)
		: base(null, null)
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

	public static EffectNoDamageHit Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}

	[DebuggerHidden]
	public override IEnumerator Animate()
	{
		return null;
	}
}

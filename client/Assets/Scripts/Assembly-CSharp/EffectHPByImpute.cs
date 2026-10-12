using System.Collections;
using System.Diagnostics;
using BattleRecorder;

public class EffectHPByImpute : EffectHP
{
	private AttackBase attack;

	private int imputeTargetHp;

	private bool apply;

	public EffectHPByImpute(AttackBase attack, UnitObject triggerUnit, UnitObject targetUnit, int delta, bool isArtificeAttack = false)
		: base(null, null, 0)
	{
	}

	public override void _Apply()
	{
	}

	[DebuggerHidden]
	public override IEnumerator Animate()
	{
		return null;
	}
}

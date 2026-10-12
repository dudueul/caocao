using System.Collections;
using System.Diagnostics;

public class EffectAtkFire : EffectHP
{
	public EffectAtkFire(AttackBase atack, UnitData triggerUnit, UnitData targetUnit, int delta, EnumData.Skills skillID)
		: base(null, null, 0)
	{
	}

	[DebuggerHidden]
	public override IEnumerator Animate()
	{
		return null;
	}
}

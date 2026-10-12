using System.Collections;
using System.Diagnostics;
using BattleRecorder;

public abstract class EffectWeaponExpBase : EffectExp
{
	private TroopStat m_beforeStats;

	protected int? m_deltaCache;

	protected EffectWeaponExpBase(UnitObject triggerUnit, UnitObject targetUnit)
		: base(null, null)
	{
	}

	protected abstract int CaculateWeaponExp();

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	[DebuggerHidden]
	public override IEnumerator Animate()
	{
		return null;
	}

	private UnitData GetTargetUnitData()
	{
		return null;
	}
}

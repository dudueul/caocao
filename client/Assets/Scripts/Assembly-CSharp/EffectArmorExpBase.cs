using System.Collections;
using System.Diagnostics;
using BattleRecorder;

public abstract class EffectArmorExpBase : EffectExp
{
	private TroopStat m_beforeStats;

	protected int? m_deltaCache;

	protected EffectArmorExpBase(UnitObject triggerUnit, UnitObject targetUnit)
		: base(null, null)
	{
	}

	protected abstract int CaculateArmorExp();

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

	private UserItem GetArmorItem()
	{
		return null;
	}

	private UnitData GetTargetUnitData()
	{
		return null;
	}
}

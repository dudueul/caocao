using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using BattleRecorder;

public abstract class EffectUnitExpBase : EffectExp
{
	protected int? m_deltaCache;

	private int m_prevHp;

	private int m_prevMp;

	private List<int> m_earnedSkills;

	protected EffectUnitExpBase(UnitObject triggerUnit, UnitObject targetUnit)
		: base(null, null)
	{
	}

	protected abstract int CaculateUnitExp();

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

using System.Collections;
using System.Diagnostics;
using BattleRecorder;
using UnityEngine;

public sealed class EffectChargeAtk : EffectHP, IWritable
{
	private IntVector2 m_toGrid;

	private Vector3 m_prevPos;

	private bool m_beSweptByAttacker;

	private bool m_immediatelyMove;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectChargeAtk(AttackBase attack, UnitObject triggerUnit, UnitObject targetUnit, IntVector2 toGrid, int delta, bool isArtificeAttack = false, bool immediatelyMove = false)
		: base(null, null, 0)
	{
	}

	public override void _Apply()
	{
	}

	public override void Redo()
	{
	}

	public override void _Revert()
	{
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	public new static EffectChargeAtk Read(OptimizedBinaryReader r)
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

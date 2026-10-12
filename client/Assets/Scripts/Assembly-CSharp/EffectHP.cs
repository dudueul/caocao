using System.Collections;
using System.Diagnostics;
using BattleRecorder;

public class EffectHP : EffectInteger
{
	private bool m_isCritical;

	private ShowDamage.DamageType m_damageType;

	private bool m_isArtificeAttack;

	private bool m_isHPDrain;

	private bool m_isSurvivalHP;

	private string m_effectPostfix;

	private bool m_isRetreat;

	public bool IsRetreat
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	protected EffectHP(UnitObject triggerUnit, UnitObject targetUnit, int delta)
		: base(null, null, 0)
	{
	}

	public EffectHP(AttackBase attack, UnitObject triggerUnit, UnitObject targetUnit, int delta, bool isCritical, ShowDamage.DamageType damageType, bool isArtificeAttack, bool isHPDrain)
		: base(null, null, 0)
	{
	}

	public EffectHP(AttackBase attack, UnitObject triggerUnit, UnitObject targetUnit, int delta, bool isCritical, ShowDamage.DamageType damageType, bool isArtificeAttack)
		: base(null, null, 0)
	{
	}

	public EffectHP(AttackBase attack, UnitObject triggerUnit, UnitObject targetUnit, int delta, bool isCritical, ShowDamage.DamageType damageType)
		: base(null, null, 0)
	{
	}

	public EffectHP(AttackBase attack, UnitObject triggerUnit, UnitObject targetUnit, int delta, ShowDamage.DamageType damageType, bool isSurvivalHP)
		: base(null, null, 0)
	{
	}

	public EffectHP(AttackBase attack, UnitObject triggerUnit, UnitObject targetUnit, int delta, bool isCritical, ShowDamage.DamageType damageType, string effectPostfix)
		: base(null, null, 0)
	{
	}

	protected void CheckDeltaConversion()
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

	protected void ApplyBossState()
	{
	}

	protected void RedoBossState()
	{
	}

	protected void RevertBossState()
	{
	}

	protected void AnimateBossState()
	{
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	public static EffectHP Read(OptimizedBinaryReader r)
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

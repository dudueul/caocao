using System.Collections;
using System.Diagnostics;
using BattleRecorder;

public class EffectWeakness : EffectBase
{
	protected enum EffectType
	{
		None = 0,
		Setup = 1,
		Update = 2,
		Remove = 3
	}

	protected bool m_ignore;

	protected EffectType m_type;

	protected int m_weakPassiveId;

	protected int m_weakTypeIndex;

	protected int m_index;

	protected int m_weaknessType;

	protected bool m_revealed;

	private UserUnit.UnitWeaknessInfo m_prevInfo;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	protected EffectWeakness(UnitObject triggerUnit, UnitObject targetUnit)
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

	private void ApplyWeakness()
	{
	}

	private void RevertWeakness()
	{
	}

	[DebuggerHidden]
	public override IEnumerator Animate()
	{
		return null;
	}

	public void DoUpdateWeaknessInfo()
	{
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}

	public static EffectWeakness Read(OptimizedBinaryReader r)
	{
		return null;
	}
}

using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using BattleRecorder;

public class EffectConditionReleaseBase : EffectBase
{
	private class History
	{
		public UnitCondition unitCondition;

		public int releaseAmount;

		public int befCount;

		public int aftCount;

		public EnumData.Conditions ConditionId
		{
			get
			{
				return EnumData.Conditions.NONE;
			}
		}

		public History(UnitCondition condition, int amount)
		{
		}
	}

	private List<History> m_historyList;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	protected EffectConditionReleaseBase(UnitObject triggerUnit, UnitObject targetUnit)
		: base(null, null)
	{
	}

	protected void AddToRemoveList(UnitCondition condition)
	{
	}

	protected void AddToRemoveList(UnitCondition condition, int amount)
	{
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	protected override bool _ApplyOnLoading()
	{
		return false;
	}

	public override void Redo()
	{
	}

	private void ApplyRelease(bool immediateUpdateMarker)
	{
	}

	private void RevertRelease()
	{
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	public static EffectConditionReleaseBase Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}

	public void ApplyConditionMarkerUpdate()
	{
	}

	[DebuggerHidden]
	public override IEnumerator Animate()
	{
		return null;
	}
}

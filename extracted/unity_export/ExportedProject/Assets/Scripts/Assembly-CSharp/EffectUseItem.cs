using System.Collections;
using System.Diagnostics;
using BattleRecorder;

public sealed class EffectUseItem : EffectInteger
{
	private int m_itemId;

	private Item m_item;

	private ulong m_removed;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectUseItem(UnitObject triggerUnit, UnitObject targetUnit, int itemID)
		: base(null, null, 0)
	{
	}

	private EffectUseItem(UnitObject triggerUnit, UnitObject targetUnit, int itemID, int hint)
		: base(null, null, 0)
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

	public static EffectUseItem Read(OptimizedBinaryReader r)
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

	[DebuggerHidden]
	private IEnumerator StartPanelAnimation()
	{
		return null;
	}

	private IUserDataBase GetTargetUserData()
	{
		return null;
	}
}

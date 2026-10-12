using BattleRecorder;

public sealed class EffectThrowItem : EffectBase
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

	public EffectThrowItem(UnitObject triggerUnit, UnitObject targetUnit, int itemID)
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

	public static EffectThrowItem Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

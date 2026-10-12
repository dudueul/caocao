public class EffectStoreRandomTableIndex : EffectBase
{
	private int index;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectStoreRandomTableIndex()
		: base(null, null)
	{
	}

	public EffectStoreRandomTableIndex(int index)
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

	public static EffectStoreRandomTableIndex Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

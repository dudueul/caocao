public class EffectUnitTurnIdx : EffectBase
{
	private int unitTurnIdx;

	private int prevUnitTurnIdx;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectUnitTurnIdx(int unitTurnIdx)
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

	public static EffectUnitTurnIdx Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

public class EffectTurn : EffectBase
{
	private int turnCount;

	private int prevTurnCount;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectTurn(int turnCount)
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

	public static EffectTurn Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

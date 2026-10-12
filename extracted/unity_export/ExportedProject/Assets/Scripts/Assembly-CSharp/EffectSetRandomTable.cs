public class EffectSetRandomTable : EffectBase
{
	private const int TABLE_LENGTH = 100;

	private const int RANDOM_MIN = 0;

	private const int RANDOM_MAX = 100000;

	private int[] table;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectSetRandomTable()
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

	public static EffectSetRandomTable Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

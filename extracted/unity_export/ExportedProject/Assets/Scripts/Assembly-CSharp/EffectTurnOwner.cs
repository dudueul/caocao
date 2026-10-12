using Caocao;

public class EffectTurnOwner : EffectBase
{
	private UnitSide turnOwner;

	private UnitSide prevTurnOwner;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectTurnOwner(UnitSide turnOwner)
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

	public static EffectTurnOwner Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

using BattleRecorder;

public class SPActionItemRuinAtk : EffectBase
{
	private int m_delta;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	protected SPActionItemRuinAtk(UnitObject attackUnit, UnitObject targetUnit, int delta)
		: base(null, null)
	{
	}

	public SPActionItemRuinAtk(UnitData attackUnit, UnitData targetUnit)
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

	public static SPActionItemRuinAtk Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

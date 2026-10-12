using BattleRecorder;

public sealed class SPActionStatRuinAtk : EffectBase
{
	private GeneralStat m_stolenStat;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	private SPActionStatRuinAtk(UnitObject attackUnit, UnitObject targetUnit, GeneralStat stolenStat)
		: base(null, null)
	{
	}

	public SPActionStatRuinAtk(UnitData attackUnit, UnitData targetUnit)
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

	public static SPActionStatRuinAtk Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

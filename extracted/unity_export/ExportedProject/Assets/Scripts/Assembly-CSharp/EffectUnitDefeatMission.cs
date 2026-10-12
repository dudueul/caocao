using BattleRecorder;

public class EffectUnitDefeatMission : EffectBase
{
	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectUnitDefeatMission(UnitObject defeatUnit)
		: base(null, null)
	{
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	public static EffectUnitDefeatMission Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

using Caocao;

public class EffectUnitSpecificCount : EffectBase
{
	private ControlFlow.UnitSpecificCount specificCountType;

	private UnitSide unitSide;

	private int unitId;

	private int deltaCount;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	public EffectUnitSpecificCount(ControlFlow.UnitSpecificCount specificCountType, UnitData unit, int deltaCount)
		: base(null, null)
	{
	}

	public EffectUnitSpecificCount(ControlFlow.UnitSpecificCount specificCountType, UnitSide unitSide, int unitId, int deltaCount)
		: base(null, null)
	{
	}

	private UnitData FindUnitData()
	{
		return null;
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

	public static EffectUnitSpecificCount Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}

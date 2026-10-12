using System.Collections.Generic;

public class SwapOrderSpell : UserSpellBase
{
	private int swapUnitOrder;

	private int targetUnitOrder;

	private UnitData swapUnit { get; set; }

	private UnitData targetUnit { get; set; }

	public override void Apply(List<UnitData> targetUnitDatas)
	{
	}

	public int GetOrder(UnitData unit)
	{
		return 0;
	}
}

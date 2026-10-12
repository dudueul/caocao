using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class SpAction459 : SpActionBase
{
	private List<UnitData> lstOwnUnits;

	private List<UnitData> lstEnemyUnits;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	private int effectRangeType
	{
		get
		{
			return 0;
		}
	}

	[DebuggerHidden]
	public override IEnumerator OnDoCoroutine()
	{
		return null;
	}
}

using System.Collections;
using System.Diagnostics;

public class SpAction481 : SpActionBase
{
	private const int targetCount = 5;

	private const int effectRangeType = 6;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override void OnCalculateBy2Unit2Value(UnitData triggerUnitData, UnitData targetUnitData, Attack attackResult, int value, bool isSimulate)
	{
	}

	[DebuggerHidden]
	public override IEnumerator AnimatePassiveEffect(UnitData effectTargetUnit, int attackIdx, int[] triggerTypes)
	{
		return null;
	}
}

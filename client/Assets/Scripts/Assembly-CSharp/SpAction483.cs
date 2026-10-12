using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class SpAction483 : SpActionBase
{
	private const int targetCount = 2;

	private const int effectRangeType = 2;

	private const string soundEffect = "SE_M_Thunder";

	public UnitData baseTarget;

	public Dictionary<int, List<UnitData>> targetUnitDatas;

	public UnitData triggerUnit;

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

	private UnitData GetNextUnitData(UnitData baseUnitData, List<UnitData> except)
	{
		return null;
	}

	[DebuggerHidden]
	public override IEnumerator AnimatePassiveEffect(UnitData effectTargetUnit, int attackIdx, int[] triggerTypes)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowChainEffect(UnitData targetUnit, int attackIdx)
	{
		return null;
	}
}

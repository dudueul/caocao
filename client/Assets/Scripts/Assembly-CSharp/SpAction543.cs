using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class SpAction543 : SpActionBase
{
	private List<EffectBase> m_effectConditionReleases;

	public int ActiveStackCnt;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override float OnGetCalculatedFloatByUnitData(UnitData targetUnitData, float value, bool isSimulate)
	{
		return 0f;
	}

	[DebuggerHidden]
	public override IEnumerator AnimatePassiveEffect(UnitData effectTargetUnit, int attackIdx, int[] triggerTypes)
	{
		return null;
	}

	private void RemoveEffectBase(UnitData targetUnitData)
	{
	}

	private void AddEffectBase(EffectBase effectBase)
	{
	}
}

using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class SpAction544 : SpActionBase
{
	private Dictionary<int, UnitData> m_dicTargetUnitData;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public void SetPushTarget(int atkIndex, UnitData targetUnitData)
	{
	}

	public override float OnGetCalculatedFloatByUnitData(UnitData targetUnitData, float value)
	{
		return 0f;
	}

	[DebuggerHidden]
	public override IEnumerator AnimatePassiveEffect(UnitData effectTargetUnit, int atkIdx, int[] triggerTypes)
	{
		return null;
	}
}

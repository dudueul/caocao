using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class SpAction547 : SpActionBase
{
	private const int FLAME_OBJECT_ID = 4300519;

	private Dictionary<int, UnitData> m_dicTargetUnitData;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public static int GetObjectID()
	{
		return 0;
	}

	public void SetTargetUnitData(int atkIndex, UnitData targetUnitData)
	{
	}

	public override void OnApplyPostEffect(ResultPerUnit result)
	{
	}

	[DebuggerHidden]
	public override IEnumerator AnimatePassiveEffect(UnitData effectTargetUnit, int attackIdx, int[] triggerTypes)
	{
		return null;
	}
}

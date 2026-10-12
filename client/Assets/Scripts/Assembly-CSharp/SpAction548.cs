using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class SpAction548 : SpActionBase
{
	private const int FLAME_OBJECT_ID = 4300519;

	private const int SP_EFFECT_AREA = 1;

	private Dictionary<int, UnitData> m_dicTargetUnitData;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override void OnApplyPostEffect(ResultPerUnit result, SkillAttack attack, float value)
	{
	}

	[DebuggerHidden]
	public override IEnumerator AnimatePassiveEffect(UnitData effectTargetUnit, int attackIdx, int[] triggerTypes)
	{
		return null;
	}

	private void SetTargetUnitData(int atkIndex, UnitData targetUnitData)
	{
	}
}

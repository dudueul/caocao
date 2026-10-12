using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class SpAction549 : SpActionBase
{
	private const int SP_EFFECT_AREA = 3;

	private const int MAX_ACTIVE_TARGET = 2;

	private const float HIT_EFFECT_DELAY = 1f;

	private EnumData.Skills m_originSkillId;

	public UnitData m_targetUnitData;

	public Dictionary<int, List<UnitData>> m_addTargetUnitDatas;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override float OnGetCalculatedFloatByUnitData(UnitData targetUnitData, float value)
	{
		return 0f;
	}

	public override void OnApplyPostEffect(ResultPerUnit result, SkillAttack attack, float value)
	{
	}

	[DebuggerHidden]
	public override IEnumerator AnimatePassiveEffect(UnitData effectTargetUnit, int attackIdx, int[] triggerTypes)
	{
		return null;
	}
}

using System.Collections.Generic;

public class SpAction587 : SpActionBase
{
	private const int SP_EFFECT_AREA = 2;

	private const int MAX_ACTIVE_TARGET = 2;

	private EnumData.Skills m_originSkillId;

	private UnitData m_targetUnitData;

	private Dictionary<int, List<UnitData>> m_addTargetUnitDatas;

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
}

using System.Collections;
using System.Diagnostics;

public class SpAction599 : SpActionBase
{
	private EnumData.Conditions m_guardConditionId;

	private EnumData.Conditions m_brokenConditionId;

	private int m_prevWeakTypeIndex;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	protected override void OnActiveOnce()
	{
	}

	protected override void OnDeactive()
	{
	}

	[DebuggerHidden]
	public override IEnumerator OnDoCoroutine()
	{
		return null;
	}

	public override float OnGetCalculatedFloatByUnitData(UnitData attackUnit, float damage, bool isSimulate)
	{
		return 0f;
	}

	public override float OnGetCalculatedFloatBySkillID(int skillID, float damage, bool isSimulate)
	{
		return 0f;
	}

	private float CalculateDamage(EnumData.WeaknessType attackType, float damage, bool isSimulate)
	{
		return 0f;
	}

	public override void OnApplyPostEffect(ResultPerUnit result)
	{
	}

	private void ProcessCheckRevealWeakness(ResultPerUnit result)
	{
	}
}

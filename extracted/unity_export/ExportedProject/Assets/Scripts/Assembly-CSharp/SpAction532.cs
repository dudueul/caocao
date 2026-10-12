public class SpAction532 : SpActionBase
{
	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override bool OnGetCalculatedBoolResult(bool isSimulate, UnitData[] applyTargets)
	{
		return false;
	}

	public override void OnApplyPostEffect(ResultPerUnit result, SkillAttack attack, float value)
	{
	}
}

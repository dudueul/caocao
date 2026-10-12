using System.Collections.Generic;

public class SpAction710 : SpActionBase
{
	private List<UnitData> unitDataOnEffectGrid;

	private List<UnitData> targetOurUnits;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override void OnCalculateBy2Unit2Value(UnitData triggerUnitData, UnitData targetUnitData, SkillAttack attackResult, int rawDamage, bool isSimulate)
	{
	}
}

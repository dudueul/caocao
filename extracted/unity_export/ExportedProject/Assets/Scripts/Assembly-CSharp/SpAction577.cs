public class SpAction577 : SpActionBase
{
	private int m_sharedDamage;

	private UnitData m_shareUnit;

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

	public UnitData GetShareUnitData(UnitData passiveTriggerUnit)
	{
		return null;
	}

	private void SetSharedDamage(UnitData targetUnitObj, int value)
	{
	}

	public override void OnCalculateBy2Unit2Value(UnitData triggerUnitObj, UnitData targetUnitObj, SkillAttack attack, int value, bool isSimulate)
	{
	}

	public override int OnGetCalculatedIntBy2Unit2Value(UnitData triggerUnitObj, UnitData targetUnitObj, SkillAttack attack, int value, int rawAttackDamage, bool isSimulate)
	{
		return 0;
	}

	public override int OnGetCalculatedIntBy2Unit2Value(UnitData triggerUnitObj, UnitData targetUnitObj, Attack attackResult, int value, int rawAttackDamage, bool isSimulate)
	{
		return 0;
	}
}

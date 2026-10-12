using System.Collections.Generic;

public class SpAction676 : SpActionBase
{
	private int m_sharedDamage;

	private List<UnitData> m_shareUnitList;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	private List<UnitData> GetsharedUnitDataList()
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

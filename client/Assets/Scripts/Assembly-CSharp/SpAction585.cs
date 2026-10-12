public class SpAction585 : SpActionBase
{
	public int remainAttackCnt;

	private const int m_addTargetCount = 2;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public bool isMainTargetAttack { get; set; }

	public int addTargetCount
	{
		get
		{
			return 0;
		}
	}

	public override void ResetIntState()
	{
	}

	public override float OnGetCalculatedFloat(float value)
	{
		return 0f;
	}

	public UnitData AddTargetUnitData(UnitData triggerUnitData, UnitData baseTargetUnitData)
	{
		return null;
	}

	public bool HasNextTurnTargetUnit(UnitData triggerUnitData, UnitData baseTargetUnitData, IntVector2 except, IntVector2 include)
	{
		return false;
	}
}

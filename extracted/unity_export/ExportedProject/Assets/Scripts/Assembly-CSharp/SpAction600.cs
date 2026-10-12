public class SpAction600 : SpActionBase
{
	private float m_imputeDamage;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public float imputeDamage
	{
		get
		{
			return 0f;
		}
	}

	public override float OnGetCalculatedFloatByUnitData(UnitData others, float value, bool isSimulate)
	{
		return 0f;
	}

	public override int OnGetCalculatedInt(int value)
	{
		return 0;
	}

	public override float OnGetCalculatedFloatBySkillID(int skillID, float value, bool isSimulate)
	{
		return 0f;
	}
}

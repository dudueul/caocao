public class SpAction582 : SpActionBase
{
	private SpActionIntState m_skill_HitCount;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public int skillHitCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public override void ResetIntState()
	{
	}

	public void OnHit()
	{
	}

	public override float OnGetCalculatedFloatBySkillID(int skillId, float value)
	{
		return 0f;
	}

	private float Calculate(EnumData.Skills skillId, float value)
	{
		return 0f;
	}
}

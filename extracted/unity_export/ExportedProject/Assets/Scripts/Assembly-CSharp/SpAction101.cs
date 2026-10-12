public class SpAction101 : SpActionBase
{
	private SpActionIntState m_hitCount;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public int hitCount
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

	public override float OnGetCalculatedFloat(float atkDmg)
	{
		return 0f;
	}
}

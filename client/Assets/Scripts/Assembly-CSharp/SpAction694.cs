public class SpAction694 : SpActionBase
{
	private SpActionIntState m_missCount;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public int missCount
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

	public void OnMiss()
	{
	}

	public float OnGetCalculatedAttackDamage(float atkDmg)
	{
		return 0f;
	}

	public override float OnGetCalculatedFloat(float atkRate)
	{
		return 0f;
	}

	private float CalculatedAtkRate()
	{
		return 0f;
	}
}

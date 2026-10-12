public class ConditionUI : SpecialStateUI
{
	private Condition m_condition;

	public Condition condition
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	protected override bool hasData
	{
		get
		{
			return false;
		}
	}

	protected override UIAtlas iconAtlas
	{
		get
		{
			return null;
		}
	}

	protected override string iconName
	{
		get
		{
			return null;
		}
	}

	protected override void OnDestroy()
	{
	}
}

public class TooltipExplanation : TooltipComponent
{
	public override void UpdateComponent(Type type, int id)
	{
	}

	public override void UpdateComponent(Type type, int id, ITransmittable data)
	{
	}

	public override void UpdateComponent(Type type, int id, Type triggerType, int triggerId)
	{
	}

	public override void UpdateComponent(Type type, int id, object[] parameters)
	{
	}

	public void SetDescription(string desc)
	{
	}

	private string GetDescription(Type type, int id)
	{
		return null;
	}

	private string GetDescription(Type type, int id, ITransmittable data)
	{
		return null;
	}

	private string GetDescription(Type type, int id, Type triggerType, int triggerId)
	{
		return null;
	}

	private string GetDescription(Type type, int id, params object[] parameters)
	{
		return null;
	}

	private string GetSetCount(RewardInfo rewardInfo)
	{
		return null;
	}

	private string GetLordEffectDescription(Type type, int id, UserUnit userUnit)
	{
		return null;
	}
}

public class TooltipUnitStat : TooltipComponent
{
	public UILabelLocalized strVal;

	public UILabelLocalized dexVar;

	public UILabelLocalized wisVar;

	public UILabelLocalized luckVar;

	public UILabelLocalized chaVar;

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

	private void UpdateComponentImpl(Type type, int id, UserUnit userUnit)
	{
	}

	private void SetStat(UILabelLocalized label, int stat)
	{
	}
}

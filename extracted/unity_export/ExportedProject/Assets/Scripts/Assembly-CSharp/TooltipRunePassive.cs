public class TooltipRunePassive : TooltipComponent
{
	private RuneItemSlot m_runeItemSlot;

	private UserRuneItem m_userRuneItem;

	private UserUnit m_userUnit;

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

	public void UpdateRunePassive(UserRuneItem userRuneItem, UserUnit userUnit = null)
	{
	}

	public void UpdateRunePassive(RuneItemSlot runeItemSlot, UserUnit userUnit = null)
	{
	}

	public void UpdateRunePassive(RunePassive runePassive)
	{
	}

	private void UpdateComponentImpl(Type type, int id)
	{
	}

	private void SetRunePassive(bool showComponent, int passiveId, int passiveValue)
	{
	}
}

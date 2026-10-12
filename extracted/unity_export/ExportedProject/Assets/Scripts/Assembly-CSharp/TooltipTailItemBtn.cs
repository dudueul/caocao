public class TooltipTailItemBtn : ClickTooltipComponent
{
	private UserItem m_userItem;

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

	public void SetChangeBtnClickHandler(EventDelegate.Callback handler)
	{
	}

	public void SetClearBtnClickHandler(EventDelegate.Callback handler)
	{
	}

	protected void SetBtnClickHandler(string btnName, EventDelegate.Callback handler)
	{
	}

	private void UpdateComponentImpl(Type type, int id, UserItem userItem, UserUnit userUnit, int quickSlotDeckId)
	{
	}

	protected override void AddClickHandler()
	{
	}

	protected override void ClearClickHandler()
	{
	}

	protected void EquipItem(ulong itemSrl)
	{
	}
}

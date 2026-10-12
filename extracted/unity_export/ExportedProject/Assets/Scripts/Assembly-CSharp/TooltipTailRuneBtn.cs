public class TooltipTailRuneBtn : ClickTooltipComponent
{
	private UserRuneItem m_userRuneItem;

	private UserRuneSlot m_userRuneSlot;

	private RuneItemSlot m_runeItemSlot;

	private UserUnit m_userUnit;

	private int m_runeSlotNum;

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

	public void ActiveButton(string btnName, bool active)
	{
	}

	public void SetBtnClickHandler(string btnName, EventDelegate.Callback handler)
	{
	}

	private void UpdateComponentImpl(Type type, int id)
	{
	}

	protected override void AddClickHandler()
	{
	}

	protected override void ClearClickHandler()
	{
	}
}

public class TooltipTailPassiveBtn : ClickTooltipComponent
{
	private UserUnit m_userUnit;

	private PassiveList m_passiveList;

	private int m_index;

	private UserArenaUnit m_userArenaUnit;

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

	private void UpdateComponentImpl(Type type, int id, UserItem userItem, UserUnit userUnit)
	{
	}

	private void UpdateComponentConfirm()
	{
	}

	private void UpdateComponentImpl(Type type, PassiveList passiveList, UserUnit userUnit, int index)
	{
	}

	private void UpdateComponentImpl(UserArenaUnit userArenaUnit, int passiveIndex)
	{
	}

	protected override void AddClickHandler()
	{
	}

	protected override void ClearClickHandler()
	{
	}

	private void ChangePassive()
	{
	}

	private void ResetPassive()
	{
	}

	private void BuyPassive()
	{
	}

	private void ClosePanel()
	{
	}

	private void OnDestroy()
	{
	}
}

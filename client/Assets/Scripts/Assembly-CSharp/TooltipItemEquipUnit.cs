public class TooltipItemEquipUnit : TooltipComponent
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

	private void UpdateComponentImpl(Type type, int id, UserItem userItem, int runeId, int passiveId)
	{
	}

	private void UpdateComponentImpl(Type type, int id, UserArenaPassive userArenaPassive)
	{
	}

	private void UpdateComponentForUserItem(Type type, int id, UserItem userItem)
	{
	}

	private void UpdateComponentForRuneSlotItem(Type type, int id, int runeId, int passiveId)
	{
	}

	private void UpdateComponentForArenaPassive(Type type, int id, UserArenaPassive userArenaPassive)
	{
	}
}

public class UIEquipmentSlot : UIItemSlot
{
	public InvEquipment equipment;

	public InvBaseItem.Slot slot;

	protected override InvGameItem observedItem
	{
		get
		{
			return null;
		}
	}

	protected override InvGameItem Replace(InvGameItem item)
	{
		return null;
	}
}

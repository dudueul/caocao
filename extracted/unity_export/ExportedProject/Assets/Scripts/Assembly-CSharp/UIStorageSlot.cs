public class UIStorageSlot : UIItemSlot
{
	public UIItemStorage storage;

	public int slot;

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

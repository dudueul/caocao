using UnityEngine;

public class TooltipTailSkinDetailBtn : TooltipComponent
{
	[SerializeField]
	private UILabelLocalized label;

	private Item m_itemPd;

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

	public void UpdateItemId(int itemId)
	{
	}

	public void UpdateItemPd(Item item)
	{
	}

	public void OnClickShowDetail()
	{
	}
}

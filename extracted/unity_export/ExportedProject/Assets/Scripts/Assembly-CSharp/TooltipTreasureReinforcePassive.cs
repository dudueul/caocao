public class TooltipTreasureReinforcePassive : TooltipComponent
{
	private UILabelLocalized m_title;

	private EnhancePassiveUI m_passiveUI;

	private UILabelLocalized m_spName;

	private UILabelLocalized m_spType;

	private UILabelLocalized m_spDesc;

	public UILabelLocalized Title
	{
		get
		{
			return null;
		}
	}

	public EnhancePassiveUI PassiveUI
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized SpName
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized SpType
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized SpDesc
	{
		get
		{
			return null;
		}
	}

	public override void UpdateComponent(Type type, int id)
	{
	}

	public override void UpdateComponent(Type type, int id, ITransmittable data)
	{
	}

	public override void UpdateComponent(Type descType, int descId, Type triggerType, int triggerId)
	{
	}

	public override void UpdateComponent(Type type, int id, object[] parameters)
	{
	}

	private void UpdateComponentImpl(Type type, int id, UserItem userItem)
	{
	}

	private void UpdateComponentImpl(Type type, int id, UserItem userItem, int index)
	{
	}

	private void OnDestroy()
	{
	}
}

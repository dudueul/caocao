using System;
using UnityEngine;

public class TooltipTailItemObtainInfoBtn : ClickTooltipComponent
{
	private Item m_itemData;

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

	private Transform GetButtonTransform()
	{
		return null;
	}

	private void UpdateComponentImpl(Type type, int id, UserItem userItem, UserUnit userUnit, Action btnCallback = null)
	{
	}

	protected override void AddClickHandler()
	{
	}

	protected override void ClearClickHandler()
	{
	}
}

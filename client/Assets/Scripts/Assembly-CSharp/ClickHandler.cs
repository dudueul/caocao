using System;
using UnityEngine;
using UnityEngine.EventSystems;

public class ClickHandler : MonoBehaviour, IPointerClickHandler, IEventSystemHandler
{
	private Action OnClickEvent__BackingField;

	private Action OnDoubleClickEvent__BackingField;

	public event Action OnClickEvent
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action OnDoubleClickEvent
	{
		add
		{
		}
		remove
		{
		}
	}

	private void OnClick()
	{
	}

	private void OnDoubleClick()
	{
	}

	public void OnPointerClick(PointerEventData eventData)
	{
	}
}

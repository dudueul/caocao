using System.Collections.Generic;
using UnityEngine;

public class UIEventTrigger : MonoBehaviour
{
	public static UIEventTrigger current;

	public List<EventDelegate> onHoverOver;

	public List<EventDelegate> onHoverOut;

	public List<EventDelegate> onPress;

	public List<EventDelegate> onRelease;

	public List<EventDelegate> onSelect;

	public List<EventDelegate> onDeselect;

	public List<EventDelegate> onClick;

	public List<EventDelegate> onDoubleClick;

	public List<EventDelegate> onDragStart;

	public List<EventDelegate> onDragEnd;

	public List<EventDelegate> onDragOver;

	public List<EventDelegate> onDragOut;

	public List<EventDelegate> onDrag;

	private void OnHover(bool isOver)
	{
	}

	private void OnPress(bool pressed)
	{
	}

	private void OnSelect(bool selected)
	{
	}

	private void OnClick()
	{
	}

	private void OnDoubleClick()
	{
	}

	private void OnDragStart()
	{
	}

	private void OnDragEnd()
	{
	}

	private void OnDragOver(GameObject go)
	{
	}

	private void OnDragOut(GameObject go)
	{
	}

	private void OnDrag(Vector2 delta)
	{
	}
}

using UnityEngine;

public class UIEventListener : MonoBehaviour
{
	public delegate void VoidDelegate(GameObject go);

	public delegate void BoolDelegate(GameObject go, bool state);

	public delegate void FloatDelegate(GameObject go, float delta);

	public delegate void VectorDelegate(GameObject go, Vector2 delta);

	public delegate void ObjectDelegate(GameObject go, GameObject obj);

	public delegate void KeyCodeDelegate(GameObject go, KeyCode key);

	public object parameter;

	public VoidDelegate onSubmit;

	public VoidDelegate onClick;

	public VoidDelegate onDoubleClick;

	public BoolDelegate onHover;

	public BoolDelegate onPress;

	public BoolDelegate onSelect;

	public FloatDelegate onScroll;

	public VoidDelegate onDragStart;

	public VectorDelegate onDrag;

	public VoidDelegate onDragOver;

	public VoidDelegate onDragOut;

	public VoidDelegate onDragEnd;

	public ObjectDelegate onDrop;

	public KeyCodeDelegate onKey;

	public BoolDelegate onTooltip;

	private void OnSubmit()
	{
	}

	private void OnClick()
	{
	}

	private void OnDoubleClick()
	{
	}

	private void OnHover(bool isOver)
	{
	}

	private void OnPress(bool isPressed)
	{
	}

	private void OnSelect(bool selected)
	{
	}

	private void OnScroll(float delta)
	{
	}

	private void OnDragStart()
	{
	}

	private void OnDrag(Vector2 delta)
	{
	}

	private void OnDragOver()
	{
	}

	private void OnDragOut()
	{
	}

	private void OnDragEnd()
	{
	}

	private void OnDrop(GameObject go)
	{
	}

	private void OnKey(KeyCode key)
	{
	}

	private void OnTooltip(bool show)
	{
	}

	public static UIEventListener Get(GameObject go)
	{
		return null;
	}
}

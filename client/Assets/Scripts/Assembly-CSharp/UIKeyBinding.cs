using UnityEngine;

public class UIKeyBinding : MonoBehaviour
{
	public enum Action
	{
		PressAndClick = 0,
		Select = 1,
		All = 2
	}

	public enum Modifier
	{
		Any = 0,
		Shift = 1,
		Control = 2,
		Alt = 3,
		None = 4
	}

	public KeyCode keyCode;

	public Modifier modifier;

	public Action action;

	private bool mIgnoreUp;

	private bool mIsInput;

	private bool mPress;

	protected virtual void Start()
	{
	}

	protected virtual void OnSubmit()
	{
	}

	protected virtual bool IsModifierActive()
	{
		return false;
	}

	protected virtual void Update()
	{
	}

	protected virtual void OnBindingPress(bool pressed)
	{
	}

	protected virtual void OnBindingClick()
	{
	}
}

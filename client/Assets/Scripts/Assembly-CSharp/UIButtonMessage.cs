using UnityEngine;

public class UIButtonMessage : MonoBehaviour
{
	public enum Trigger
	{
		OnClick = 0,
		OnMouseOver = 1,
		OnMouseOut = 2,
		OnPress = 3,
		OnRelease = 4,
		OnDoubleClick = 5
	}

	public GameObject target;

	public string functionName;

	public Trigger trigger;

	public bool includeChildren;

	private bool mStarted;

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnHover(bool isOver)
	{
	}

	private void OnPress(bool isPressed)
	{
	}

	private void OnSelect(bool isSelected)
	{
	}

	private void OnClick()
	{
	}

	private void OnDoubleClick()
	{
	}

	private void Send()
	{
	}
}

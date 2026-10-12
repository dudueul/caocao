using System;
using UnityEngine;

public class tk2dUIMultiStateToggleButton : tk2dUIBaseItemControl
{
	public GameObject[] states;

	public bool activateOnPress;

	private Action<tk2dUIMultiStateToggleButton> OnStateToggle__BackingField;

	private int index;

	public string SendMessageOnStateToggleMethodName;

	public int Index
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public event Action<tk2dUIMultiStateToggleButton> OnStateToggle
	{
		add
		{
		}
		remove
		{
		}
	}

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void ButtonClick()
	{
	}

	private void ButtonDown()
	{
	}

	private void ButtonToggle()
	{
	}

	private void SetState()
	{
	}
}

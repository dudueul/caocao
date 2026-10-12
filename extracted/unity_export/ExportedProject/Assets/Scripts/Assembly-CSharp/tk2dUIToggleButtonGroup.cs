using System;
using UnityEngine;

public class tk2dUIToggleButtonGroup : MonoBehaviour
{
	[SerializeField]
	private tk2dUIToggleButton[] toggleBtns;

	public GameObject sendMessageTarget;

	[SerializeField]
	private int selectedIndex;

	private tk2dUIToggleButton selectedToggleButton;

	private Action<tk2dUIToggleButtonGroup> OnChange__BackingField;

	public string SendMessageOnChangeMethodName;

	public tk2dUIToggleButton[] ToggleBtns
	{
		get
		{
			return null;
		}
	}

	public int SelectedIndex
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public tk2dUIToggleButton SelectedToggleButton
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public event Action<tk2dUIToggleButtonGroup> OnChange
	{
		add
		{
		}
		remove
		{
		}
	}

	protected virtual void Awake()
	{
	}

	protected void Setup()
	{
	}

	public void AddNewToggleButtons(tk2dUIToggleButton[] newToggleBtns)
	{
	}

	private void ClearExistingToggleBtns()
	{
	}

	private void SetToggleButtonUsingSelectedIndex()
	{
	}

	private void ButtonToggle(tk2dUIToggleButton toggleButton)
	{
	}

	private void SetSelectedIndexFromSelectedToggleButton()
	{
	}
}

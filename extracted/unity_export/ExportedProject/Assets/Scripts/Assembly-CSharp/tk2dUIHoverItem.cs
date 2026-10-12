using System;
using UnityEngine;

public class tk2dUIHoverItem : tk2dUIBaseItemControl
{
	public GameObject outStateGO;

	public GameObject overStateGO;

	private bool isOver;

	private Action<tk2dUIHoverItem> OnToggleHover__BackingField;

	public string SendMessageOnToggleHoverMethodName;

	public bool IsOver
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public event Action<tk2dUIHoverItem> OnToggleHover
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

	private void HoverOver()
	{
	}

	private void HoverOut()
	{
	}

	public void SetState()
	{
	}
}

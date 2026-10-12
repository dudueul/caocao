using System;
using System.Collections.Generic;
using UnityEngine;

[ExecuteInEditMode]
public class UIToggle : UIWidgetContainer
{
	public delegate bool Validate(bool choice);

	public static BetterList<UIToggle> list;

	public static UIToggle current;

	public int group;

	public UIWidget activeSprite;

	public Animation activeAnimation;

	public Animator animator;

	public bool startsActive;

	public bool instantTween;

	public bool optionCanBeNone;

	public List<EventDelegate> onChange;

	public Validate validator;

	[HideInInspector]
	[SerializeField]
	private UISprite checkSprite;

	[HideInInspector]
	[SerializeField]
	private Animation checkAnimation;

	[HideInInspector]
	[SerializeField]
	private GameObject eventReceiver;

	[HideInInspector]
	[SerializeField]
	private string functionName;

	[HideInInspector]
	[SerializeField]
	private bool startsChecked;

	private bool mIsActive;

	private bool mStarted;

	public bool value
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	[Obsolete]
	public bool isChecked
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public static UIToggle GetActiveToggle(int group)
	{
		return null;
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void Start()
	{
	}

	private void OnClick()
	{
	}

	public void Set(bool state)
	{
	}
}

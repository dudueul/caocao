using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

[ExecuteInEditMode]
public class tk2dUITextInput : MonoBehaviour
{
	public tk2dUIItem selectionBtn;

	public tk2dTextMesh inputLabel;

	public tk2dTextMesh emptyDisplayLabel;

	public GameObject unSelectedStateGO;

	public GameObject selectedStateGO;

	public GameObject cursor;

	public float fieldLength;

	public int maxCharacterLength;

	public string emptyDisplayText;

	public bool isPasswordField;

	public string passwordChar;

	[SerializeField]
	[HideInInspector]
	private tk2dUILayout layoutItem;

	private bool isSelected;

	private bool wasStartedCalled;

	private bool wasOnAnyPressEventAttached;

	private TouchScreenKeyboard keyboard;

	private bool listenForKeyboardText;

	private bool isDisplayTextShown;

	public Action<tk2dUITextInput> OnTextChange;

	public string SendMessageOnTextChangeMethodName;

	private string text;

	public tk2dUILayout LayoutItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public GameObject SendMessageTarget
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool IsFocus
	{
		get
		{
			return false;
		}
	}

	public string Text
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void Awake()
	{
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

	public void SetFocus()
	{
	}

	public void SetFocus(bool focus)
	{
	}

	private void FormatTextForDisplay(string modifiedText)
	{
	}

	private void ListenForKeyboardTextUpdate()
	{
	}

	private void InputSelected()
	{
	}

	[DebuggerHidden]
	private IEnumerator TouchScreenKeyboardLoop()
	{
		return null;
	}

	private void InputDeselected()
	{
	}

	private void AnyPress()
	{
	}

	private void SetState()
	{
	}

	private void SetCursorPosition()
	{
	}

	private void ShowDisplayText()
	{
	}

	private void HideDisplayText()
	{
	}

	private void LayoutReshaped(Vector3 dMin, Vector3 dMax)
	{
	}
}

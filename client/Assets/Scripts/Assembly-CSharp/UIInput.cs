using System;
using System.Collections.Generic;
using UnityEngine;

public class UIInput : MonoBehaviour
{
	public enum InputType
	{
		Standard = 0,
		AutoCorrect = 1,
		Password = 2
	}

	public enum Validation
	{
		None = 0,
		Integer = 1,
		Float = 2,
		Alphanumeric = 3,
		Username = 4,
		Name = 5
	}

	public enum KeyboardType
	{
		Default = 0,
		ASCIICapable = 1,
		NumbersAndPunctuation = 2,
		URL = 3,
		NumberPad = 4,
		PhonePad = 5,
		NamePhonePad = 6,
		EmailAddress = 7
	}

	public enum OnReturnKey
	{
		Default = 0,
		Submit = 1,
		NewLine = 2
	}

	public delegate char OnValidate(string text, int charIndex, char addedChar);

	public static UIInput current;

	public static UIInput selection;

	public UILabel label;

	public InputType inputType;

	public OnReturnKey onReturnKey;

	public KeyboardType keyboardType;

	public bool hideInput;

	[NonSerialized]
	public bool selectAllTextOnFocus;

	public Validation validation;

	public int characterLimit;

	public string savedAs;

	[HideInInspector]
	[SerializeField]
	private GameObject selectOnTab;

	public Color activeTextColor;

	public Color caretColor;

	public Color selectionColor;

	public List<EventDelegate> onSubmit;

	public List<EventDelegate> onChange;

	public OnValidate onValidate;

	[SerializeField]
	[HideInInspector]
	protected string mValue;

	[NonSerialized]
	protected string mDefaultText;

	[NonSerialized]
	protected Color mDefaultColor;

	[NonSerialized]
	protected float mPosition;

	[NonSerialized]
	protected bool mDoInit;

	[NonSerialized]
	protected UIWidget.Pivot mPivot;

	[NonSerialized]
	protected bool mLoadSavedValue;

	protected static int mDrawStart;

	protected static string mLastIME;

	protected static TouchScreenKeyboard mKeyboard;

	private static bool mWaitForKeyboard;

	[NonSerialized]
	protected int mSelectionStart;

	[NonSerialized]
	protected int mSelectionEnd;

	[NonSerialized]
	protected UITexture mHighlight;

	[NonSerialized]
	protected UITexture mCaret;

	[NonSerialized]
	protected Texture2D mBlankTex;

	[NonSerialized]
	protected float mNextBlink;

	[NonSerialized]
	protected float mLastAlpha;

	[NonSerialized]
	protected string mCached;

	[NonSerialized]
	protected int mSelectMe;

	public string defaultText
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool inputShouldBeHidden
	{
		get
		{
			return false;
		}
	}

	[Obsolete]
	public string text
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string value
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	[Obsolete]
	public bool selected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isSelected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public int cursorPosition
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int selectionStart
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int selectionEnd
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public UITexture caret
	{
		get
		{
			return null;
		}
	}

	public string Validate(string val)
	{
		return null;
	}

	private void Start()
	{
	}

	protected void Init()
	{
	}

	protected void SaveToPlayerPrefs(string val)
	{
	}

	protected virtual void OnSelect(bool isSelected)
	{
	}

	protected void OnSelectEvent()
	{
	}

	protected void OnDeselectEvent()
	{
	}

	protected virtual void Update()
	{
	}

	protected void DoBackspace()
	{
	}

	protected virtual void Insert(string text)
	{
	}

	protected string GetLeftText()
	{
		return null;
	}

	protected string GetRightText()
	{
		return null;
	}

	protected string GetSelection()
	{
		return null;
	}

	protected int GetCharUnderMouse()
	{
		return 0;
	}

	protected virtual void OnPress(bool isPressed)
	{
	}

	protected virtual void OnDrag(Vector2 delta)
	{
	}

	private void OnDisable()
	{
	}

	protected virtual void Cleanup()
	{
	}

	public void Submit()
	{
	}

	public void UpdateLabel()
	{
	}

	protected void SetPivotToLeft()
	{
	}

	protected void SetPivotToRight()
	{
	}

	protected void RestoreLabelPivot()
	{
	}

	protected char Validate(string text, int pos, char ch)
	{
		return '\0';
	}

	protected void ExecuteOnChange()
	{
	}

	public void RemoveFocus()
	{
	}

	public void SaveValue()
	{
	}

	public void LoadValue()
	{
	}
}

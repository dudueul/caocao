using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

[ExecuteInEditMode]
public class UIPopupList : UIWidgetContainer
{
	public enum Position
	{
		Auto = 0,
		Above = 1,
		Below = 2
	}

	public enum OpenOn
	{
		ClickOrTap = 0,
		RightClick = 1,
		DoubleClick = 2,
		Manual = 3
	}

	public delegate void LegacyEvent(string val);

	public static UIPopupList current;

	private const float animSpeed = 0.15f;

	public UIAtlas atlas;

	public UIFont bitmapFont;

	public Font trueTypeFont;

	public int fontSize;

	public FontStyle fontStyle;

	public string backgroundSprite;

	public string highlightSprite;

	public Position position;

	public NGUIText.Alignment alignment;

	public List<string> items;

	public List<object> itemData;

	public Vector2 padding;

	public Color textColor;

	public Color backgroundColor;

	public Color highlightColor;

	public bool isAnimated;

	public bool isLocalized;

	public OpenOn openOn;

	public List<EventDelegate> onChange;

	[HideInInspector]
	[SerializeField]
	private string mSelectedItem;

	[HideInInspector]
	[SerializeField]
	private UIPanel mPanel;

	[HideInInspector]
	[SerializeField]
	private GameObject mChild;

	[HideInInspector]
	[SerializeField]
	private UISprite mBackground;

	[HideInInspector]
	[SerializeField]
	private UISprite mHighlight;

	[HideInInspector]
	[SerializeField]
	private UILabel mHighlightedLabel;

	[HideInInspector]
	[SerializeField]
	private List<UILabel> mLabelList;

	[HideInInspector]
	[SerializeField]
	private float mBgBorder;

	[NonSerialized]
	private GameObject mSelection;

	[HideInInspector]
	[SerializeField]
	private GameObject eventReceiver;

	[HideInInspector]
	[SerializeField]
	private string functionName;

	[HideInInspector]
	[SerializeField]
	private float textScale;

	[HideInInspector]
	[SerializeField]
	private UIFont font;

	[HideInInspector]
	[SerializeField]
	private UILabel textLabel;

	private LegacyEvent mLegacyEvent;

	private bool mUseDynamicFont;

	private bool mTweening;

	private Coroutine closeIfUnselectedCoroutine;

	public UnityEngine.Object ambigiousFont
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
	public LegacyEvent onSelectionChange
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool isOpen
	{
		get
		{
			return false;
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

	public object data
	{
		get
		{
			return null;
		}
	}

	[Obsolete]
	public string selection
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private bool handleEvents
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private bool isValid
	{
		get
		{
			return false;
		}
	}

	private int activeFontSize
	{
		get
		{
			return 0;
		}
	}

	private float activeFontScale
	{
		get
		{
			return 0f;
		}
	}

	public void Clear()
	{
	}

	public void AddItem(string text)
	{
	}

	public void AddItem(string text, object data)
	{
	}

	public void RemoveItem(string text)
	{
	}

	public void RemoveItemByData(object data)
	{
	}

	protected void TriggerCallbacks()
	{
	}

	private void OnEnable()
	{
	}

	private void OnValidate()
	{
	}

	private void Start()
	{
	}

	private void OnLocalize()
	{
	}

	private void Highlight(UILabel lbl, bool instant)
	{
	}

	private Vector3 GetHighlightPosition()
	{
		return default(Vector3);
	}

	[DebuggerHidden]
	private IEnumerator UpdateTweenPosition()
	{
		return null;
	}

	private void OnItemHover(GameObject go, bool isOver)
	{
	}

	private void Select(UILabel lbl, bool instant)
	{
	}

	private void OnItemPress(GameObject go, bool isPressed)
	{
	}

	private void OnItemClick(GameObject go)
	{
	}

	private void OnKey(KeyCode key)
	{
	}

	private void OnDisable()
	{
	}

	private void OnSelect(bool isSelected)
	{
	}

	public void Close()
	{
	}

	private void AnimateColor(UIWidget widget)
	{
	}

	private void AnimatePosition(UIWidget widget, bool placeAbove, float bottom)
	{
	}

	private void AnimateScale(UIWidget widget, bool placeAbove, float bottom)
	{
	}

	private void Animate(UIWidget widget, bool placeAbove, float bottom)
	{
	}

	private void OnClick()
	{
	}

	private void OnDoubleClick()
	{
	}

	[DebuggerHidden]
	private IEnumerator CloseIfUnselected()
	{
		return null;
	}

	public void Show()
	{
	}
}

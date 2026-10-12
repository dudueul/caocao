using UnityEngine;

public class UIKeyBindingButton : MonoBehaviour
{
	[SerializeField]
	private KeyBindId m_id;

	[SerializeField]
	private UIButton m_button;

	[SerializeField]
	private UILabel m_buttonLabel;

	private readonly Color focusColor;

	private readonly Color normalColor;

	private static UIKeyBindingButton m_current;

	private bool m_isFocused;

	private KeyCode modifier1;

	private KeyCode modifier2;

	private KeyCode modifier3;

	private KeyCode pressCode;

	public KeyBindId bindId
	{
		get
		{
			return KeyBindId.NONE;
		}
	}

	public KeyBindData bindKey
	{
		get
		{
			return null;
		}
	}

	public static UIKeyBindingButton current
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void SetFocus(bool isFocus)
	{
	}

	public void OnClick()
	{
	}

	private void Awake()
	{
	}

	private void Update()
	{
	}

	private void ResetModifier()
	{
	}

	private bool UpdateModifier(KeyCode keyCode, bool keyDown)
	{
		return false;
	}

	public void OnKey(KeyBindData key)
	{
	}

	public void Reload()
	{
	}
}

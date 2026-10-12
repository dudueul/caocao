using System;
using UnityEngine;

public class tk2dUIItem : MonoBehaviour
{
	private Action OnDown__BackingField;

	private Action OnUp__BackingField;

	private Action OnClick__BackingField;

	private Action OnRelease__BackingField;

	private Action OnHoverOver__BackingField;

	private Action OnHoverOut__BackingField;

	private Action<tk2dUIItem> OnDownUIItem__BackingField;

	private Action<tk2dUIItem> OnUpUIItem__BackingField;

	private Action<tk2dUIItem> OnClickUIItem__BackingField;

	private Action<tk2dUIItem> OnReleaseUIItem__BackingField;

	private Action<tk2dUIItem> OnHoverOverUIItem__BackingField;

	private Action<tk2dUIItem> OnHoverOutUIItem__BackingField;

	public GameObject sendMessageTarget;

	public string SendMessageOnDownMethodName;

	public string SendMessageOnUpMethodName;

	public string SendMessageOnClickMethodName;

	public string SendMessageOnReleaseMethodName;

	[SerializeField]
	private bool isChildOfAnotherUIItem;

	public bool registerPressFromChildren;

	public bool isHoverEnabled;

	public Transform[] editorExtraBounds;

	public Transform[] editorIgnoreBounds;

	private bool isPressed;

	private bool isHoverOver;

	private tk2dUITouch touch;

	private tk2dUIItem parentUIItem;

	public bool IsPressed
	{
		get
		{
			return false;
		}
	}

	public tk2dUITouch Touch
	{
		get
		{
			return default(tk2dUITouch);
		}
	}

	public tk2dUIItem ParentUIItem
	{
		get
		{
			return null;
		}
	}

	public event Action OnDown
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action OnUp
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action OnClick
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action OnRelease
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action OnHoverOver
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action OnHoverOut
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<tk2dUIItem> OnDownUIItem
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<tk2dUIItem> OnUpUIItem
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<tk2dUIItem> OnClickUIItem
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<tk2dUIItem> OnReleaseUIItem
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<tk2dUIItem> OnHoverOverUIItem
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Action<tk2dUIItem> OnHoverOutUIItem
	{
		add
		{
		}
		remove
		{
		}
	}

	private void Awake()
	{
	}

	private void Start()
	{
	}

	public void UpdateParent()
	{
	}

	public void ManuallySetParent(tk2dUIItem newParentUIItem)
	{
	}

	public void RemoveParent()
	{
	}

	public bool Press(tk2dUITouch touch)
	{
		return false;
	}

	public bool Press(tk2dUITouch touch, tk2dUIItem sentFromChild)
	{
		return false;
	}

	public void UpdateTouch(tk2dUITouch touch)
	{
	}

	private void DoSendMessage(string methodName)
	{
	}

	public void Release()
	{
	}

	public void CurrentOverUIItem(tk2dUIItem overUIItem)
	{
	}

	public bool CheckIsUIItemChildOfMe(tk2dUIItem uiItem)
	{
		return false;
	}

	public void Exit()
	{
	}

	public bool HoverOver(tk2dUIItem prevHover)
	{
		return false;
	}

	public void HoverOut(tk2dUIItem currHoverButton)
	{
	}

	private tk2dUIItem GetParentUIItem()
	{
		return null;
	}

	public void SimulateClick()
	{
	}

	public void InternalSetIsChildOfAnotherUIItem(bool state)
	{
	}

	public bool InternalGetIsChildOfAnotherUIItem()
	{
		return false;
	}
}

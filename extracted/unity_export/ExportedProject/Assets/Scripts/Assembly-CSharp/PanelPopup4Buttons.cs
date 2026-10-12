using System;
using Eppy;
using UnityEngine;

public class PanelPopup4Buttons : PanelPopupBase
{
	public static readonly Color green;

	public static readonly Color blue;

	public static readonly Color orange;

	private Func<PopupState> OnBtn1__BackingField;

	private Func<PopupState> OnBtn2__BackingField;

	private Func<PopupState> OnBtn3__BackingField;

	private Func<PopupState> OnBtn4__BackingField;

	public Action onSetContext;

	public Func<PopupState> OnCloseByTappingOutSideCallback;

	public Tuple<string, Color, bool> btn1
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Tuple<string, Color, bool> btn2
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Tuple<string, Color, bool> btn3
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Tuple<string, Color, bool> btn4
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private GameObject titleLabel
	{
		get
		{
			return null;
		}
	}

	private GameObject centerTextOneLine
	{
		get
		{
			return null;
		}
	}

	private GameObject centerTextTwoLine
	{
		get
		{
			return null;
		}
	}

	private GameObject resource1
	{
		get
		{
			return null;
		}
	}

	private GameObject resource2
	{
		get
		{
			return null;
		}
	}

	private GameObject m_btn1
	{
		get
		{
			return null;
		}
	}

	private GameObject m_btn2
	{
		get
		{
			return null;
		}
	}

	private GameObject m_btn3
	{
		get
		{
			return null;
		}
	}

	private GameObject m_btn4
	{
		get
		{
			return null;
		}
	}

	private GameObject _3btn
	{
		get
		{
			return null;
		}
	}

	private GameObject _4btn
	{
		get
		{
			return null;
		}
	}

	public string title
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public event Func<PopupState> OnBtn1
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Func<PopupState> OnBtn2
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Func<PopupState> OnBtn3
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Func<PopupState> OnBtn4
	{
		add
		{
		}
		remove
		{
		}
	}

	public static PanelPopup4Buttons CreateInstance()
	{
		return null;
	}

	public void SetLabel1(string key, params object[] args)
	{
	}

	public void SetLabel2(string key, params object[] args)
	{
	}

	private Tuple<string, Color, bool> GetButtonContext(GameObject button)
	{
		return null;
	}

	private void SetButtonContext(Tuple<string, Color, bool> context, GameObject button)
	{
	}

	private void SetParentUIRoot()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected virtual void OnSetContext()
	{
	}

	public void OnBtnClick()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}

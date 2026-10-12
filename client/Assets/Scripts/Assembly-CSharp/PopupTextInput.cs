using System;
using UnityEngine;

public class PopupTextInput : CaocaoUIModalPopup
{
	private static PopupTextInput self;

	private string notInputAlertText;

	private Transform tr_timerBar;

	private double showTime;

	private double durationTime;

	private bool isTimeInProgress;

	private string expireMsg;

	private Action expirePopupCloseCallback;

	private Action expireCallback;

	private UILabelLocalized m_labelTitle;

	private UILabelLocalized m_labelSubTitle;

	private UIInput m_input;

	private UIButton m_btnConfirm;

	private Transform m_timerObject;

	private UILabelLocalized labelTitle
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelSubTitle
	{
		get
		{
			return null;
		}
	}

	private UIInput input
	{
		get
		{
			return null;
		}
	}

	private UIButton btnConfirm
	{
		get
		{
			return null;
		}
	}

	private Transform timerObject
	{
		get
		{
			return null;
		}
	}

	public static void Show(string title, string subTitle, string emptyText, string notInputAlertText, Action<string> callback, string defaultValue = "", IDepthManagerblePopup parentPopup = null)
	{
	}

	public static void Show(string title, bool titleTrans, string subTitle, bool subTitleTrans, string emptyText, string notInputAlertText, int limitTimeSec, string expireMsg, Action expireCallback, Action expirePopupCloseCallback, Action<string> callback, string defaultValue = "", IDepthManagerblePopup parentPopup = null)
	{
	}

	public static void Close()
	{
	}

	private void Update()
	{
	}

	protected override void OnDestroy()
	{
	}

	protected override void ShowPanelImpl()
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

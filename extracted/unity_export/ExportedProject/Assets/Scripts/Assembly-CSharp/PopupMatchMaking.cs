using System;
using System.Collections;
using System.Diagnostics;
using BattlePlay;
using UnityEngine;

public class PopupMatchMaking : CaocaoUIModalPopup
{
	public UIButton button;

	public GameObject closeSprite;

	public UILabelLocalized timeLabel;

	public UILabelLocalized message;

	public UILabelLocalized waitingLabel;

	public GameObject boxNoticeLabel;

	public GameObject matchAlarmGo;

	public GameObject opponentText;

	private static PopupMatchMaking self;

	private DateTime m_startDateTime;

	private bool m_forceClosePopup;

	private TimeSpan lastElapsedTime;

	public static bool isAlive
	{
		get
		{
			return false;
		}
	}

	public static void Show()
	{
	}

	public static void ShowMatched(IDepthManagerblePopup parent = null)
	{
	}

	public static void Close()
	{
	}

	private void UpdateOpponentInfo(BattleUser opponentInfo)
	{
	}

	[DebuggerHidden]
	private IEnumerator GotoBan()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator GotoOrder()
	{
		return null;
	}

	private void StartMatchmaking()
	{
	}

	private void UpdateTime()
	{
	}

	private void OnMatchedCallback()
	{
	}

	private void OnMatchmakingCanceledCallback()
	{
	}

	private void OnDisconnectedCallback()
	{
	}

	private void OnCheckMatchmakingCallback(bool isRunningMatchmaking)
	{
	}

	public void CancelMatchmaking()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnApplicationPause(bool pause)
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}

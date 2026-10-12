using System;
using System.Collections;
using System.Diagnostics;
using System.Text;
using UnityEngine;

public class UnderDescript : SelfRegister2UIComponentsMonobehaviour
{
	private float intervalForOneCharacter;

	private float fadeTimeInterval;

	private UILabelLocalized m_label;

	private GameObject waitingIcon;

	private static StringBuilder fullMessage;

	private static StringBuilder currentMsgBuilder;

	private float lastTypingTime;

	private bool waitingForClick;

	private bool onGoing;

	private bool willBeClose;

	private Action callback;

	protected override void Awake()
	{
	}

	public override void OnInit()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void Update()
	{
	}

	public void ShowUnderDescrip(EventCellPatterns.EventPattern_MapProperty condition)
	{
	}

	public void ShowUnderDescription(string Description, bool bOption1, bool bOption2, bool bOption3, bool onGoing, bool atFirst, Action callback)
	{
	}

	private void OnClick()
	{
	}

	[DebuggerHidden]
	private IEnumerator CloseWindow(float waitingTime)
	{
		return null;
	}

	private void Close(bool forceClose)
	{
	}

	public void ChangeLetterTypingMode(bool isAtOneTime)
	{
	}

	private void OnSkip(bool skip)
	{
	}
}

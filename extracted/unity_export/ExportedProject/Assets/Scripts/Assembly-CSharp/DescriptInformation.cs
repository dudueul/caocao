using System;
using System.Collections;
using System.Diagnostics;
using System.Text;
using UnityEngine;

public class DescriptInformation : MonoBehaviour
{
	private const float m_fCheckTimeInterval = 0.8f;

	public UILabelLocalized m_label;

	private float m_fCheckTimesUpInterval;

	private float m_fLastTime;

	private bool m_bOnGoing;

	private bool m_Closing;

	private bool m_Waiting;

	private const float m_fTimeInterval = 0.5f;

	private StringBuilder m_sDialog;

	public GameObject m_WaitingIcon;

	public Action OnUpdateOnce;

	private Action callback;

	private void Awake()
	{
	}

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}

	private void Update()
	{
	}

	public void ShowDescription(string sDialog, bool Waiting, bool onGoing, Action callback)
	{
	}

	[Obsolete]
	public void ShowDescription(string sDialog, bool Waiting, bool OnGoing)
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
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

	private void OnSkip(bool skip)
	{
	}
}

using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class Patcher : MonoBehaviour, IPatchView
{
	private bool isClosed;

	public static Patcher instance;

	public UILabelLocalized m_progressBarText;

	public UISprite m_progressBarUI;

	public UILabelLocalized m_downloadProgressCountText;

	public UILabelLocalized m_downloadPct;

	public FullscreenPatcher m_fullscreenPatcher;

	private void Init()
	{
	}

	public void ProgressTitle(string progress, object param)
	{
	}

	public void ProgressBar(float progress)
	{
	}

	public void ProgressBytes(long downloaded, long total)
	{
	}

	public void DecompressCount(int count, int totalCount)
	{
	}

	public void SetActive(bool active)
	{
	}

	public void Finish()
	{
	}

	public bool IsClosed()
	{
		return false;
	}

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	public void RequestPatchResourceInfo()
	{
	}

	[DebuggerHidden]
	private IEnumerator StartPatch(DateTime eventScriptDate)
	{
		return null;
	}

	public static void BannerClosed()
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowLoginUI()
	{
		return null;
	}
}

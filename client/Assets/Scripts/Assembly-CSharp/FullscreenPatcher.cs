using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class FullscreenPatcher : MonoBehaviour, IPatchView
{
	public UITexture image;

	public GameObject loadingIndicator;

	public GameObject leftButton;

	public UISprite leftButtonSprite;

	public GameObject rightButton;

	public UISprite rightButtonSprite;

	public UILabelLocalized page;

	public GameObject progressBar;

	public GameObject startButton;

	private int m_index;

	private List<string> m_comicBookNameList;

	private Dictionary<string, WWW> m_url2www;

	private Texture2D m_texture;

	private PanelPopup1BtnConfirm m_retryPopup;

	private bool isClosed;

	public UILabelLocalized m_progressBarText;

	public UISlider m_progressBarUI;

	public UILabelLocalized m_downloadProgressCountText;

	private void OnDestroy()
	{
	}

	public void OnClose()
	{
	}

	[DebuggerHidden]
	public IEnumerator StartComicViewer()
	{
		return null;
	}

	private void LoadComicBook(int index, Action onResponse)
	{
	}

	private void PreloadComicBook(int index, Action onResponse)
	{
	}

	public void OnClickNext()
	{
	}

	public void OnClickPrev()
	{
	}

	[DebuggerHidden]
	private IEnumerator OpenUrl(string url, Action<WWW> onResponse)
	{
		return null;
	}

	public void ProgressTitle(string progress, object param = null)
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
}

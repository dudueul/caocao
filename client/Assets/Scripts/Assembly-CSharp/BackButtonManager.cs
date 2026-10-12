using System;
using System.Collections.Generic;
using UnityEngine;

public class BackButtonManager : MonoBehaviour
{
	public static BackButtonManager instance;

	private List<Action> m_onBackButtonListeners;

	public bool useEndingBanner { get; set; }

	public bool useBackButton { get; set; }

	public bool showLockMessage { get; set; }

	public void AddBackButtonListener(Action onBackButtonListener)
	{
	}

	public void RemoveBackButtonListener(Action onBackButtonListener)
	{
	}

	public void Clear()
	{
	}

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	private void LateUpdate()
	{
	}

	private void ShowEndingBanner()
	{
	}
}

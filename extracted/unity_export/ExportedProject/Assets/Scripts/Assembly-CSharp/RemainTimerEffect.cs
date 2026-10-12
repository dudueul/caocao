using System;
using UnityEngine;

public class RemainTimerEffect : MonoBehaviour
{
	private UILabelLocalized m_targetUI;

	private TimeSpan m_doingTime;

	private AudioSource m_alertSound;

	public Action OnTimeUp;

	private bool m_flickerStarted;

	private void OnDestroy()
	{
	}

	public void UpdateTime(int remainSec)
	{
	}

	private void StartTextFlickering()
	{
	}

	private void OnTextFlickering(Color newValue)
	{
	}
}

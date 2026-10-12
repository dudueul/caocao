using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class ShowCritical : MonoBehaviour
{
	private bool m_playing;

	private bool m_isCompleted;

	private UnitAnimation m_unitAnimation;

	public bool isPlaying
	{
		get
		{
			return false;
		}
	}

	public bool isCompleted
	{
		get
		{
			return false;
		}
	}

	public void StartAnimation()
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowCriticalAnmation()
	{
		return null;
	}

	private void OnCriticalAlphaAnimStarted()
	{
	}

	private void OnCriticalAlphaAnimUpdated(float value)
	{
	}

	private void OnCriticalAlphaAnimCompleted()
	{
	}
}

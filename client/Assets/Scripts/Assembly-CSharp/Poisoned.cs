using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class Poisoned : MonoBehaviour
{
	private UnitAnimation m_unitAnimation;

	protected static string m_animationName;

	private bool m_playing;

	private bool m_isCompleted;

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

	public void Stop()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitAndCreateAnimation()
	{
		return null;
	}

	private void CreateAnimation()
	{
	}

	protected void OnPoisonedAnimationStarted()
	{
	}

	protected void OnPoisonedAnimationUpdated(float value)
	{
	}

	protected void OnPoisonedAnimationCompleted()
	{
	}

	protected void OnDestroy()
	{
	}
}

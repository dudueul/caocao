using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class ShowVibrate : MonoBehaviour
{
	private bool m_playing;

	private bool m_isCompleted;

	private Vector3 m_originalPos;

	private float m_amount;

	private float m_duration;

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

	public void StartAnimation(float amount, float duration)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate()
	{
		return null;
	}

	private void OnAnimateCompleted()
	{
	}

	public void ForceFinishAnimation()
	{
	}
}

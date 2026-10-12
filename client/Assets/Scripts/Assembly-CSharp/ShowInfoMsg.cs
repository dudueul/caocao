using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class ShowInfoMsg : MonoBehaviour
{
	private bool m_playing;

	private bool m_isCompleted;

	private GameObject m_infoMsgObj;

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

	public void StartAnimation(float duration, string msg, params object[] args)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate(float duration, string msg, params object[] args)
	{
		return null;
	}

	private void OnDisable()
	{
	}
}

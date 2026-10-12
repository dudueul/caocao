using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class ShowSkillMsg : MonoBehaviour
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

	public void StartAnimation(string msg, int faceId)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate(string msg, int faceId)
	{
		return null;
	}
}

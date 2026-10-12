using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class DuelFuncFlashImpl : MonoBehaviour
{
	private tk2dSpriteAnimator animator;

	private UnitAnimation unitAnimation;

	public bool m_isCompleted;

	public void StartAnimation(float time, bool flashOn)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate(float time, bool flashOn)
	{
		return null;
	}

	private void OnFlashAnimStarted()
	{
	}

	private void OnFlashAnimUpdated(float value)
	{
	}

	private void OnFlashAnimCompleted()
	{
	}
}

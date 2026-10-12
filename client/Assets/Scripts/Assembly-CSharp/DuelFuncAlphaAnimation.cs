using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class DuelFuncAlphaAnimation : MonoBehaviour
{
	private tk2dSprite sprite;

	private tk2dSpriteAnimator animator;

	private tk2dSprite shadowSprite;

	private float m_fromAlpha;

	private float m_toAlpha;

	private float m_time;

	public void StartAnimation(float fromAlpha, float toAlpha, float time)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate()
	{
		return null;
	}

	private void OnUnitAlphaAniStarted()
	{
	}

	private void OnUnitAlphaAniUpdate(float value)
	{
	}

	private void OnUnitAlphaAniCompleted()
	{
	}
}

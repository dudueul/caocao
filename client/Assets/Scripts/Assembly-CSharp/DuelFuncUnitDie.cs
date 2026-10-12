using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class DuelFuncUnitDie : MonoBehaviour
{
	private tk2dSpriteAnimator animator;

	private const string m_clipName = "Groggy";

	private const string m_spriteName = "Groggy_F00";

	private bool m_isCompleted;

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
	private IEnumerator Animate()
	{
		return null;
	}
}

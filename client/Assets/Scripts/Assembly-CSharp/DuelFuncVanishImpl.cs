using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class DuelFuncVanishImpl : MonoBehaviour
{
	public bool m_isCompleted;

	private bool _doVanish;

	public void StartAnimation(float time, bool doVanish)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate(float time, bool doVanish)
	{
		return null;
	}

	private void OnVanishAnimCompleted()
	{
	}
}

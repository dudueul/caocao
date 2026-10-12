using System.Collections;
using System.Diagnostics;
using UnityEngine;

[DisallowMultipleComponent]
public class DeltaDamageAlphaTweener : MonoBehaviour
{
	private float fromAlpha;

	private float toAlpha;

	private float duration;

	private string iTweenName;

	public void StartAnimation()
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate()
	{
		return null;
	}

	private void OnUpdate(float value)
	{
	}

	public void StopAlphaTweening()
	{
	}
}

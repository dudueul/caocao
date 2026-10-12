using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class ShowMapDimming : MonoBehaviour
{
	private SpriteRenderer dimmingRenderer;

	private float m_time;

	private float originAlpha;

	private float destAlpha;

	public bool isDimmed;

	public void StartAnimation(float time, BattleDimmer.DimmingState dimmingState)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate(float time, BattleDimmer.DimmingState dimmingState)
	{
		return null;
	}

	public void OnUpdate(float value)
	{
	}

	public void OnDimmed()
	{
	}

	public void StopDimming()
	{
	}

	private void OnStopDimmingEffect()
	{
	}
}

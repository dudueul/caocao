using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

internal class EventTwinkleMarking : MonoBehaviour
{
	private int m_RotationTime;

	private Func<GameObject, IEnumerator> m_twinkleFunc;

	public void StartTwinkleTile(bool settled)
	{
	}

	public void StartTwinkleTile(bool settled, Func<GameObject, IEnumerator> twinkleFunc)
	{
	}

	[DebuggerHidden]
	private IEnumerator TwinkleTile(bool settled)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator TurnOnOffTile(GameObject go)
	{
		return null;
	}

	private void TurnOnTile(SpriteRenderer sprRenderer, bool IsTurnOn)
	{
	}

	private void DetachTile()
	{
	}

	public void DestroyTwinkleTile()
	{
	}
}

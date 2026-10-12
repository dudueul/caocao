using System.Collections;
using System.Diagnostics;
using UnityEngine;
using UnityEngine.UI;

public class SlotTwinkleMarking : MonoBehaviour
{
	private const float TWINKLE_TIME = 0.3f;

	private const float MIN_ALPHA = 0.3f;

	private Coroutine coroutine;

	private Color prevColor;

	public Image image { get; set; }

	private bool isPlaying
	{
		get
		{
			return false;
		}
	}

	public void StopTwinkle()
	{
	}

	public void StartTwinkle()
	{
	}

	[DebuggerHidden]
	private IEnumerator Twinkle()
	{
		return null;
	}

	private void OnDestroy()
	{
	}
}

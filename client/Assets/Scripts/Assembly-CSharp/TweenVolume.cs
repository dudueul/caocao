using System;
using UnityEngine;

public class TweenVolume : UITweener
{
	public float from;

	public float to;

	private AudioSource mSource;

	public AudioSource audioSource
	{
		get
		{
			return null;
		}
	}

	[Obsolete]
	public float volume
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float value
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	protected override void OnUpdate(float factor, bool isFinished)
	{
	}

	public static TweenVolume Begin(GameObject go, float duration, float targetVolume)
	{
		return null;
	}

	public override void SetStartToCurrentValue()
	{
	}

	public override void SetEndToCurrentValue()
	{
	}
}

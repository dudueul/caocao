using System;
using UnityEngine;

public class TweenAlpha : UITweener
{
	public float from;

	public float to;

	private bool mCached;

	private UIRect mRect;

	private Material mMat;

	private SpriteRenderer mSr;

	[Obsolete]
	public float alpha
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

	private void Cache()
	{
	}

	protected override void OnUpdate(float factor, bool isFinished)
	{
	}

	public static TweenAlpha Begin(GameObject go, float duration, float alpha)
	{
		return null;
	}

	public override void SetStartToCurrentValue()
	{
	}

	public override void SetEndToCurrentValue()
	{
	}

	private void OnDestroy()
	{
	}
}

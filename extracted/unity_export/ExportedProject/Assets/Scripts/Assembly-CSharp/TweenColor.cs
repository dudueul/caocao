using System;
using UnityEngine;

public class TweenColor : UITweener
{
	public Color from;

	public Color to;

	private bool mCached;

	private UIWidget mWidget;

	private Material mMat;

	private Light mLight;

	private SpriteRenderer mSr;

	[Obsolete]
	public Color color
	{
		get
		{
			return default(Color);
		}
		set
		{
		}
	}

	public Color value
	{
		get
		{
			return default(Color);
		}
		set
		{
		}
	}

	private void OnDestroy()
	{
	}

	private void Cache()
	{
	}

	protected override void OnUpdate(float factor, bool isFinished)
	{
	}

	public static TweenColor Begin(GameObject go, float duration, Color color)
	{
		return null;
	}

	public override void SetStartToCurrentValue()
	{
	}

	public override void SetEndToCurrentValue()
	{
	}

	private void SetCurrentValueToStart()
	{
	}

	private void SetCurrentValueToEnd()
	{
	}
}

using System;
using UnityEngine;

public class TweenOrthoSize : UITweener
{
	public float from;

	public float to;

	private Camera mCam;

	public Camera cachedCamera
	{
		get
		{
			return null;
		}
	}

	[Obsolete]
	public float orthoSize
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

	public static TweenOrthoSize Begin(GameObject go, float duration, float to)
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

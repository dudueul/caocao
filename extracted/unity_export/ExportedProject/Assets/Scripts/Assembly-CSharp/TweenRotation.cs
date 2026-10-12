using System;
using UnityEngine;

public class TweenRotation : UITweener
{
	public Vector3 from;

	public Vector3 to;

	public bool quaternionLerp;

	private Transform mTrans;

	public Transform cachedTransform
	{
		get
		{
			return null;
		}
	}

	[Obsolete]
	public Quaternion rotation
	{
		get
		{
			return default(Quaternion);
		}
		set
		{
		}
	}

	public Quaternion value
	{
		get
		{
			return default(Quaternion);
		}
		set
		{
		}
	}

	protected override void OnUpdate(float factor, bool isFinished)
	{
	}

	public static TweenRotation Begin(GameObject go, float duration, Quaternion rot)
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

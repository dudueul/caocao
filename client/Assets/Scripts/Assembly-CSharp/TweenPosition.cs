using System;
using UnityEngine;

public class TweenPosition : UITweener
{
	public Vector3 from;

	public Vector3 to;

	[HideInInspector]
	public bool worldSpace;

	private Transform mTrans;

	private UIRect mRect;

	public Transform cachedTransform
	{
		get
		{
			return null;
		}
	}

	[Obsolete]
	public Vector3 position
	{
		get
		{
			return default(Vector3);
		}
		set
		{
		}
	}

	public Vector3 value
	{
		get
		{
			return default(Vector3);
		}
		set
		{
		}
	}

	private void Awake()
	{
	}

	protected override void OnUpdate(float factor, bool isFinished)
	{
	}

	public static TweenPosition Begin(GameObject go, float duration, Vector3 pos)
	{
		return null;
	}

	public static TweenPosition Begin(GameObject go, float duration, Vector3 pos, bool worldSpace)
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

using System;
using UnityEngine;

public class TweenScale : UITweener
{
	public Vector3 from;

	public Vector3 to;

	public bool updateTable;

	private Transform mTrans;

	private UITable mTable;

	public Transform cachedTransform
	{
		get
		{
			return null;
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

	[Obsolete]
	public Vector3 scale
	{
		get
		{
			return default(Vector3);
		}
		set
		{
		}
	}

	protected override void OnUpdate(float factor, bool isFinished)
	{
	}

	public static TweenScale Begin(GameObject go, float duration, Vector3 scale)
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

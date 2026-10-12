using System.Collections.Generic;
using AnimationOrTween;
using UnityEngine;

public class ActiveAnimation : MonoBehaviour
{
	public static ActiveAnimation current;

	public List<EventDelegate> onFinished;

	[HideInInspector]
	public GameObject eventReceiver;

	[HideInInspector]
	public string callWhenFinished;

	private Animation mAnim;

	private Direction mLastDirection;

	private Direction mDisableDirection;

	private bool mNotify;

	private Animator mAnimator;

	private string mClip;

	private float playbackTime
	{
		get
		{
			return 0f;
		}
	}

	public bool isPlaying
	{
		get
		{
			return false;
		}
	}

	public void Finish()
	{
	}

	public void Reset()
	{
	}

	private void Start()
	{
	}

	private void Update()
	{
	}

	private void Play(string clipName, Direction playDirection)
	{
	}

	public static ActiveAnimation Play(Animation anim, string clipName, Direction playDirection, EnableCondition enableBeforePlay, DisableCondition disableCondition)
	{
		return null;
	}

	public static ActiveAnimation Play(Animation anim, string clipName, Direction playDirection)
	{
		return null;
	}

	public static ActiveAnimation Play(Animation anim, Direction playDirection)
	{
		return null;
	}

	public static ActiveAnimation Play(Animator anim, string clipName, Direction playDirection, EnableCondition enableBeforePlay, DisableCondition disableCondition)
	{
		return null;
	}
}

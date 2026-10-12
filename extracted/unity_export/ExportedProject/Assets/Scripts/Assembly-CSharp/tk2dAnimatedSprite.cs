using System;
using UnityEngine;

public class tk2dAnimatedSprite : tk2dSprite
{
	public delegate void AnimationCompleteDelegate(tk2dAnimatedSprite sprite, int clipId);

	public delegate void AnimationEventDelegate(tk2dAnimatedSprite sprite, tk2dSpriteAnimationClip clip, tk2dSpriteAnimationFrame frame, int frameNum);

	[SerializeField]
	private tk2dSpriteAnimator _animator;

	[SerializeField]
	private tk2dSpriteAnimation anim;

	[SerializeField]
	private int clipId;

	public bool playAutomatically;

	public bool createCollider;

	public AnimationCompleteDelegate animationCompleteDelegate;

	public AnimationEventDelegate animationEventDelegate;

	public tk2dSpriteAnimator Animator
	{
		get
		{
			return null;
		}
	}

	public tk2dSpriteAnimation Library
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int DefaultClipId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public static bool g_paused
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool Paused
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public tk2dSpriteAnimationClip CurrentClip
	{
		get
		{
			return null;
		}
	}

	public float ClipTimeSeconds
	{
		get
		{
			return 0f;
		}
	}

	public float ClipFps
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public bool Playing
	{
		get
		{
			return false;
		}
	}

	public static float DefaultFps
	{
		get
		{
			return 0f;
		}
	}

	private void CheckAddAnimatorInternal()
	{
	}

	protected override bool NeedBoxCollider()
	{
		return false;
	}

	private void ProxyCompletedHandler(tk2dSpriteAnimator anim, tk2dSpriteAnimationClip clip)
	{
	}

	private void ProxyEventTriggeredHandler(tk2dSpriteAnimator anim, tk2dSpriteAnimationClip clip, int frame)
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void Start()
	{
	}

	public static tk2dAnimatedSprite AddComponent(GameObject go, tk2dSpriteAnimation anim, int clipId)
	{
		return null;
	}

	public void Play()
	{
	}

	public void Play(float clipStartTime)
	{
	}

	public void PlayFromFrame(int frame)
	{
	}

	public void Play(string name)
	{
	}

	public void PlayFromFrame(string name, int frame)
	{
	}

	public void Play(string name, float clipStartTime)
	{
	}

	public void Play(tk2dSpriteAnimationClip clip, float clipStartTime)
	{
	}

	public void Play(tk2dSpriteAnimationClip clip, float clipStartTime, float overrideFps)
	{
	}

	public void Stop()
	{
	}

	public void StopAndResetFrame()
	{
	}

	[Obsolete]
	public bool isPlaying()
	{
		return false;
	}

	public bool IsPlaying(string name)
	{
		return false;
	}

	public bool IsPlaying(tk2dSpriteAnimationClip clip)
	{
		return false;
	}

	public int GetClipIdByName(string name)
	{
		return 0;
	}

	public tk2dSpriteAnimationClip GetClipByName(string name)
	{
		return null;
	}

	public void Pause()
	{
	}

	public void Resume()
	{
	}

	public void SetFrame(int currFrame)
	{
	}

	public void SetFrame(int currFrame, bool triggerEvent)
	{
	}

	public void UpdateAnimation(float deltaTime)
	{
	}
}

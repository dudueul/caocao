using System.Collections.Generic;
using UnityEngine;

[ExecuteInEditMode]
public class UISpriteAnimation : MonoBehaviour
{
	[HideInInspector]
	[SerializeField]
	protected int mFPS;

	[HideInInspector]
	[SerializeField]
	protected string mPrefix;

	[HideInInspector]
	[SerializeField]
	protected bool mLoop;

	[HideInInspector]
	[SerializeField]
	protected bool mSnap;

	protected UISprite mSprite;

	protected float mDelta;

	protected int mIndex;

	protected bool mActive;

	protected List<string> mSpriteNames;

	public int frames
	{
		get
		{
			return 0;
		}
	}

	public int framesPerSecond
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string namePrefix
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool loop
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isPlaying
	{
		get
		{
			return false;
		}
	}

	protected virtual void Start()
	{
	}

	protected virtual void Update()
	{
	}

	public void RebuildSpriteList()
	{
	}

	public void Play()
	{
	}

	public void Pause()
	{
	}

	public void ResetToBeginning()
	{
	}
}

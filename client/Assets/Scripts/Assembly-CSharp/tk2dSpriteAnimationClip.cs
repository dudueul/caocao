using System;

[Serializable]
public class tk2dSpriteAnimationClip
{
	public enum WrapMode
	{
		Loop = 0,
		LoopSection = 1,
		Once = 2,
		PingPong = 3,
		RandomFrame = 4,
		RandomLoop = 5,
		Single = 6
	}

	public bool autoNaming;

	public string name;

	public tk2dSpriteAnimationFrame[] frames;

	public float fps;

	public int loopStart;

	public WrapMode wrapMode;

	public bool Empty
	{
		get
		{
			return false;
		}
	}

	public tk2dSpriteAnimationClip()
	{
	}

	public tk2dSpriteAnimationClip(tk2dSpriteAnimationClip source)
	{
	}

	public void CopyFrom(tk2dSpriteAnimationClip source)
	{
	}

	public void Clear()
	{
	}

	public tk2dSpriteAnimationFrame GetFrame(int frame)
	{
		return null;
	}
}

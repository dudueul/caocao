using System;
using UnityEngine;

[Serializable]
public class tk2dSpriteAnimationFrame
{
	public tk2dSpriteCollectionData spriteCollection;

	public int spriteId;

	public bool triggerEvent;

	public Vector2 framePosition;

	public Vector2 frameScale;

	public float frameRotation;

	public tk2dCustomTrigger customTrigger;

	public string eventInfo;

	public int eventInt;

	public float eventFloat;

	public bool splitFlag;

	public int groupID;

	public override string ToString()
	{
		return null;
	}

	public void CopyFrom(tk2dSpriteAnimationFrame source)
	{
	}

	public void CopyTriggerFrom(tk2dSpriteAnimationFrame source)
	{
	}

	public void ClearTrigger()
	{
	}

	public void CopyFrom(tk2dSpriteAnimationFrame source, bool full)
	{
	}
}

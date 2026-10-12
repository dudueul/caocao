using System;
using UnityEngine;

[Serializable]
public class tk2dSpriteColliderIsland
{
	public bool connected;

	public Vector2[] points;

	public bool IsValid()
	{
		return false;
	}

	public void CopyFrom(tk2dSpriteColliderIsland src)
	{
	}

	public bool CompareTo(tk2dSpriteColliderIsland src)
	{
		return false;
	}
}

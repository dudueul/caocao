using System;
using UnityEngine;

[Serializable]
public class tk2dSpriteSheetSource
{
	public enum Anchor
	{
		UpperLeft = 0,
		UpperCenter = 1,
		UpperRight = 2,
		MiddleLeft = 3,
		MiddleCenter = 4,
		MiddleRight = 5,
		LowerLeft = 6,
		LowerCenter = 7,
		LowerRight = 8
	}

	public enum SplitMethod
	{
		UniformDivision = 0
	}

	public Texture2D texture;

	public int tilesX;

	public int tilesY;

	public int numTiles;

	public Anchor anchor;

	public tk2dSpriteCollectionDefinition.Pad pad;

	public Vector3 scale;

	public bool additive;

	public bool active;

	public int tileWidth;

	public int tileHeight;

	public int tileMarginX;

	public int tileMarginY;

	public int tileSpacingX;

	public int tileSpacingY;

	public SplitMethod splitMethod;

	public int version;

	public const int CURRENT_VERSION = 1;

	public tk2dSpriteCollectionDefinition.ColliderType colliderType;

	public string Name
	{
		get
		{
			return null;
		}
	}

	public void CopyFrom(tk2dSpriteSheetSource src)
	{
	}

	public bool CompareTo(tk2dSpriteSheetSource src)
	{
		return false;
	}
}

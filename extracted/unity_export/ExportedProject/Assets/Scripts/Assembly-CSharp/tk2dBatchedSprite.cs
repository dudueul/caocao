using System;
using UnityEngine;

[Serializable]
public class tk2dBatchedSprite
{
	public enum Type
	{
		EmptyGameObject = 0,
		Sprite = 1,
		TiledSprite = 2,
		SlicedSprite = 3,
		ClippedSprite = 4,
		TextMesh = 5
	}

	[Flags]
	public enum Flags
	{
		None = 0,
		Sprite_CreateBoxCollider = 1,
		SlicedSprite_BorderOnly = 2
	}

	public Type type;

	public string name;

	public int parentId;

	public int spriteId;

	public int xRefId;

	public tk2dSpriteCollectionData spriteCollection;

	public Quaternion rotation;

	public Vector3 position;

	public Vector3 localScale;

	public Color color;

	public Vector3 baseScale;

	public int renderLayer;

	[SerializeField]
	private Vector2 internalData0;

	[SerializeField]
	private Vector2 internalData1;

	[SerializeField]
	private Vector2 internalData2;

	[SerializeField]
	private Vector2 colliderData;

	[SerializeField]
	private string formattedText;

	[SerializeField]
	private Flags flags;

	public tk2dBaseSprite.Anchor anchor;

	public Matrix4x4 relativeMatrix;

	private Vector3 cachedBoundsCenter;

	private Vector3 cachedBoundsExtents;

	public float BoxColliderOffsetZ
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float BoxColliderExtentZ
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public string FormattedText
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Vector2 ClippedSpriteRegionBottomLeft
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

	public Vector2 ClippedSpriteRegionTopRight
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

	public Vector2 SlicedSpriteBorderBottomLeft
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

	public Vector2 SlicedSpriteBorderTopRight
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

	public Vector2 Dimensions
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

	public bool IsDrawn
	{
		get
		{
			return false;
		}
	}

	public Vector3 CachedBoundsCenter
	{
		get
		{
			return default(Vector3);
		}
		set
		{
		}
	}

	public Vector3 CachedBoundsExtents
	{
		get
		{
			return default(Vector3);
		}
		set
		{
		}
	}

	public bool CheckFlag(Flags mask)
	{
		return false;
	}

	public void SetFlag(Flags mask, bool value)
	{
	}

	public tk2dSpriteDefinition GetSpriteDefinition()
	{
		return null;
	}
}

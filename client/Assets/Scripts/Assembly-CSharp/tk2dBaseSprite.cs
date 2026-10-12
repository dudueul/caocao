using System;
using System.Collections.Generic;
using UnityEngine;
using tk2dRuntime;

public abstract class tk2dBaseSprite : MonoBehaviour, ISpriteCollectionForceBuild, IWeakResourceRef
{
	public enum Anchor
	{
		LowerLeft = 0,
		LowerCenter = 1,
		LowerRight = 2,
		MiddleLeft = 3,
		MiddleCenter = 4,
		MiddleRight = 5,
		UpperLeft = 6,
		UpperCenter = 7,
		UpperRight = 8
	}

	[SerializeField]
	private tk2dSpriteCollectionData collection;

	protected tk2dSpriteCollectionData collectionInst;

	[SerializeField]
	protected Color _color;

	[SerializeField]
	protected Vector3 _scale;

	[SerializeField]
	protected int _spriteId;

	public BoxCollider2D boxCollider2D;

	public List<PolygonCollider2D> polygonCollider2D;

	public List<EdgeCollider2D> edgeCollider2D;

	public BoxCollider boxCollider;

	public MeshCollider meshCollider;

	public Vector3[] meshColliderPositions;

	public Mesh meshColliderMesh;

	private Action<tk2dBaseSprite> SpriteChanged__BackingField;

	protected Vector2 _framePosition;

	protected Vector2 _frameScale;

	protected float _frameRotation;

	protected bool materialManualMode;

	public Vector3 m_spriteOffset;

	private Renderer _cachedRenderer;

	[SerializeField]
	protected int renderLayer;

	[SerializeField]
	public string mSpriteCollectionDataPath;

	[SerializeField]
	public int mSspriteId;

	public tk2dSpriteCollectionData Collection
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Vector3 spriteOffset
	{
		get
		{
			return default(Vector3);
		}
		set
		{
		}
	}

	public Color color
	{
		get
		{
			return default(Color);
		}
		set
		{
		}
	}

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

	private Renderer CachedRenderer
	{
		get
		{
			return null;
		}
	}

	public int SortingOrder
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool FlipX
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool FlipY
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public int spriteId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool MaterialManualMode
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public tk2dSpriteDefinition CurrentSprite
	{
		get
		{
			return null;
		}
	}

	public event Action<tk2dBaseSprite> SpriteChanged
	{
		add
		{
		}
		remove
		{
		}
	}

	private void InitInstance()
	{
	}

	public void SetSprite(int newSpriteId)
	{
	}

	public bool SetSprite(string spriteName)
	{
		return false;
	}

	public void SetSprite(tk2dSpriteCollectionData newCollection, int newSpriteId)
	{
	}

	public void SetSprite(tk2dSpriteCollectionData newCollection, int newSpriteId, tk2dSpriteAnimationFrame frame)
	{
	}

	public bool SetSprite(tk2dSpriteCollectionData newCollection, string spriteName)
	{
		return false;
	}

	public void MakePixelPerfect()
	{
	}

	public void ForceUpdateMaterial()
	{
	}

	protected abstract void UpdateMaterial();

	protected abstract void UpdateColors();

	protected abstract void UpdateVertices();

	protected abstract void UpdateGeometry();

	protected abstract int GetCurrentVertexCount();

	public abstract void Build();

	public int GetSpriteIdByName(string name)
	{
		return 0;
	}

	public static T AddComponent<T>(GameObject go, tk2dSpriteCollectionData spriteCollection, int spriteId) where T : tk2dBaseSprite
	{
		return null;
	}

	public static T AddComponent<T>(GameObject go, tk2dSpriteCollectionData spriteCollection, string spriteName) where T : tk2dBaseSprite
	{
		return null;
	}

	protected int GetNumVertices()
	{
		return 0;
	}

	protected int GetNumIndices()
	{
		return 0;
	}

	protected void SetPositions(Vector3[] positions, Vector3[] normals, Vector4[] tangents)
	{
	}

	protected void SetColors(Color32[] dest)
	{
	}

	public Bounds GetBounds()
	{
		return default(Bounds);
	}

	public Bounds GetUntrimmedBounds()
	{
		return default(Bounds);
	}

	public static Bounds AdjustedMeshBounds(Bounds bounds, int renderLayer)
	{
		return default(Bounds);
	}

	public tk2dSpriteDefinition GetCurrentSpriteDef()
	{
		return null;
	}

	public virtual void ReshapeBounds(Vector3 dMin, Vector3 dMax)
	{
	}

	protected virtual bool NeedBoxCollider()
	{
		return false;
	}

	protected virtual void UpdateCollider()
	{
	}

	protected virtual void CreateCollider()
	{
	}

	protected void Awake()
	{
	}

	protected virtual void OnDestroy()
	{
	}

	public void CreateSimpleBoxCollider()
	{
	}

	public bool UsesSpriteCollection(tk2dSpriteCollectionData spriteCollection)
	{
		return false;
	}

	public virtual void ForceBuild()
	{
	}

	public static GameObject CreateFromTexture<T>(Texture texture, tk2dSpriteCollectionSize size, Rect region, Vector2 anchor) where T : tk2dBaseSprite
	{
		return null;
	}

	public void DisconnectResourceRef(List<string> adjustedPaths)
	{
	}

	void IWeakResourceRef.ConnectResourceRef()
	{
	}
}

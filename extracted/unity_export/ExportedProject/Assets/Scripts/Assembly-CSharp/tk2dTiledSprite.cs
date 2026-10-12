using UnityEngine;

[ExecuteInEditMode]
public class tk2dTiledSprite : tk2dBaseSprite
{
	private Mesh mesh;

	private Vector2[] meshUvs;

	private Vector3[] meshVertices;

	private Color32[] meshColors;

	private Vector3[] meshNormals;

	private Vector4[] meshTangents;

	private int[] meshIndices;

	private float textureOffset;

	[SerializeField]
	private Vector2 _dimensions;

	[SerializeField]
	private Anchor _anchor;

	[SerializeField]
	protected bool _createBoxCollider;

	private Vector3 boundsCenter;

	private Vector3 boundsExtents;

	public Vector2 dimensions
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

	public Anchor anchor
	{
		get
		{
			return Anchor.LowerLeft;
		}
		set
		{
		}
	}

	public bool IsScrolling
	{
		get
		{
			return false;
		}
	}

	public float TextureOffset
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public bool CreateBoxCollider
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private new void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	protected new void SetColors(Color32[] dest)
	{
	}

	public override void Build()
	{
	}

	protected override void UpdateGeometry()
	{
	}

	protected override void UpdateColors()
	{
	}

	protected override void UpdateVertices()
	{
	}

	protected void UpdateColorsImpl()
	{
	}

	protected void UpdateGeometryImpl()
	{
	}

	protected override void UpdateCollider()
	{
	}

	protected override void CreateCollider()
	{
	}

	protected override void UpdateMaterial()
	{
	}

	protected override int GetCurrentVertexCount()
	{
		return 0;
	}

	public override void ReshapeBounds(Vector3 dMin, Vector3 dMax)
	{
	}
}

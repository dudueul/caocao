using UnityEngine;

[ExecuteInEditMode]
public class tk2dClippedSprite : tk2dBaseSprite
{
	private Mesh mesh;

	private Vector2[] meshUvs;

	private Vector3[] meshVertices;

	private Color32[] meshColors;

	private Vector3[] meshNormals;

	private Vector4[] meshTangents;

	private int[] meshIndices;

	public Vector2 _clipBottomLeft;

	public Vector2 _clipTopRight;

	private Rect _clipRect;

	[SerializeField]
	protected bool _createBoxCollider;

	private Vector3 boundsCenter;

	private Vector3 boundsExtents;

	public Rect ClipRect
	{
		get
		{
			return default(Rect);
		}
		set
		{
		}
	}

	public Vector2 clipBottomLeft
	{
		get
		{
			return default(Vector2);
		}
		set
		{
		}
	}

	public Vector2 clipTopRight
	{
		get
		{
			return default(Vector2);
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

	protected void SetGeometry(Vector3[] vertices, Vector2[] uvs)
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

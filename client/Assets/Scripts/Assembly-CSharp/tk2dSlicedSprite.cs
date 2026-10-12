using UnityEngine;

[ExecuteInEditMode]
public class tk2dSlicedSprite : tk2dBaseSprite
{
	private Mesh mesh;

	private Vector2[] meshUvs;

	private Vector3[] meshVertices;

	private Color32[] meshColors;

	private Vector3[] meshNormals;

	private Vector4[] meshTangents;

	private int[] meshIndices;

	[SerializeField]
	private Vector2 _dimensions;

	[SerializeField]
	private Anchor _anchor;

	[SerializeField]
	private bool _borderOnly;

	[SerializeField]
	private bool legacyMode;

	public float borderTop;

	public float borderBottom;

	public float borderLeft;

	public float borderRight;

	[SerializeField]
	protected bool _createBoxCollider;

	private Vector3 boundsCenter;

	private Vector3 boundsExtents;

	public bool BorderOnly
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

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

	public void SetBorder(float left, float bottom, float right, float top)
	{
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

	private void SetIndices()
	{
	}

	private bool NearEnough(float value, float compValue, float scale)
	{
		return false;
	}

	private void PermanentUpgradeLegacyMode()
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

	private void UpdateIndices()
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

using System;
using UnityEngine;

[ExecuteInEditMode]
public class tk2dSprite : tk2dBaseSprite
{
	[NonSerialized]
	public bool StopUpdate;

	private Mesh mesh;

	private Vector3[] meshVertices;

	private Vector3[] meshNormals;

	private Vector4[] meshTangents;

	private Color32[] meshColors;

	private Vector2[] meshUvs;

	private int[] meshIndices;

	private float textureOffset;

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

	public static tk2dSprite AddComponent(GameObject go, tk2dSpriteCollectionData spriteCollection, int spriteId)
	{
		return null;
	}

	public static tk2dSprite AddComponent(GameObject go, tk2dSpriteCollectionData spriteCollection, string spriteName)
	{
		return null;
	}

	public static GameObject CreateFromTexture(Texture texture, tk2dSpriteCollectionSize size, Rect region, Vector2 anchor)
	{
		return null;
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

	protected void UpdateVerticesImpl()
	{
	}

	protected void UpdateGeometryImpl()
	{
	}

	protected override void UpdateMaterial()
	{
	}

	protected override int GetCurrentVertexCount()
	{
		return 0;
	}

	public override void ForceBuild()
	{
	}

	public override void ReshapeBounds(Vector3 dMin, Vector3 dMax)
	{
	}
}

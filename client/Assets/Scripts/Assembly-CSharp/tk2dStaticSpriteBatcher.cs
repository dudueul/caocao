using UnityEngine;
using tk2dRuntime;

[ExecuteInEditMode]
public class tk2dStaticSpriteBatcher : MonoBehaviour, ISpriteCollectionForceBuild
{
	public enum Flags
	{
		None = 0,
		GenerateCollider = 1,
		FlattenDepth = 2,
		SortToCamera = 4
	}

	public static int CURRENT_VERSION;

	public int version;

	public tk2dBatchedSprite[] batchedSprites;

	public tk2dTextMeshData[] allTextMeshData;

	public tk2dSpriteCollectionData spriteCollection;

	[SerializeField]
	private Flags flags;

	private Mesh mesh;

	private Mesh colliderMesh;

	[SerializeField]
	private Vector3 _scale;

	public bool CheckFlag(Flags mask)
	{
		return false;
	}

	public void SetFlag(Flags mask, bool value)
	{
	}

	private void Awake()
	{
	}

	private bool UpgradeData()
	{
		return false;
	}

	protected void OnDestroy()
	{
	}

	public void UpdateMatrices()
	{
	}

	public void Build()
	{
	}

	private void SortBatchedSprites()
	{
	}

	private Material GetMaterial(tk2dBatchedSprite bs)
	{
		return null;
	}

	private void BuildRenderMesh()
	{
	}

	private void BuildPhysicsMesh()
	{
	}

	private void BuildPhysicsMesh2D(EdgeCollider2D[] edgeColliders, int numEdgeColliders)
	{
	}

	private void BuildPhysicsMesh3D(MeshCollider meshCollider, bool flattenDepth, int numVertices, int numIndices)
	{
	}

	public bool UsesSpriteCollection(tk2dSpriteCollectionData spriteCollection)
	{
		return false;
	}

	public void ForceBuild()
	{
	}
}

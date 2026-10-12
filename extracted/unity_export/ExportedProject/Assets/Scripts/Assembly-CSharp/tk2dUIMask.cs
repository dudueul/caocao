using UnityEngine;

[ExecuteInEditMode]
public class tk2dUIMask : MonoBehaviour
{
	public tk2dBaseSprite.Anchor anchor;

	public Vector2 size;

	public float depth;

	public bool createBoxCollider;

	private MeshFilter _thisMeshFilter;

	private BoxCollider _thisBoxCollider;

	private static readonly Vector2[] uv;

	private static readonly int[] indices;

	private MeshFilter ThisMeshFilter
	{
		get
		{
			return null;
		}
	}

	private BoxCollider ThisBoxCollider
	{
		get
		{
			return null;
		}
	}

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	private Mesh FillMesh(Mesh mesh)
	{
		return null;
	}

	private void OnDrawGizmosSelected()
	{
	}

	public void Build()
	{
	}

	public void ReshapeBounds(Vector3 dMin, Vector3 dMax)
	{
	}
}

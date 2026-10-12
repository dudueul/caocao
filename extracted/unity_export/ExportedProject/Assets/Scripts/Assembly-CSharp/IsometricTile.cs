using UnityEngine;

public class IsometricTile : MonoBehaviour
{
	private static float ms_width;

	private static float ms_height;

	private static Vector3[] ms_tileVertices;

	private static Vector2[] ms_tileUV;

	private static int[] ms_tileTriangles;

	private static Mesh ms_lineRenderMesh;

	private static Material ms_lineRenderMat;

	private LineRenderer m_lineRenderer;

	public IntVector2 isoGridPos { get; set; }

	public bool showTileBorder
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private void OnEnable()
	{
	}

	private void Start()
	{
	}
}

using UnityEngine;

public class GuildWarMinimapTiles : MonoBehaviour
{
	[SerializeField]
	private MeshFilter m_meshFilter;

	[SerializeField]
	private MeshRenderer m_meshRender;

	private Mesh m_mesh;

	private Mesh mesh
	{
		get
		{
			return null;
		}
	}

	public void SetVertices(Vector3[] vertices)
	{
	}

	public Vector3[] GetVertices()
	{
		return null;
	}

	public void SetTriangles(int[] triangles)
	{
	}

	public int[] GetTriangles()
	{
		return null;
	}

	public void SetColors(Color32[] colors32)
	{
	}

	public Color32[] GetColors()
	{
		return null;
	}

	private void Start()
	{
	}
}

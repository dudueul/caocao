using UnityEngine;

public class PNGSprite : MonoBehaviour
{
	public int m_pixelsPerUnit;

	private string m_path;

	private Texture m_loadedTexture;

	private Vector2 m_loadedTextureDimension;

	private IntVector2 m_areaCount;

	private Mesh m_mesh;

	private Vector3[] m_vertices;

	private Vector2[] m_uvs;

	private int[] m_triangles;

	public string path
	{
		get
		{
			return null;
		}
	}

	public Texture loadedTexture
	{
		get
		{
			return null;
		}
	}

	public IntVector2 areaCount
	{
		get
		{
			return default(IntVector2);
		}
		set
		{
		}
	}

	public void Load(string path, int width, int height)
	{
	}

	private void OnDestroy()
	{
	}
}

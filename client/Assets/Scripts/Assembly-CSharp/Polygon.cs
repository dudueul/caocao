using UnityEngine;

[ExecuteInEditMode]
public class Polygon : MonoBehaviour
{
	public enum Type
	{
		None = 0,
		Base = 1,
		Basic = 2,
		Buffed = 3
	}

	private float m_radius;

	public Type polygonType;

	public Color32 m_color;

	public int m_Str;

	public int m_Wis;

	public int m_Cha;

	public int m_Dex;

	public int m_Luck;

	private Mesh m_mesh;

	private void Awake()
	{
	}

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}

	public void SetStat(int str, int wis, int cha, int dex, int luck)
	{
	}

	private void DrawPolygon(Vector2[] vertices2D)
	{
	}

	private Vector2[] GetVertices()
	{
		return null;
	}
}

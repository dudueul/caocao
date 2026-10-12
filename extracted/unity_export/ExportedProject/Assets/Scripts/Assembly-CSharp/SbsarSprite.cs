using UnityEngine;

public class SbsarSprite : MonoBehaviour
{
	public int m_pixelsPerUnit;

	private IntVector2 m_areaCount;

	private Mesh m_mesh;

	private Material m_mat;

	public string path { get; private set; }

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

	public void LoadWithMaterial(Material material, IntVector2 realMapSize, IntVector2 colunmByRow)
	{
	}

	public void Load(string sbsarSourcePath, IntVector2 realMapSize, IntVector2 colunmByRow)
	{
	}

	private void _Load(IntVector2 realMapSize, IntVector2 colunmByRow)
	{
	}

	protected void OnDestroy()
	{
	}
}

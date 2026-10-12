using UnityEngine;

public class DuelCoordinatesManager
{
	private int m_maximumXCount;

	private int m_maximumYCount;

	private float m_tileWidth;

	private float m_tileHeight;

	public float m_resizeFactor;

	public int unitInitPosY;

	public int xCount
	{
		get
		{
			return 0;
		}
	}

	public int yCount
	{
		get
		{
			return 0;
		}
	}

	public float tileWidth
	{
		get
		{
			return 0f;
		}
	}

	public float tileHeight
	{
		get
		{
			return 0f;
		}
	}

	public void Initialize()
	{
	}

	private void OnDestroy()
	{
	}

	public Vector3 GetWorldPosByGridPos(int x)
	{
		return default(Vector3);
	}

	public Vector3 GetWorldPosByGridPos(int x, int y)
	{
		return default(Vector3);
	}

	public IntVector2 GetGridPosByWorldPos(Vector3 worldPos)
	{
		return default(IntVector2);
	}

	public Vector3 GetWorldPosByFloatGridPos(Vector2 floatGridPos)
	{
		return default(Vector3);
	}
}

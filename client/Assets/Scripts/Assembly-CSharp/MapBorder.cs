using UnityEngine;

public class MapBorder : MonoBehaviour
{
	private GameObject m_eastBoard;

	private GameObject m_northBoard;

	private GameObject m_southBoard;

	private GameObject m_westBoard;

	public static IntVector2 cloudOccupiedSize;

	private bool isInit;

	private void Start()
	{
	}

	private void init()
	{
	}

	private void OnDestroy()
	{
	}

	public void GuildWarSetup(float HALF_WORLD_MAP_WIDTH, float HALF_WORLD_MAP_HEIGHT)
	{
	}

	public void Setup(IntVector2 mapDimension)
	{
	}

	private void SetupCouldBorder(GameObject dest, Vector3 destPosition, int realSize, int totalSize)
	{
	}
}

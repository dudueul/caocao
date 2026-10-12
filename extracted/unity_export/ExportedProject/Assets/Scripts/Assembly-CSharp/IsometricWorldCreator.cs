using System.Collections.Generic;
using System.Text;
using UnityEngine;

public class IsometricWorldCreator : MonoBehaviour
{
	public static int DX;

	public static int DY;

	public static float isoTileWidth;

	public static float isoTileHeight;

	public static float mapImageWidth;

	public static float mapImageHeight;

	public static IntVector2 preOffset;

	public static IntVector2 postOffset;

	private static StringBuilder ms_sb;

	private static List<IntVector2> m_cannotMovableTiles;

	private List<KeyValuePair<string, Vector3>> m_eventMapEnvsData;

	public List<EventMapEnvObject> m_eventMapEnvs;

	private void Start()
	{
	}

	public static void CaculateSize()
	{
	}

	private void CreateTiles()
	{
	}

	public void AddCannotMovableTile(IntVector2 tile)
	{
	}

	public void DeleteCannotMovableTile(IntVector2 tile)
	{
	}

	public List<IntVector2> GetCannotMovableTiles()
	{
		return null;
	}

	public void ClearTiles()
	{
	}

	public void exportToXML(string filePath)
	{
	}

	public void importFromXML(int eventMapId)
	{
	}

	public void importFromXML(string filePath, int eventMapId)
	{
	}

	private void CreateEventMapEnvObjs()
	{
	}

	private EventMapEnvObject CreateEventMapEnvObj(KeyValuePair<string, Vector3> data, Transform parent)
	{
		return null;
	}

	public void ClearEvnObjects()
	{
	}

	public void FlipTilePos()
	{
	}

	public void FlipEnvObj()
	{
	}
}

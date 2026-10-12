using System.Collections.Generic;
using UnityEngine;

[ExecuteInEditMode]
public class MapObjectGroup : MonoBehaviour
{
	private MapInfo m_mapInfo;

	private GameObject m_objs;

	private List<int> m_tileIdxs;

	private static int ms_mapObjectCounter;

	private Dictionary<int, List<GameObject>> m_tileIdx2MapObject;

	public List<GameObject> m_SortedObjList;

	private void Start()
	{
	}

	public void Init()
	{
	}

	public void ClearListReferences()
	{
	}

	public GameObject CreateNewMapObject(int mapObjectId, IntVector2 gridPos, Vector3 worldPos, string sortingLayer)
	{
		return null;
	}

	private void AddNewMapObject(GameObject mapObjectObj)
	{
	}

	public GameObject GetMapObjectAtGridPos(IntVector2 gridPos)
	{
		return null;
	}

	public void DeleteNewMapObject(GameObject mapObjectObj)
	{
	}

	public void SyncAndSortMapObjectList()
	{
	}

	private void RemoveNullRefOfList(List<GameObject> list)
	{
	}

	private int mapObjListSortCompareFunc(GameObject a, GameObject b)
	{
		return 0;
	}

	private void Reset()
	{
	}

	private void UpdateMapInfo()
	{
	}

	public List<GameObject> GetAllMapObjects()
	{
		return null;
	}

	public void CheckAndUpdateMapObjectList()
	{
	}

	public void RefreshObjectList()
	{
	}

	public List<GameObject> GetMapObjectList(int tileIdx)
	{
		return null;
	}

	public List<GameObject> GetSortedObjList()
	{
		return null;
	}
}

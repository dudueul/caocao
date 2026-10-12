using UnityEngine;

[ExecuteInEditMode]
public class MapEditControl : MonoBehaviour
{
	public MapInfo m_mapInfo;

	public string[] m_mapNames;

	public string m_mapNameFilter;

	public string[] filteredMapNames;

	public int m_mapIndex;

	public int currentMapId;

	private void Start()
	{
	}
}
